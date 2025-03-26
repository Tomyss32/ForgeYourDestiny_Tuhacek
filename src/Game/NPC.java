package Game;

import java.util.HashMap;
import java.util.Map;

public class NPC extends Character {
    private Map<String, Integer> tradeItems;
    private boolean givesMoney;

    public NPC(String name, boolean givesMoney) {
        super(name);
        this.givesMoney = givesMoney;
        this.tradeItems = new HashMap<>();
    }

    public void addTradeItem(String itemName, int value) {
        tradeItems.put(itemName, value);
    }

    public boolean acceptsItem(String itemName) {
        return tradeItems.containsKey(itemName);
    }

    public int getItemValue(String itemName) {
        return tradeItems.getOrDefault(itemName, 0);
    }

    public boolean givesMoney() {
        return givesMoney;
    }

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