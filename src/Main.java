public class Main {
    public static void main(String[] args) {
        LocationNavigator ln = new LocationNavigator();
        ln.loadMap("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek2\\src\\map");
        HelpCommand helpCommand = new HelpCommand("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek2\\src\\help");
        StoryCommand storyCommand = new StoryCommand("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek2\\src\\story");
        ln.navigate();
        helpCommand.execute(null);
    }
}