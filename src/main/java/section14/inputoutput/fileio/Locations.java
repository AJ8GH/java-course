package section14.inputoutput.fileio;

import section12.collections.maps.adventure.Location;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> LOCATIONS = new HashMap<>();

    static {
        Map<String, Integer> exits = new HashMap<>();
        LOCATIONS.put(0, new Location(0, "You are sitting in front of a computer learning Java", exits));

        exits = new HashMap<>();
        exits.put("W", 2);
        exits.put("E", 3);
        exits.put("S", 4);
        exits.put("N", 5);
        LOCATIONS.put(1, new Location(1, "You are standing at the end of a road before a small bridge", exits));

        exits = new HashMap<>();
        exits.put("N", 5);
        LOCATIONS.put(2, new Location(2, "You are at the top of a hill", exits));

        exits = new HashMap<>();
        exits.put("W", 1);
        LOCATIONS.put(3, new Location(3, "You are inside a building, a well house for a small spring", exits));

        exits = new HashMap<>();
        exits.put("N", 1);
        exits.put("W", 2);
        LOCATIONS.put(4, new Location(4, "You are in a valley beside a stream", exits));

        exits = new HashMap<>();
        exits.put("S", 1);
        exits.put("W", 2);
        LOCATIONS.put(5, new Location(5, "You are in the forest", exits));
    }

    @Override
    public int size() {
        return LOCATIONS.size();
    }

    @Override
    public boolean isEmpty() {
        return LOCATIONS.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return LOCATIONS.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return LOCATIONS.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return LOCATIONS.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return LOCATIONS.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        LOCATIONS.putAll(m);
    }

    @Override
    public void clear() {
        LOCATIONS.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return LOCATIONS.keySet();
    }

    @Override
    public Collection<Location> values() {
        return LOCATIONS.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return LOCATIONS.entrySet();
    }
}
