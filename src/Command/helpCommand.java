package Command;

import Game.Player;

import java.io.*;


/**
 * The helpCommand class reads and displays the content of a help file.
 * It provides instructions or information.
 */
public class helpCommand implements Command {
    private String fileName;

    /**
     * Constructs a help command with a help file.
     *
     * @param fileName Name of the help file to read.
     */
    public helpCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Executes the command by reading and displaying the help file content.
     *
     * @param player The player executing the command.
     */
    @Override
    public void execute(Player player) {
        String filePath = new File(fileName).getAbsolutePath();
        readFile(filePath);
    }

    /**
     * Reads and prints the contents of the specified help file.
     *
     * @param filePath Path of the file to read.
     */
    private void readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File help was not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading help file: " + e.getMessage());
        }
    }
}