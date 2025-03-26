package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    public Location currentLocation;
    private List<Item> inventory;
    private int money;
    private int reputation;

    public Player(String name, Location startLocation) {
        this.name = name;
        this.currentLocation = startLocation;
        this.inventory = new ArrayList<>();
        this.money = 0;
        this.reputation = 0;
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
        currentLocation.getItems().remove(item);
        System.out.println("Picked up: " + item.getName());
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void addReputation(int amount) {
        reputation += amount;
    }

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
    public int getReputation() {
        return reputation;
    }
    public int getMoney() {
        return money;
    }

    public void removeMoney(int amount) {
        money -= amount;
    }
}