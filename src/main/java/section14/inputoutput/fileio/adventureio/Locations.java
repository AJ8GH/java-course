package section14.inputoutput.fileio.adventureio;

import section12.collections.maps.adventure.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> LOCATIONS = new HashMap<>();

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
        return LOCATIONS.put(key, value);
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
