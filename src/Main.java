public class Main {
    public static void main(String[] args) {
        LocationNavigator ln = new LocationNavigator();
        ln.loadMap("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek\\src\\mapa");
        HelpCommand helpCommand = new HelpCommand("help.txt");
        StoryCommand storyCommand = new StoryCommand("story.txt");
        ln.navigate();
        helpCommand.execute(null);
    }
}