import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    public Location currentLocation;
    private List<Item> inventory;

    public Player(String name, Location startLocation) {
        this.name = name;
        this.currentLocation = startLocation;
        this.inventory = new ArrayList<>();
    }

    public void move(String direction) {
        if (currentLocation.exits.containsKey(direction)) {
            currentLocation = currentLocation.exits.get(direction);
            System.out.println("You moved to: " + currentLocation.name);
            displayAvailableItems();
        } else {
            System.out.println("Invalid direction.");
        }
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
        currentLocation.getItems().remove(item);
        System.out.println("Picked up: " + item.getName());
    }

    public void talkTo(Character character) {
        character.interact();
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
    }

    private void displayAvailableItems() {
        if (!currentLocation.getItems().isEmpty()) {
            System.out.println("You can pick up the following items:");
            for (Item item : currentLocation.getItems()) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}