package Command;

import Game.Item;
import Game.NPC;
import Game.Player;

public class giveCommand implements Command {
    @Override
    public void execute(Player player) {
        System.out.println("Use: give [item name] [NPC name]");
    }

    public void giveItem(Player player, String itemName, String npcName) {
        NPC targetNPC = null;

        for (NPC npc : player.currentLocation.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                targetNPC = npc;
                break;
            }
        }

        if (targetNPC == null) {
            System.out.println("This NPC aint here.");
            return;
        }

        Item itemToGive = null;
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToGive = item;
                break;
            }
        }

        if (itemToGive == null) {
            System.out.println("You aint got this item.");
            return;
        }

        if (!targetNPC.acceptsItem(itemName)) {
            System.out.println(targetNPC.getName() + ": I don't accept this item.");
            return;
        }

        int reward = targetNPC.getItemValue(itemName);
        if (targetNPC.givesMoney()) {
            player.addMoney(reward);
            System.out.println(targetNPC.getName() + " gave you " + reward + " golden coins for " + itemName + ".");
        } else {
            player.addReputation(reward);
            System.out.println(targetNPC.getName() + " raised you reputation by " + reward + " for " + itemName + ".");
        }

        player.removeItem(itemToGive);
    }
}