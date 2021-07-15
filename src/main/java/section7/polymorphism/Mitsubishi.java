package section7.polymorphism;

public class Mitsubishi extends Car {
    public Mitsubishi(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Mitsubishi engine starting...";
    }

    @Override
    public String accelerate() {
        return "Mitsubishi accelerating...";
    }

    @Override
    public String brake() {
        return "Mitsubishi braking...";
    }
}
