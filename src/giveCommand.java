class GiveCommand implements Command {
    @Override
    public void execute(Player player) {
        System.out.println("Použij: give [název itemu] [název NPC]");
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
            System.out.println("Toto NPC zde není.");
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
            System.out.println("Tento předmět nemáš v inventáři.");
            return;
        }

        if (!targetNPC.acceptsItem(itemName)) {
            System.out.println(targetNPC.getName() + ": O tento předmět nemám zájem.");
            return;
        }

        int reward = targetNPC.getItemValue(itemName);
        if (targetNPC.givesMoney()) {
            player.addMoney(reward);
            System.out.println(targetNPC.getName() + " ti dal " + reward + " zlatých za " + itemName + ".");
        } else {
            player.addReputation(reward);
            System.out.println(targetNPC.getName() + " ti zvýšil reputaci o " + reward + " bodů za " + itemName + ".");
        }

        player.removeItem(itemToGive);
    }
}