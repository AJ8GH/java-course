package section14.inputoutput.fileio;

import section12.collections.maps.adventure.Location;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Deserialiser {
    public static void main(String[] args) {
        Deserialiser deserialiser = new Deserialiser();
        deserialiser.fromCSV(new Locations());
    }

    public void fromCSV(Locations locations) {
        loadLocations(locations);
        loadDirections(locations);
    }

    private void loadLocations(Locations locations) {
        try (BufferedReader reader = new BufferedReader(new FileReader("locations.csv"))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> exits = new HashMap<>();
                locations.put(loc, new Location(loc, description, exits));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDirections(Locations locations) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("directions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = reader.readLine().split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println("Imported direction: " + loc + ": " + direction + ": " + destination);
                locations.get(loc).addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
