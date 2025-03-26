package Command;

import Game.Item;
import Game.NPC;
import Game.Player;

/**
 * Command that handles giving items from the player's inventory to NPCs in exchange for rewards.
 * It checks if the NPC accepts the item and whether the player has the item in their inventory.
 * The reward is either money or reputation points, depending on the NPC's preferences.
 */
public class giveCommand implements Command {

    /**
     * Executes the give command, printing out instructions.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        System.out.println("Use: give [item name] [NPC name]");
    }

    /**
     * Gives an item from the players inventory to an NPC.
     * The NPC accepts the item, and the player must have the item in their inventory.
     * If the NPC accepts the item, the player receives a reward in the form of money or reputation.
     * The item is then removed from the player's inventory.
     *
     * @param player The player giving the item.
     * @param itemName The name of the item to give.
     * @param npcName The name of the NPC to give the item to.
     */
    public void giveItem(Player player, String itemName, String npcName) {
        NPC targetNPC = null;

        // Find the NPC in the player's current location
        for (NPC npc : player.currentLocation.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                targetNPC = npc;
                break;
            }
        }

        // Check if the NPC was found
        if (targetNPC == null) {
            System.out.println("This NPC aint here.");
            return;
        }

        // Find the item in the player's inventory
        Item itemToGive = null;
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToGive = item;
                break;
            }
        }

        // Check if the player has the item
        if (itemToGive == null) {
            System.out.println("You aint got this item.");
            return;
        }

        // Check if the NPC accepts the item
        if (!targetNPC.acceptsItem(itemName)) {
            System.out.println(targetNPC.getName() + ": I don't accept this item.");
            return;
        }

        // Give the reward (money or reputation)
        int reward = targetNPC.getItemValue(itemName);
        if (targetNPC.givesMoney()) {
            player.addMoney(reward);
            System.out.println(targetNPC.getName() + " gave you " + reward + " golden coins for " + itemName + ".");
        } else {
            player.addReputation(reward);
            System.out.println(targetNPC.getName() + " raised you reputation by " + reward + " for " + itemName + ".");
        }

        // Remove the item from the player's inventory
        player.removeItem(itemToGive);
    }
}