package section15.concurrency.deadlocks.datadisplay;

public class Display {
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public synchronized void dataChanged() {
        System.out.println("I'm doing somthing because the data changed...");
    }

    public synchronized void updateDisplay() {
        System.out.println("Updating display...");
        Object o = data.getData();
    }
}
