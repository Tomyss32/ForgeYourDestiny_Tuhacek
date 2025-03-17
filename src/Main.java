public class Main {
    public static void main(String[] args) {
        LocationNavigator ln = new LocationNavigator();
        ln.loadMap("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek\\src\\mapa");
        HelpCommand helpCommand = new HelpCommand("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek2\\src\\help");
        HelpCommand storyCommand = new HelpCommand("C:\\Users\\tomca\\OneDrive\\Plocha\\ForgeYourDestiny_Tuhacek2\\src\\story");
        ln.navigate();
        helpCommand.execute(null);
    }
}