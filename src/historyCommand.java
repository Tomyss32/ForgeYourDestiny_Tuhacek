import java.io.*;

class HistoryCommand implements Command {
    private String fileName;

    public HistoryCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(Player player) {
        String filePath = new File(fileName).getAbsolutePath(); // Získání absolutní cesty
        readFile(filePath);
    }

    private void readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Soubor historie nenalezen: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru historie: " + e.getMessage());
        }
    }
}