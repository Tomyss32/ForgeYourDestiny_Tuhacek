package Command;

import Game.Player;

import java.io.*;

/**
 * The historyCommand class reads and displays the historical lore of locations.
 */
public class historyCommand implements Command {
    private String fileName;


    /**
     * Constructs a history command with a specified history file.
     *
     * @param fileName Name of the history file to read.
     */
    public historyCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Executes the command by reading and displaying the history file content.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        String filePath = new File(fileName).getAbsolutePath();
        readFile(filePath);
    }


    /**
     * Reads and prints the contents of the specified history file.
     *
     * @param filePath Path of the file to read.
     */
    private void readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File History was not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading history file: " + e.getMessage());
        }
    }


}