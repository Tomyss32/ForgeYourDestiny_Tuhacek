import java.io.*;

class StoryCommand implements Command {
    private String filePath;

    public StoryCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(Player player) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File with story was not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while loading a story file: " + e.getMessage());
        }
    }
}