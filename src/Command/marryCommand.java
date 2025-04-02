package Command;

import Game.Player;

/**
 * The marryCommand allows the player to marry if they have high enough reputation.
 */
public class marryCommand implements Command {
    private static final int DEFAULT_REQUIRED_REPUTATION = 150; // Default required reputation
    private final int requiredReputation;

    /**
     * Constructs a marry command with a default reputation requirement.
     */
    public marryCommand() {
        this.requiredReputation = DEFAULT_REQUIRED_REPUTATION;
    }

    /**
     * Constructs a marry command with a custom reputation requirement.
     *
     * @param requiredReputation The required reputation to get married.
     */
    public marryCommand(int requiredReputation) {
        this.requiredReputation = requiredReputation;
    }

    /**
     * Executes the marriage command, checking if the player meets the reputation requirement.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        int currentReputation = player.getReputation();

        if (currentReputation >= requiredReputation) {
            System.out.println("Congratulations! You are respected enough and can get married.");
            System.out.println("The wedding took place with great celebration, and you are now officially married!");
            System.out.println("The game is over. Thank you for playing!");
            System.exit(0);
        } else {
            int remaining = requiredReputation - currentReputation;
            System.out.println("Your reputation is not high enough for marriage.");
            System.out.println("You need " + remaining + " more reputation points to marry.");
        }
    }
}