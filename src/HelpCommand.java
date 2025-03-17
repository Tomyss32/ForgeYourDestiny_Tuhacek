import java.io.*;

class HelpCommand implements Command {
    private String filePath;

    public HelpCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(Player player) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading help file: " + e.getMessage());
        }
    }
}