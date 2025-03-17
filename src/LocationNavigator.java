import java.io.*;
import java.util.*;

class LocationNavigator {
    private Map<String, Location> locations = new HashMap<>();
    private Player player;
    private HelpCommand helpCommand;
    private StoryCommand storyCommand;

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
            System.out.println("Chyba při čtení souboru s mapou: " + e.getMessage());
        }

        addItemsToLocations();
        addNPCsToLocations();
    }

    private void addItemsToLocations() {
        Location bylany = locations.get("Bylany");
        if (bylany != null) {
            bylany.addItem(new Item("Broken Wheel", "A rusty broken wheel. It might be useful."));
        }
    }

    private void addNPCsToLocations() {
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            bylany.addNPC(new NPC("Blacksmith"));
        }

        Location kutnaHora = locations.get("kutnaHora");
        if (kutnaHora != null) {
            kutnaHora.addNPC(new NPC("Merchant"));
            kutnaHora.addNPC(new NPC("Tereza"));
        }
    }

    public void navigate() {
        Scanner scanner = new Scanner(System.in);
        player = new Player("Hero", locations.values().iterator().next());

        helpCommand = new HelpCommand("help.txt");
        storyCommand = new StoryCommand("story.txt");

        while (true) {
            System.out.println("\nYou are at: " + player.currentLocation.name);
            if (!player.currentLocation.exits.isEmpty()) {
                System.out.println("You can go to: " + String.join(", ", player.currentLocation.exits.keySet()));
            } else {
                System.out.println("No available paths from here.");
            }

            if (!player.currentLocation.getNPCs().isEmpty()) {
                System.out.print("NPCs here: ");
                for (NPC npc : player.currentLocation.getNPCs()) {
                    System.out.print(npc.name + " ");
                }
                System.out.println();
            }

            System.out.print("Enter command (move [direction], pickup [item], talk [character], inventory, help, story, exit): ");
            String input = scanner.nextLine().trim();
            String[] commandParts = input.split(" ");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game...");
                break;
            } else if (commandParts[0].equalsIgnoreCase("move") && commandParts.length > 1) {
                player.move(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("pickup") && commandParts.length > 1) {
                pickUpItem(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("talk") && commandParts.length > 1) {
                talkToNPC(commandParts[1]);
            } else if (input.equalsIgnoreCase("inventory")) {
                player.showInventory();
            } else if (input.equalsIgnoreCase("help")) {
                helpCommand.execute(player);
            } else if (input.equalsIgnoreCase("story")) {
                storyCommand.execute(player);
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }
        scanner.close();
    }

    private void pickUpItem(String itemName) {
        Item itemToPick = null;
        for (Item item : player.currentLocation.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToPick = item;
                break;
            }
        }
        if (itemToPick != null) {
            player.pickUpItem(itemToPick);
        } else {
            System.out.println("Item not found in this location.");
        }
    }

    private void talkToNPC(String npcName) {
        for (NPC npc : player.currentLocation.getNPCs()) {
            if (npc.name.equalsIgnoreCase(npcName)) {
                npc.interact();
                return;
            }
        }
        System.out.println("This character is not here.");
    }
}