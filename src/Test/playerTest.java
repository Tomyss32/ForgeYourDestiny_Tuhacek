package Test;

import Game.Item;
import Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class playerTest {
    private Player player;
    private Item sword;
    private Item shield;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", null);
        sword = new Item("Sword", "A sharp blade");
        shield = new Item("Shield", "A sturdy protector");
    }


    @Test
    void testAddMoney() {
        player.addMoney(100);
        assertEquals(100, player.getMoney());
    }

    @Test
    void testRemoveMoney() {
        player.addMoney(100);
        player.removeMoney(40);
        assertEquals(60, player.getMoney());
    }

    @Test
    void testAddReputation() {
        player.addReputation(10);
        assertEquals(10, player.getReputation());
    }
}
