package section6;

public class Cuboid extends Rectangle {
    private final double height;

    public Cuboid(double height, double width, double length) {
        super(width, length);
        this.height = (height < 0) ? 0 : height;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return getArea() * height;
    }
}
