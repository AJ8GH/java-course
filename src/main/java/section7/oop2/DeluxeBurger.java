package section7.oop2;

public class DeluxeBurger extends Hamburger {
    public DeluxeBurger() {
        super("Deluxe", "Wagyu", 15.10, "Brioche");
        super.addHamburgerAddition1("Chips", 2);
        super.addHamburgerAddition2("Drink", 2);
    }

    @Override
    public void addHamburgerAddition1(String name, double price) {
        System.out.println("No additions to deluxe burger allowed");
    }

    @Override
    public void addHamburgerAddition2(String name, double price) {
          System.out.println("No additions to deluxe burger allowed");
    }

    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("No additions to deluxe burger allowed");
    }

    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("No additions to deluxe burger allowed");
    }
}
