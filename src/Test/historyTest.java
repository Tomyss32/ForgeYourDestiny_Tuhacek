package Test;

import Command.historyCommand;
import Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class historyTest {
    private Player player;
    private historyCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String testFileName = "test_history.txt";

    @BeforeEach
    void setUp() throws IOException {
        player = new Player("TestPlayer", null);
        System.setOut(new PrintStream(outContent));
        createTestFile();
        command = new historyCommand(testFileName);
    }

    private void createTestFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("This is a test history.");
            writer.newLine();
            writer.write("It has multiple lines.");
        }
    }

    @Test
    void testExecute_FileExists() {
        command.execute(player);
        String output = outContent.toString();
        assertTrue(output.contains("This is a test history."));
        assertTrue(output.contains("It has multiple lines."));
    }

    @Test
    void testExecute_FileDoesNotExist() {
        historyCommand invalidCommand = new historyCommand("non_existent.txt");
        invalidCommand.execute(player);
        assertTrue(outContent.toString().contains("File History was not found"));
    }
}