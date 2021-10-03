package section12.collections.maps.adventure;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DirectionGetter {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getDirectionFromPlayer(Map<String, Integer> exits) {
        System.out.println("Available exits are ");
        for (String exit : exits.keySet()) {
            System.out.println(exit + ", ");
        }
        System.out.println();

        String direction = "";
        String[] input = SCANNER.nextLine().toUpperCase().split(" ");
        if (Set.of(input).contains("WEST") || Set.of(input).contains("W")) {
            direction = "W";
        } else if (Set.of(input).contains("NORTH") || Set.of(input).contains("N")) {
            direction = "N";
        } else if (Set.of(input).contains("SOUTH") || Set.of(input).contains("S")) {
            direction = "S";
        } else if (Set.of(input).contains("EAST") || Set.of(input).contains("E")) {
            direction = "E";
        } else if (Set.of(input).contains("QUIT") || Set.of(input).contains("Q")) {
            direction = "Q";
        }
        return direction;
    }
}
