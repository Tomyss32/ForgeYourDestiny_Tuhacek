import java.util.HashMap;
import java.util.Map;

class Character {
    protected String name;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void interact() {
        System.out.println(name + ": Hello, traveler!");
    }
}

class NPC extends Character {
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
                System.out.println("If not take this precious sword to the merchant.");
                System.out.println("He will give some money for it");
                break;

            case "Merchant":
                System.out.println("Merchant: Welcome to my shop! I have the finest goods in town.");
                System.out.println("I have a work for you!");
                System.out.println("Take this potion and take it to the bandit a Ďáblovka");
                System.out.println("Oh, and you can keep the money from it.");
                System.out.println("Take care!");
                break;

            case "Tereza":
                System.out.println("Tereza: Oh, a new face! Are you from around here?");
                System.out.println("If so, you can win my hand if you can get a 100 reputation!");

                break;

        }
    }


}
