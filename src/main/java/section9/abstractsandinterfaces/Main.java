package section9.abstractsandinterfaces;

public class Main {
    public static void main(String[] args) {
        Player p = new Player("me", 100, 99);
        System.out.println(p.toString());

        Monster m = new Monster("Monny", 20, 57);
        System.out.println(m.toString());
    }
}
