package section12.collections.theatre;

public class Seat implements Comparable<Seat> {
    private final String seatNumber;
    private final double price;
    private boolean reserved = false;

    public Seat(String seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return reserved;
    }

    @Override
    public int compareTo(Seat seat) {
        return seatNumber.compareToIgnoreCase(seat.getSeatNumber());
    }

    public boolean reserve() {
        if (!isReserved()) {
            System.out.println("Seat " + seatNumber + " reserved");
            return this.reserved = true;
        } else {
            System.out.println("Seat " + seatNumber + " is already taken");
            return false;
        }
    }

    public boolean cancel() {
        if (isReserved()) {
            System.out.println("Seat " + seatNumber + " has been cancelled");
            reserved = false;
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " not reserved");
            return false;
        }
    }
}
