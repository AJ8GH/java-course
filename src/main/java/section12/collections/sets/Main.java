package section12.collections.sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        HeavenlyBody planet = new HeavenlyBody("Mercury", 88);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        planet = new HeavenlyBody("Earth", 365);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        planet = new HeavenlyBody("Earth", 365);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        printSet(planets);
//        Sets can be combined as a union. duplicates will be discarded

    }

    private static void printSet(Set<HeavenlyBody> set) {
        for (HeavenlyBody heavenlyBody : set) {
            System.out.println(heavenlyBody.getName());
        }
    }
}
