import java.io.*;

class InspectCommand implements Command {
    private String folderPath;

    public InspectCommand(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public void execute(Player player) {
        inspectLocation(player.currentLocation.getName());
    }

    public void inspectLocation(String locationName) {
        String filePath = locationName;

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Nebyl nalezen popis pro lokaci: " + locationName);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }
}