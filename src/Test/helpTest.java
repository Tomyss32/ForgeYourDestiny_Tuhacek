package Test;

import Command.helpCommand;
import Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class helpTest {
    private Player player;
    private helpCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String testFileName = "test_help.txt";

    @BeforeEach
    void setUp() throws IOException {
        player = new Player("TestPlayer", null);
        System.setOut(new PrintStream(outContent));
        createTestFile();
        command = new helpCommand(testFileName);
    }

    private void createTestFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("This is a test help file.");
            writer.newLine();
            writer.write("It contains multiple lines of help text.");
        }
    }

    @Test
    void testExecute_FileExists() {
        command.execute(player);
        String output = outContent.toString();
        assertTrue(output.contains("This is a test help file."));
        assertTrue(output.contains("It contains multiple lines of help text."));
    }

    @Test
    void testExecute_FileDoesNotExist() {
        helpCommand invalidCommand = new helpCommand("non_existent.txt");
        invalidCommand.execute(player);
        assertTrue(outContent.toString().contains("File help was not found"));
    }
}