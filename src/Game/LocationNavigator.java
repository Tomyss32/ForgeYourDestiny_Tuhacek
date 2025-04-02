package Game;

import Command.*;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;
/**
 * Class responsible for managing the navigation through locations in the game.
 * It handles loading the map, adding items and NPCs to locations, and allowing the player to navigate, interact with NPCs,
 * pick up items, and execute commands like 'give', 'marry', 'build', etc.
 */
public class LocationNavigator {
    private Map<String, Location> locations = new HashMap<>();
    private Player player;
    private giveCommand giveCommand;
    private buildCommand buildCommand;
    private historyCommand historyCommand;
    private marryCommand marryCommand;

    /**
     * Initializes the LocationNavigator with commands and locations.
     */
    public LocationNavigator() {
        this.buildCommand = new buildCommand();
        this.historyCommand = new historyCommand("history");
        this.marryCommand = new marryCommand();
    }

    /**
     * Loads the map from a file and sets up the locations and exits.
     * It also adds items and NPCs to locations after loading the map.
     *
     * @param filename The name of the file containing the map information.
     */
    public void loadMap(String filename) {
        // Implementation details
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
            System.out.println("Error while reading map file: " + e.getMessage());
        }

        addItemsToLocations();
        addNPCsToLocations();
    }

    /**
     * Adds items to locations based on predefined conditions.
     */
    private void addItemsToLocations() {
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            bylany.addItem(new Item("BrokenWheel", "A rusty broken wheel. It might be useful."));
            bylany.addItem(new Item("SilverRing", "A valuable silver ring."));
        }

        Location kutnaHora = locations.get("kutnaHora");
        if (kutnaHora != null) {
            kutnaHora.addItem(new Item("Potion", "A valuable Potion. Seems fishy."));
            kutnaHora.addItem(new Item("Armor", "I finally found it, i must have been looking for that crap for an eternity!!!!"));

        }
        Location staraKutna = locations.get("staraKutna");
        if (staraKutna != null) {
            staraKutna.addItem(new Item("Food", "Its a piece of art, i want to eat this so bad..."));
            staraKutna.addItem(new Item("Flowers", " A wonderfull bouqet i have to go and give it to caroline as soon as possible."));
        }

        Location dablovka = locations.get("dablovka");
        if (dablovka != null) {
            dablovka.addItem(new Item("Soap", "Im finally getting a bath!!!!!."));
        }

        Location miskovice = locations.get("miskovice");
        if (miskovice != null) {
            miskovice.addItem(new Item("Neklace", "Am i gonna steal it? Nah i shouldnt."));
        }
        Location grunta = locations.get("grunta");
        if (grunta != null) {
            grunta.addItem(new Item("Leather", "A valuable piece of leather. Looks like a good quality too."));

        }

    }

    /**
     * Adds NPCs to locations and assigns them tradeable items.
     */
    private void addNPCsToLocations() {
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            NPC blacksmith = new NPC("Blacksmith", false);
            blacksmith.addTradeItem("Food", 45);
            bylany.addNPC(blacksmith);
        }

        Location kutnaHora = locations.get("kutnaHora");
        if (kutnaHora != null) {
            NPC merchant = new NPC("Merchant", true);
            merchant.addTradeItem("SilverRing", 25);
            merchant.addTradeItem("GoldenCoin", 50);
            kutnaHora.addNPC(merchant);
        }

        Location miskovice = locations.get("miskovice");
        if (miskovice != null) {
            NPC Caroline = new NPC("Caroline", false);
            Caroline.addTradeItem("Soap", 35);
            Caroline.addTradeItem("Flowers", 50);
            miskovice.addNPC(Caroline);
        }

        Location staraKutna = locations.get("staraKutna");
        if (staraKutna != null) {
            NPC Grandmother = new NPC("Grandmother", true);
            Grandmother.addTradeItem("Neklace", 35);
            Grandmother.addTradeItem("Leather", 15);
            staraKutna.addNPC(Grandmother);
        }

        Location dablovka = locations.get("dablovka");
        if (dablovka != null) {
            NPC Devil = new NPC("Devil", true);
            Devil.addTradeItem("Potion", 25);
            dablovka.addNPC(Devil);
        }
        Location grunta = locations.get("grunta");
        if (grunta != null) {
            NPC Henry = new NPC("Henry", true);
            Henry.addTradeItem("Sword", 20);
            Henry.addTradeItem("Armor", 45);
            grunta.addNPC(Henry);
        }

    }

    /**
     * Starts the location navigation process, allowing the player to interact with the game world,
     * pick up items, interact with NPCs, and execute commands.
     */
    public void navigate() {
        Scanner scanner = new Scanner(System.in);
        player = new Player("Pepa", locations.get("bylany"));
        historyCommand historyCommand = new historyCommand("history");
        helpCommand helpCommand = new helpCommand("help");
        storyCommand storyCommand = new storyCommand("story");
        giveCommand = new giveCommand();

        while (true) {
            System.out.println("\nYou are at: " + player.currentLocation.getName());

            if (!player.currentLocation.getExits().isEmpty()) {
                System.out.println("You can go to: " + String.join(", ", player.currentLocation.getExits().keySet()));
            }

            if (!player.currentLocation.getNPCs().isEmpty()) {
                System.out.print("NPCs here: ");
                for (NPC npc : player.currentLocation.getNPCs()) {
                    System.out.print(npc.getName() + " ");
                }
                System.out.println();
            }

            System.out.print("Enter command (move, pickup, talk, inventory, give [item] [npc], history, help, story, build, marry, exit): ");
            String input = scanner.nextLine().trim();
            String[] commandParts = input.split(" ", 3);

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting game...");
                break;
            } else if (commandParts[0].equalsIgnoreCase("move") && commandParts.length > 1) {
                movePlayer(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("pickup") && commandParts.length > 1) {
                pickUpItem(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("talk") && commandParts.length > 1) {
                talkToNPC(commandParts[1]);
            } else if (commandParts[0].equalsIgnoreCase("give") && commandParts.length > 2) {
                giveCommand.giveItem(player, commandParts[1], commandParts[2]);

            } else if (input.equalsIgnoreCase("marry")) {
                marryCommand.execute(player);
            } else if (input.equalsIgnoreCase("build")) {
                buildCommand.execute(player);
            } else if (input.equalsIgnoreCase("inventory")) {
                player.showInventory();
            } else if (input.equalsIgnoreCase("history")) {
                historyCommand.execute(player);
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

    /**
     * Moves the player to a new location if the exit exists.
     *
     * @param direction The direction to move the player in.
     */
    private void movePlayer(String direction) {
        if (player.currentLocation.getExits().containsKey(direction)) {
            player.currentLocation = player.currentLocation.getExits().get(direction);
            System.out.println("You moved to: " + player.currentLocation.getName());
        } else {
            System.out.println("Invalid direction.");
        }
    }

    /**
     * Allows the player to pick up an item from the current location.
     *
     * @param itemName The name of the item to pick up.
     */
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


    /**
     * Allows the player to interact with an NPC.
     *
     * @param npcName The name of the NPC to talk to.
     */
    private void talkToNPC(String npcName) {
        for (NPC npc : player.currentLocation.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                npc.interact();
                return;
            }
        }
        System.out.println("This character is not here.");
    }

    /**
     * Returns the map of locations in the game.
     *
     * @return The map of location names and their corresponding Location objects.
     */
    public Map<String, Location> getLocations() {
        return locations;
    }


}