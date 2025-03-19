import java.io.*;
import java.util.*;

class LocationNavigator {
    private Map<String, Location> locations = new HashMap<>();
    private Player player;
    private HelpCommand helpCommand;
    private StoryCommand storyCommand;
    HistoryCommand historyCommand = new HistoryCommand("history");
    private GiveCommand giveCommand;
    MarryCommand marryCommand = new MarryCommand();
    BuildCommand buildCommand = new BuildCommand();

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
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            bylany.addItem(new Item("Broken Wheel", "A rusty broken wheel. It might be useful."));
            bylany.addItem(new Item("SilverRing", "A valuable silver ring."));
        }
    }

    private void addNPCsToLocations() {
        Location bylany = locations.get("bylany");
        if (bylany != null) {
            NPC blacksmith = new NPC("Blacksmith", false);
            blacksmith.addTradeItem("IronBar", 10);
            bylany.addNPC(blacksmith);
        }

        Location kutnaHora = locations.get("kutnaHora");
        if (kutnaHora != null) {
            NPC merchant = new NPC("Merchant", false);
            merchant.addTradeItem("SilverRing", 100);
            merchant.addTradeItem("GoldenCoin", 100);
            kutnaHora.addNPC(merchant);

            NPC tereza = new NPC("Tereza", false);
            tereza.addTradeItem("Flower", 5);
            kutnaHora.addNPC(tereza);
        }
    }

    public void navigate() {
        Scanner scanner = new Scanner(System.in);
        player = new Player("Hero", locations.get("bylany"));
        HistoryCommand historyCommand = new HistoryCommand("history.txt");
        HelpCommand helpCommand = new HelpCommand("help.txt");
        StoryCommand storyCommand = new StoryCommand("story.txt");
        giveCommand = new GiveCommand();

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

            System.out.print("Enter command (move, pickup, talk, inventory, give [item] [npc], history, help, story, exit): ");
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

    private void movePlayer(String direction) {
        if (player.currentLocation.getExits().containsKey(direction)) {
            player.currentLocation = player.currentLocation.getExits().get(direction);
            System.out.println("You moved to: " + player.currentLocation.getName());
        } else {
            System.out.println("Invalid direction.");
        }
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
            if (npc.getName().equalsIgnoreCase(npcName)) {
                npc.interact();
                return;
            }
        }
        System.out.println("This character is not here.");
    }
}