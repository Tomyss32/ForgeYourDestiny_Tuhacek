package Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 * The player can interact with items, manage inventory, and earn money or reputation.
 */
public class Player {
    private String name;
    public Location currentLocation;
    private List<Item> inventory;
    private int money;
    private int reputation;


    /**
     * Constructs a Player with a specified name and starting location.
     *
     * @param name          The name of the player.
     * @param startLocation The initial location where the player starts.
     */
    public Player(String name, Location startLocation) {
        this.name = name;
        this.currentLocation = startLocation;
        this.inventory = new ArrayList<>();
        this.money = 0;
        this.reputation = 0;
    }


    /**
     * Default constructor for Player.
     */
    public Player() {
    }


    /**
     * Allows the player to pick up an item from the current location.
     * The item is added to the player's inventory and removed from the location.
     *
     * @param item The item to be picked up.
     */
    public void pickUpItem(Item item) {
        inventory.add(item);
        currentLocation.getItems().remove(item);
        System.out.println("Picked up: " + item.getName());
    }


    /**
     * Retrieves the player's current inventory.
     *
     * @return A list of items in the player's inventory.
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Removes a specified item from the player's inventory.
     *
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    /**
     * Adds money to the player's balance.
     *
     * @param amount The amount of money to add.
     */
    public void addMoney(int amount) {
        money += amount;
    }


    /**
     * Adds reputation points to the player.
     *
     * @param amount The amount of reputation points to add.
     */
    public void addReputation(int amount) {
        reputation += amount;
    }


    /**
     * Displays the player's inventory, money, and reputation.
     */
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
        System.out.println("Money: " + money + " gold");
        System.out.println("Reputation: " + reputation + " points");
    }

    /**
     * Gets the player's current reputation points.
     *
     * @return The player's reputation points.
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * Gets the player's current amount of money.
     *
     * @return The player's money balance.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Removes money from the player's balance.
     *
     * @param amount The amount of money to remove.
     */
    public void removeMoney(int amount) {
        money -= amount;
    }
}