package com.gatech.astroworld.spacetrader.entity;

import static com.gatech.astroworld.spacetrader.entity.Resources.ARTISTIC;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFHERBS;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFWATER;
import static com.gatech.astroworld.spacetrader.entity.Resources.MINERALRICH;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHFAUNA;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHSOIL;
import static com.gatech.astroworld.spacetrader.entity.Resources.WARLIKE;
import static com.gatech.astroworld.spacetrader.entity.Resources.WEIRDMUSHROOMS;

public enum GoodType {
    WATER("Water", 5, 0, 0, 0, 2, 30, 3, 4, LOTSOFWATER, 30, 50, 0),
    FURS("Furs", 6, 0, 0, 0, 0, 250, 10, 10, RICHFAUNA, 230, 280, 0),
    ORE("Ore", 7, 0, 2, 2, 3, 350, 20, 10, MINERALRICH, 350, 420, 0),
    FOOD("Food", 5, 0, 1, 0, 1, 100, 5, 5, RICHSOIL, 90, 160, 0),
    GAMES("Games", 5, 0, 3, 1, 6, 250, -10, 5, ARTISTIC, 160, 270, 0),
    FIREARMS("Firearms", 5, 0, 3, 1, 5, 1250, -75, 100, WARLIKE, 600, 1100, 0),
    MEDICINE("Medicine", 5, 0, 4, 1, 6, 650, -20, 10, LOTSOFHERBS, 400, 700, 0),
    NARCOTICS("Narcotics", 5, 0, 5, 0, 5, 3500, -125, 150, WEIRDMUSHROOMS, 2000, 3000, 0),
    ROBOTS("Robots", 5, 0, 6, 4, 7, 5000, -150, 100, null, 3500, 5000, 0);

    private String name;
    private int size;
    private int rarity;
    private GoodType type;
    private int quantity;
    private int price;
    private int sellCount;

    private int MTLP; //min tech level to produce resource
    private int MTLU; //minimum tech to use resource
    private int TTP; //tech level which produces most of this item
    private int basePrice;
    private int IPL; //price increase per tech level
    private int var; //variance is macimum percent that the price can vary from base
    private Resources conditionModifier; //when his is present, pricce is unusually low
    private int minTrader; //min price offered by random trader (not planet)
    private int maxTrader; //max price offered by random trader (not planet)

    GoodType(String name, int quanity, int price, int MTLP, int MTLU, int TTP, int basePrice,  int IPL, int var,
                Resources conditionModifier, int minTrader, int maxTrader, int sellCount) {
        this.name = name;
        this.quantity = quanity;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
        this.conditionModifier = conditionModifier;
        this.minTrader = minTrader;
        this.maxTrader = maxTrader;
        this.sellCount = sellCount;
    }
    public String getName() {
        return name;
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return var;
    }

    public Resources getConditionModifier() {
        return conditionModifier;
    }

    public int getMinTrader() {
        return minTrader;
    }

    public int getMaxTrader() {
        return maxTrader;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int i) {
        sellCount = i;
    }


    public int getPrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int num) {
        quantity = num;
    }

    public void setPrice(int p) {
        price = p;
    }



}






