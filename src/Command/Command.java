package Command;

import Game.Player;


/**
 * The Command interface represents an action that can be executed by a player.
 * Implementing classes should define the behavior of the command when executed.
 */
public interface Command {
    void execute(Player player);


}