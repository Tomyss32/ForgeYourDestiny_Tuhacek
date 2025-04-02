package Game;

/**
 * Represents a character in the game, such as a player or NPC.
 * The Character class stores the name of the character and provides
 * a basic interaction method.
 */
public class Character {
    protected String name;


    /**
     * Constructor to create a new character with a given name.
     *
     * @param name The name of the character.
     */
    public Character(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    /**
     * Simulates an interaction with the character. This method
     * outputs a generic greeting.
     * This method can be overridden by subclasses to provide specific interactions.
     */
    public void interact() {
        System.out.println(name + ": Hello, traveler!");
    }
}

