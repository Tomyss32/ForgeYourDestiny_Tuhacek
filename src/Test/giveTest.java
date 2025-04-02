package Test;

import Command.giveCommand;
import Game.Item;
import Game.NPC;
import Game.Player;
import Game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class giveTest {
    private Player player;
    private NPC npc;
    private Item item;
    private giveCommand command;
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location("Market");
        player = new Player("TestPlayer", location);
        npc = new NPC("Merchant");
        item = new Item("Gold Coin", "A valuable coin.");
        location.addNPC(npc);
        player.pickUpItem(item);
        command = new giveCommand();
    }

    @Test
    void testGiveItem_NPCNotFound() {
        command.giveItem(player, "Gold Coin", "Stranger");
        assertTrue(player.getInventory().contains(item));
    }

    @Test
    void testGiveItem_ItemNotFound() {
        command.giveItem(player, "Silver Coin", "Merchant");
        assertTrue(player.getInventory().contains(item));
    }


}