package section12.collections.theatre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Lyceum", 8, 12);
        theatre.getSeats();
        theatre.reserveSeat("H11");
        theatre.reserveSeat("H11");
        theatre.reserveSeat("B06");
        theatre.reserveSeat("zkos");
        theatre.cancelSeat("A00");
        theatre.cancelSeat("H11");
        theatre.cancelSeat("H11");
        theatre.reserveSeat("H11");
        theatre.reserveSeat("B12");
        theatre.reserveSeat("B11");
        theatre.reserveSeat("H12");
        theatre.reserveSeat("B13");

//        Shallow copy
        List<Seat> copy = new ArrayList<>(theatre.getSeats());

        printList(copy);

        Seat copySeat = copy.get(0);
        System.out.println(copySeat.getSeatNumber());
        copySeat.reserve();
        theatre.reserveSeat("A01");

//        shuffle
        Collections.shuffle(copy);
        printList(copy);

//        min and max (works on shuffled collection)
        System.out.println(Collections.min(copy).getSeatNumber());
        System.out.println(Collections.max(copy).getSeatNumber());

//        swap (in bubble sort)
        bubbleSort(copy);

//        reserve
        Collections.reverse(copy);
        printList(copy);

//        sort with comparator
        Collections.shuffle(copy);
        printList(copy);

        Collections.sort(copy, theatre.PRICE_ORDER);
        printList(copy);

        Collections.sort(copy);
        printList(copy);
        Collections.sort(copy, theatre.PRICE_ORDER);
        printList(copy);
    }

    private static void printList(List<Seat> list) {
        for (Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " : £" + seat.getPrice() + " | ");
        }
        System.out.println();
        System.out.println("==============================================================================================================================================================================================================================================================================");
    }

    private static void bubbleSort(List<? extends Seat> list) {
        int swaps = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, i, j);
                    swaps += 1;
                }
            }
        }
        System.out.println("No. of swaps: " + swaps);
        printList((List<Seat>) list);
    }
}
