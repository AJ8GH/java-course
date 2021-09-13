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

        for (String key : languages.keySet()) {
            System.out.println(key + ": " + languages.get(key));
        }
    }
}
