package session5;

public class Switch {
    public static void main(String[] args) {
        int switchValue = 3;

        switch (switchValue) {
            case 1:
                System.out.println("value is 1");
                break;
            case 2:
                System.out.println("value is 2");
                break;
            case 3: case 4: case 5:
                System.out.println("value was 3, 4 or 5");
                break;
        }
    }
}
