package com.gatech.astroworld.spacetrader.model;

import android.widget.Toast;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.Player;
import java.util.Random;

import java.util.List;

public class Store {

    private List<MarketGood> storeInventory;
    private int storeCredits;
    private List<GoodType> cartBuy;
    private List<MarketGood> cartSell;


    public void populateStoreInventory() {

    }


    public void buy(Player buyer) {
        int availSpace = buyer.getShip().getCapacity() - buyer.getShip().cargoAmount();
        if (cartBuy.size() <= availSpace && buyer.getCredits() >= cartBuyTotal()) {
            for (GoodType item: cartBuy) {
                Integer index = buyer.getShip().containsCargo(item);
                int originalQuantity = buyer.getShip().getCargoList().get(index).getQuantity();
                if (index != null) {
                    buyer.getShip().getCargoList().get(index).setQuantity(originalQuantity +
                            item.getQuantity());
                } else {
                    buyer.getShip().getCargoList().add(item);
                }
            }
        }
        cartBuy = null;
    }

    public void addToCartBuy() {
        for (int i = 0; i < storeInventory.size(); i++) {
            int quantBuy = storeInventory.get(i).getCount();
            if (quantBuy != 0) {
                GoodType good = storeInventory.get(i).getGoodType();
                good.setQuantity(storeInventory.get(i).getCount());
                good.setPrice(storeInventory.get(i).getPrice());
                cartBuy.add(good);
            }
        }
        for (MarketGood good: storeInventory) {
            good.setCount(0);
        }
    }

    public void incrementCountBuy(MarketGood good) {
        good.setCount(good.getCount() + 1);
    }

    public void decrementCountBuy(MarketGood good) {
        int i = good.getCount() - 1;
        if (i >= 0) {
            good.setCount(i-1);
        }
    }

    public void incrementCountSell(GoodType good) {
        int i = good.getSellCount();
        if (i <= good.getQuantity()) {
            good.setSellCount(i + 1);
        }
    }


    public void decrementCountSell(GoodType good) {
        int i = good.getSellCount();
        if (i >= 0) {
            good.setSellCount(i - 1);
        }
    }


    public void addToCartSell(Player player) {
        List<GoodType> list = player.getShip().getCargoList();

        for (int i = 0; i < list.size(); i++) {
            int quantSell = list.get(i).getSellCount();
            if (quantSell != 0) {
                MarketGood good = new MarketGood(list.get(i), list.get(i).getName(), 0,
                        quantSell, list.get(i).getBasePrice(), null, null);
                cartSell.add(good);
                int orig = list.get(i).getQuantity();
                list.get(i).setQuantity(orig - quantSell);
            }
        }
        for (GoodType good: list) {
            good.setSellCount(0);
        }
    }

    public void sell(Player player) {
        player.setCredits(player.getCredits() + cartSellTotal());
        for (MarketGood gSold: cartSell) {
            for (MarketGood gMark: storeInventory) {
                if (gSold.getName().equals(gMark.getName())) {
                    int orig = gMark.getQuantity();
                    gMark.setCount(orig + gSold.getQuantity());
                }
            }
            gSold.setSolarSystem(player.getCurrentSystem());
            gSold.setPlanet(player.getCurrentPlanet());
            storeInventory.add(gSold);
        }
    }


    public int cartBuyTotal() {
        int total = 0;
        for (GoodType item: cartBuy) {
            total += item.getPrice();
        }
        return total;
    }

    public int cartSellTotal() {
        int total = 0;
        for (MarketGood item: cartSell) {
            total += item.getPrice();
        }
        return total;
    }

    private class MarketGood {
        private GoodType good;
        private String name;
        private int count;
        private Planet planet;
        private SolarSystem sys;
        private int quantity;
        private int price;

        public MarketGood(GoodType good, String name, int count, int quantity, int price,
                          SolarSystem sys, Planet planet) {
            this.good = good;
            this.name = good.getName();
            this.count = count;
            this.quantity = quantity;
            this.price = calculatePrice();

        }

        private int calculatePrice() {
            int newPrice = 0;
            newPrice = good.getBasePrice() + (good.getIPL() * sys.getTechLevel().ordinal()) +
                    good.getVar();
            return newPrice;
        }

        public int getCount() {
            return count;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setCount(int c) {
            count = c;
        }

        public GoodType getGoodType() {
            return good;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setSolarSystem(SolarSystem system) {
            sys = system;
        }

        public void setPlanet(Planet plan) {
            planet = plan;
        }


        /**
         * Calculates the expected quantity of a GoodType given the System's tech level
         *
         * @param item instance of good type item
         * @param play current player
         * @return expected quantity of GoodType based on tech level
         */
        private int calculateQuantity(GoodType item, Player play) {
            int base = 10;
            if (play.getCurrentSystem().getTechLevel().ordinal() < item.getMTLP()) {
                return 0;
            } else {
                Random ranCalc = new Random();
                int upperBound = 10;
                base += ranCalc.nextInt(upperBound);

                if (play.getCurrentSystem().getTechLevel().ordinal() == item.getTTP()) {
                    upperBound =15;
                    base += ranCalc.nextInt(upperBound);
                }

            }
            return base;
        }
        //push to solve conflict

    }




}
