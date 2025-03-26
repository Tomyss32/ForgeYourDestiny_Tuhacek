package Command;

import Game.Player;

import java.io.*;

/**
 * The storyCommand class loads and prints the story from a file.
 */
public class storyCommand implements Command {
    private String fileName;

    /**
     * Constructs a story command with a file.
     *
     * @param fileName The name of the file containing the story.
     */
    public storyCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Executes the story command that is reading and displaying the story.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        String filePath = new File(fileName).getAbsolutePath();
        readFile(filePath);
    }


    /**
     * Reads and prints the story from the file.
     */
    private void readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File story was not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading story file: " + e.getMessage());
        }
    }
}