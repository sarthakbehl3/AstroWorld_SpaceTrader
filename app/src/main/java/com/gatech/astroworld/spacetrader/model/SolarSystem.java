package com.gatech.astroworld.spacetrader.model;
import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;
import com.gatech.astroworld.spacetrader.entity.Resources;
import com.gatech.astroworld.spacetrader.entity.TechLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem {
    private String name;
    private List<Planet> listOfPlanets = new ArrayList<>();
    private TechLevel techLevel;
    private Resources resources;
    private SysLocation sysLocation;
    private int systemSize = 1000;
    private int maxPlanets = 9;

    private int systemMargin = 5;


    private Random rand = new Random();
    public SolarSystem () {
        SysLocation loc = new SysLocation();
        this.sysLocation = loc;

        final TechLevel[] TECHLEVEL_VALUES = TechLevel.values();
        final Resources[] RESOURCES_VALUES = Resources.values();
        this.name = SolarSystemName[rand.nextInt(SolarSystemName.length)];
        this.techLevel = TECHLEVEL_VALUES[rand.nextInt(TECHLEVEL_VALUES.length - 1)];
        this.resources = RESOURCES_VALUES[rand.nextInt(RESOURCES_VALUES.length - 1)];

        /* Compare new system distance from galactic center to the distance of every system created.
         * If the new system is within +/- systemMargin of an existing system, then the new system
         * distance is changed. */
        for (SolarSystem tempSys: Game.getInstance().getSystemList()) {
            double tempX = tempSys.sysLocation.xPos;
            double tempY = tempSys.sysLocation.yPos;
            double distance = Math.sqrt(Math.pow((sysLocation.xPos - tempX), 2)
                            + Math.pow((sysLocation.yPos - tempY), 2));

            while (distance < systemMargin) {
                sysLocation.randomLocation();
                distance = Math.sqrt(Math.pow((sysLocation.xPos - tempX), 2)
                        + Math.pow((sysLocation.yPos - tempY), 2));
            }
        }
        for (int i = 0; i < rand.nextInt(maxPlanets - 1) + 1; i++) {
            addPlanet(new Planet(this));
        }
    }

    public int getSystemSize() {
        return this.systemSize;
    }

    public List<Planet> getListOfPlanets() {
        return listOfPlanets;
    }

    public void addPlanet(Planet newPlanet) {
        this.listOfPlanets.add(newPlanet);
        Random rand = new Random();
        PoliticalSystems[] arr = PoliticalSystems.values();
        PoliticalSystems g = arr[rand.nextInt(PoliticalSystems.values().length - 1)];
        newPlanet.setGov(g);
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public class SysLocation {
        private int galaxySize = Game.getInstance().getGalaxySize();
        Random random = new Random();
        private double xPos;
        private double yPos;
        private double galacCenterDist;

        SysLocation () {
            this.xPos = (random.nextDouble() * 2 * galaxySize) - galaxySize;
            this.yPos = (random.nextDouble() * 2 * galaxySize) - galaxySize;
            galacCenterDist = Math.hypot(xPos, yPos);
        }

        public double getGalacCenterDist() {
            return galacCenterDist;
        }
        public void updateLocation(double newX, double newY){
            this.xPos = newX;
            this.yPos = newY;
            galacCenterDist = Math.hypot(xPos, yPos);
        }
        public void randomLocation() {
            updateLocation((random.nextDouble() * 2 * galaxySize) - galaxySize,
                            (random.nextDouble() * 2 * galaxySize) - galaxySize);
        }

        public double getxPos() {
            return xPos;
        }

        public double getyPos() {
            return yPos;
        }

    }

    @Override
    public String toString() {

        /*return ("\n\n\nName:  " + name + "\nPosition: (" + this.sysLocation.getxPos() + "," + this.sysLocation.getyPos() + ")"
                + "\nTech Level: " + techLevel.toString() + "\nResources: "
                + this.resources.toString() + "\nTech: " + techLevel.toString()) + "\nNumber of Planets: " + listOfPlanets.size(); */
        return "Testing system toString";
    }

    private String[] SolarSystemName =
    {
        "Acamar",
            "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",			// One of the heroes in Master of Magic
            "Bretel",		// This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",		// The city I lived in while programming this game
            "Carzon",
            "Castor",		// A Greek demi-god
            "Cestus",
            "Cheron",
            "Courteney",	// After Courteney Cox…
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",		// One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",		// Iron
            "Festen",		// A great Scandinavian movie
            "Fourmi",		// An ant, in French
            "Frolix",		// A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",		// One way of writing the name of king Arthur's wife
            "Hades",		// The underworld
            "Hamlet",		// From Shakespeare
            "Helena",		// Of Troy
            "Hulst",		// A Dutch plant
            "Iodine",		// An element
            "Iralius",
            "Janus",		// A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",		// A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",			// My dog's name
            "Klaatu",		// From a classic SF movie
            "Klaestron",
            "Korma",		// An Indian sauce
            "Kravat",		// Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",		// A king in a Greek tragedy
            "Largo",
            "Lave",			// The starting system in Elite
            "Ligon",
            "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",		// The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",		// The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",		// A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",		// The name of my daughter
            "Nelvana",
            "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",			// An interesting spelling of the great river
            "Odet",
            "Og",			// The last of the witches in Pratchett's Discworld
            "Omega",		// The end of it all
            "Omphalos",		// Greek for navel
            "Orias",
            "Othello",		// From Shakespeare
            "Parade",		// This word means the same in Dutch and in English
            "Penthara",
            "Picard",		// The enigmatic captain from ST:TNG
            "Pollux",		// Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",			// A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",		// The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",			// That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",		// A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",		// A seldom encountered Dutch girl's name
            "Titan",		// The largest moon of Jupiter
            "Torin",		// A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",		// The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",		// A Greek hero
            "Yew",			// A city which is in almost all of the Ultima games
            "Yojimbo",		// A film by Akira Kurosawa
            "Zalkon",
            "Zuul"			// From the first Ghostbusters movie
    };


}
