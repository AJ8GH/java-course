package section7.oop2.polymorphism;

public class Car {
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.name = name;
    }

    public String startEngine() {
        return name + " engine starting...";
    }

    public String accelerate() {
        return name + " accelerating...";
    }

    public String brake() {
        return name + " braking...";
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }
}
