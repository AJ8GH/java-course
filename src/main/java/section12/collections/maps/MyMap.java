package section12.collections.maps;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    public static void main(String[] args) {
        Map<String, String> languages = new HashMap<>();

        languages.put("Java", "A compiled high level, object oriented, platform independent language");
        languages.put("Python", "An interpreted, object oriented, high level programming language with dynamic semantics");
        languages.put("Algol", "An algorithmic language");

//        put returns the previous value of key or null if no value
        System.out.println(languages.put("BASIC", "A beginners all purpose symbolic language"));
        System.out.println(languages.put("Lisp", "Therein lies madness"));

        System.out.println(languages.get("Java"));

        System.out.println(languages.put("Java", "This course is about Java"));
        System.out.println(languages.get("Java"));

        System.out.println(languages.containsKey("Java"));
        System.out.println(languages.containsKey("Rust"));
        System.out.println(languages.containsValue("This course is about Java"));

        System.out.println("==================");

        printMap(languages);

//        Removing from maps
        languages.remove("Lisp");

        languages.remove("Algol", "This is the wrong value for this key");

        System.out.println("===================");
        printMap(languages);

        System.out.println("=============");
        System.out.println(languages.replace("Algol", "New definition"));
        System.out.println(languages.replace("Scala", "This will not be added"));
        printMap(languages);

//        replace with a third param to update only if value and key are what you expect
        languages.replace("Current key", "Old value", "New Value");
    }

    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
