package Game;

import java.util.*;

/**
 * Represents a location in the game world.
 * Each location has a name, exits to other locations, items, and NPCs.
 */
public class Location {
    public String name;
    public Map<String, Location> exits;
    public List<Item> items;
    public List<NPC> npcs;


    /**
     * Constructs a new Location with a given name.
     *
     * @param name The name of the location.
     */
    public Location(String name) {
        this.name = name;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds an exit from this location to another.
     *
     * @param direction   The direction in which the exit leads (e.g., "north", "south").
     * @param destination The location to which this exit leads.
     */
    public void addExit(String direction, Location destination) {
        exits.put(direction, destination);
    }

    /**
     * Adds an item to the location.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Adds an NPC to the location.
     *
     * @param npc The NPC to be added.
     */
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    /**
     * Gets all NPCs present in the location.
     *
     * @return A list of NPCs in this location.
     */
    public List<NPC> getNPCs() {
        return npcs;
    }

    /**
     * Gets all items present in the location.
     *
     * @return A list of items in this location.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets all exits from this location.
     *
     * @return A map of exits with directions as keys and corresponding locations as values.
     */
    public Map<String, Location> getExits() {
        return exits;
    }
}