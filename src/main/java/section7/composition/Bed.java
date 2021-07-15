package section7.composition;

public class Bed {
    private String style;
    private int pillows;
    private int height;
    private int quilt;
    private int sheets;

    public Bed(String style, int pillows, int height, int quilt, int sheets) {
        this.style = style;
        this.pillows = pillows;
        this.height = height;
        this.quilt = quilt;
        this.sheets = sheets;
    }

    public void make() {
        System.out.println("Bed is made");
    }

    public String getStyle() {
        return style;
    }

    public int getPillows() {
        return pillows;
    }

    public int getHeight() {
        return height;
    }

    public int getQuilt() {
        return quilt;
    }

    public int getSheets() {
        return sheets;
    }
}
