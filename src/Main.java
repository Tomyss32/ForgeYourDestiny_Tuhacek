import Command.helpCommand;
import Command.storyCommand;
import Game.LocationNavigator;

public class Main {
    public static void main(String[] args) {
        LocationNavigator ln = new LocationNavigator();
        ln.loadMap("map");
        helpCommand helpCommand = new helpCommand("help");
        storyCommand storyCommand = new storyCommand("story");
        ln.navigate();

    }
}