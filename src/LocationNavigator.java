import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class LocationNavigator {
    private Map<String, Location> locations = new HashMap<>();
    private Player player;

    public void loadMap(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String locationName = parts[0].trim();
                    Location location = locations.computeIfAbsent(locationName, Location::new);
                    for (int i = 1; i < parts.length; i++) {
                        String exitName = parts[i].trim();
                        Location exitLocation = locations.computeIfAbsent(exitName, Location::new);
                        location.addExit(exitName, exitLocation);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading map file: " + e.getMessage());
        }

        addItemsToLocations();
    }

    private void addItemsToLocations() {
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            bylany.addItem(new Item("Wheel", "A rusty broken wheel that might be usefull"));
        }

    }

    public void navigate() {
        Scanner scanner = new Scanner(System.in);
        player = new Player("Hero", locations.values().iterator().next());

        while (true) {
            System.out.println("You are at: " + player.currentLocation.name);
            if (player.currentLocation.exits.isEmpty()) {
                System.out.println("No available paths from here.");
                break;
            }
            System.out.println("You can go to: " + String.join(", ", player.currentLocation.exits.keySet()));
            System.out.print("Enter command (move [direction], pickup [item], talk [character], inventory, or 'exit' to quit): ");
            String input = scanner.nextLine().trim();
            String[] commandParts = input.split(" ");

            if (commandParts[0].equalsIgnoreCase("exit")) {
                break;
            } else if (commandParts[0].equalsIgnoreCase("move") && commandParts.length > 1) {
                player.move(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("pickup") && commandParts.length > 1) {
                Item itemToPick = null;
                for (Item item : player.currentLocation.getItems()) {
                    if (item.getName().equalsIgnoreCase(commandParts[1])) {
                        itemToPick = item;
                        break;
                    }
                }
                if (itemToPick != null) {
                    player.pickUpItem(itemToPick);
                } else {
                    System.out.println("Item not found in this location.");
                }
            } else if (commandParts[0].equalsIgnoreCase("talk") && commandParts.length > 1) {
                Character character = new Character(commandParts[1]);
                player.talkTo(character);
            } else if (commandParts[0].equalsIgnoreCase("inventory")) {
                player.showInventory();
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }
        scanner.close();
    }
    }
