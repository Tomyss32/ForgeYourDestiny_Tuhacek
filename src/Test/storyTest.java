package Test;

import Command.storyCommand;
import Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class storyTest {
    private Player player;
    private storyCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String testFileName = "test_story.txt";

    @BeforeEach
    void setUp() throws IOException {
        player = new Player("TestPlayer", null);
        System.setOut(new PrintStream(outContent));
        createTestFile();
        command = new storyCommand(testFileName);
    }

    private void createTestFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("This is a test story.");
            writer.newLine();
            writer.write("It has multiple lines.");
        }
    }

    @Test
    void testExecute_FileExists() {
        command.execute(player);
        String output = outContent.toString();
        assertTrue(output.contains("This is a test story."));
        assertTrue(output.contains("It has multiple lines."));
    }

    @Test
    void testExecute_FileDoesNotExist() {
        storyCommand invalidCommand = new storyCommand("non_existent.txt");
        invalidCommand.execute(player);
        assertTrue(outContent.toString().contains("File story was not found"));
    }
}