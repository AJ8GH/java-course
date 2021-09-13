package section7.oop2.polymorphism;

public class Ford extends Car {
    public Ford(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Ford engine starting...";
    }

    @Override
    public String accelerate() {
        return "Ford accelerating...";
    }

    @Override
    public String brake() {
        return "Ford braking...";
    }
}
