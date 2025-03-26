package Command;

import Game.Player;

import java.io.*;

public class helpCommand implements Command {
    private String fileName;

    public helpCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(Player player) {
        String filePath = new File(fileName).getAbsolutePath();
        readFile(filePath);
    }

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