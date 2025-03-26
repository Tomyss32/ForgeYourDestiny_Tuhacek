package Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a non-player character (NPC) in the game.
 * NPCs can trade items, provide quests, and interact with the player.
 */
public class NPC extends Character {
    private Map<String, Integer> tradeItems;
    private boolean givesMoney;

    /**
     * Constructs an NPC with a specified name and whether they give money in exchange for items.
     *
     * @param name The name of the NPC.
     * @param givesMoney True if the NPC gives money for items, false if they provide reputation.
     */
    public NPC(String name, boolean givesMoney) {
        super(name);
        this.givesMoney = givesMoney;
        this.tradeItems = new HashMap<>();
    }

    /**
     * Constructs an NPC with a specified name.
     *
     * @param name The name of the NPC.
     */
    public NPC(String name) {
        super(name);
    }

    /**
     * Adds an item that the NPC is willing to trade.
     *
     * @param itemName The name of the item.
     * @param value    The value of the item if its money or reputation.
     */
    public void addTradeItem(String itemName, int value) {
        tradeItems.put(itemName, value);
    }

    /**
     * Checks if the NPC accepts a specific item for trade.
     *
     * @param itemName The name of the item.
     * @return True if the NPC accepts the item, false otherwise.
     */
    public boolean acceptsItem(String itemName) {
        return tradeItems.containsKey(itemName);
    }

    /**
     * Gets the value of an item that the NPC trades.
     *
     * @param itemName The name of the item.
     * @return The item's value, or 0 if the item is not in the NPC's trade list.
     */
    public int getItemValue(String itemName) {
        return tradeItems.getOrDefault(itemName, 0);
    }

    /**
     * Determines if the NPC gives money instead of reputation when trading.
     *
     * @return True if the NPC gives money, false if they provide reputation.
     */
    public boolean givesMoney() {
        return givesMoney;
    }


    /**
     * Defines how the NPC interacts with the player.
     * Each NPC has a unique dialogue.
     */
    @Override
    public void interact() {
        switch (name) {
            case "Blacksmith":
                System.out.println("Blacksmith: Ah, traveler! Need a new sword?");
                System.out.println("If not take this precious Silver Ring to the merchant.");
                System.out.println("He will give you some money for it");
                break;

            case "Merchant":
                System.out.println("Merchant: Welcome to my shop! I have the finest goods in town.");
                System.out.println("I have a work for you! But i can also buy From you.");
                System.out.println("Take this potion tho and deliver it to the bandit at Ďáblovka");
                System.out.println("Oh, and you can keep the money from it.");
                System.out.println("Take care!");
                break;

            case "Devil":
                System.out.println("Devil: You look like a shit!!");
                System.out.println("Take this soap and take a bath at Miskovice");
                System.out.println("You are surely gaining some reputation after that!");
                System.out.println("Oh and if you have something you want to give me!");
                System.out.println("You know where to find me...");
                break;

            case "Caroline":
                System.out.println("Caroline: Oi you are here for that bath aye!");
                System.out.println("If you have the Soap you can take the bath");
                System.out.println("After that bath take this old rusty Neklace and take it to Stara Kutna");
                System.out.println("There you will find a Grandmother");
                System.out.println("Give it to her for some shiny gold");
                break;

            case "Grandmother":
                System.out.println("Grandmother: The last 20 years of my life i have been searching for one uniqe neklace");
                System.out.println("If you have the Neklace i will shower zou in gold");
                System.out.println("if not though, take this food to that old fool at bylany");
                System.out.println("I hope he will like it!");
                break;

        }
    }
}