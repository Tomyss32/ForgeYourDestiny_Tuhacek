package Test;

import Game.Location;
import Game.LocationNavigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Map;

class mapTest {
    private LocationNavigator navigator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String testFileName = "test_map.txt";

    @BeforeEach
    void setUp() throws IOException {
        System.setOut(new PrintStream(outContent));
        createTestFile();
        navigator = new LocationNavigator();
    }

    private void createTestFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("Town,Forest,Cave");
            writer.newLine();
            writer.write("Forest,Town,Mountain");
        }
    }

    @Test
    void testLoadMap_FileExists() {
        navigator.loadMap(testFileName);
        Map<String, Location> locations = navigator.getLocations();
        assertTrue(locations.containsKey("Town"));
        assertTrue(locations.containsKey("Forest"));
        assertTrue(locations.containsKey("Cave"));
        assertTrue(locations.get("Town").getExits().containsKey("Forest"));
    }

    @Test
    void testLoadMap_FileDoesNotExist() {
        navigator.loadMap("non_existent.txt");
        assertTrue(outContent.toString().contains("Error while reading map file"));
    }
}