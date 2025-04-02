package Command;

import Game.Player;

/**
 * Command that allows the player to build their own forge if they have enough gold.
 * If the player has at least the required amount of gold, they can buy a forge and end the game.
 */
public class buildCommand implements Command {
    private static final int REQUIRED_GOLD = 150;

    /**
     * Executes the build command, checking if the player has enough gold.
     * If the player meets the gold requirement, they successfully buy the forge.
     * Otherwise, they are informed that they lack sufficient gold.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        if (player.getMoney() >= REQUIRED_GOLD) {
            player.removeMoney(REQUIRED_GOLD);
            System.out.println("Congratulations! You have enough gold to build your own forge.");
            System.out.println("The purchase from the Old Blacksmith was successfully completed, and you are now a proud owner of the Bylany forge!");
            System.out.println("The game is over. Thank you so much for playing!");

            System.exit(0);
        } else {
            System.out.println("You don't have enough gold to build a forge. You need at least " + REQUIRED_GOLD + " gold.");
        }
    }
}