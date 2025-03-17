import java.util.*;

class Location {
    public String name;
    public Map<String, Location> exits;
    public List<Item> items;
    public List<NPC> npcs;

    public Location(String name) {
        this.name = name;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addExit(String direction, Location destination) {
        exits.put(direction, destination);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<String, Location> getExits() {
        return exits;
    }
}