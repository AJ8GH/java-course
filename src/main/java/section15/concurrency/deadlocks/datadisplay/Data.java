package section15.concurrency.deadlocks.datadisplay;

import org.junit.jupiter.api.condition.DisabledOnJre;

public class Data {
    private Display display;

    public void setDisplay(Display display) {
        this.display = display;
    }

    public synchronized void updateData() {
        System.out.println("Updating data...");
        display.dataChanged();
    }

    public synchronized Object getData() {
        return new Object();
    }
}
