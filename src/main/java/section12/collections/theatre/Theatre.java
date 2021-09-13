package section12.collections.theatre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;

public class Theatre {
    private final String theatreName;
    private final List<Seat> seats = new ArrayList<>();

    public static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            return Double.compare(seat1.getPrice(), seat2.getPrice());
        }
    };

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.0;
                if (row < 'D' && seatNum >= 4 && seatNum <= 9) price = 14.0;
                if (row > 'F' || seatNum < 4 || seatNum > 9) price = 7.0;
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public List<Seat> getSeats() {
        for (Seat seat : seats) System.out.println(seat.getSeatNumber());
        return seats;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat seat = findSeat(seatNumber);
        if (seat == null) {
            System.out.println("There is no seat " + seatNumber);
            return false;
        } else {
            return seat.reserve();
        }
    }

    public boolean cancelSeat(String seatNumber) {
        Seat seat = findSeat(seatNumber);
        if (seat == null) {
            System.out.println("There is no seat " + seatNumber);
            return false;
        } else {
            return seat.cancel();
        }
    }

    private Seat findSeat(String seatNumber) {
//            Seat requestedSeat = new Seat(seatNumber);
//            int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
//            if (foundSeat >= 0) return seats.get(foundSeat);
//            return null;
        int low = 0;
        int high = seats.size()-1;

        while (low <= high) {
            System.out.print(".");
            int mid = (low + high) >>> 1;
            Seat midVal = seats.get(mid);
            int cmp = midVal.getSeatNumber().compareTo(seatNumber);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
               return seats.get(mid);
        }
        System.out.println("There is no seat " + seatNumber);
        return null;
//
//        for (Seat seat : seats) {
//            System.out.print(".");
//            if (seat.getSeatNumber().equals(seatNumber)) return seat;
//        }
//        return null;
    }
}
