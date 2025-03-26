package Game;


/**
 * Represents an item with a name and description.
 * This class is used to manage items that can be added to a player's inventory.
 * The Item class implements Comparable to allow sorting of items by name.
 */
public class Item {
    private String name;
    private String description;


    /**
     * Constructor to create a new item.
     *
     * @param name The name of the item.
     * @param description A description of the item.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }


    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }
}