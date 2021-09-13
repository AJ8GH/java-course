package section7.oop2.polymorphism;

public class Holden extends Car {
    public Holden(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Holden engine starting...";
    }

    @Override
    public String accelerate() {
        return "Holden accelerating...";
    }

    @Override
    public String brake() {
        return "Holden braking...";
    }
}
