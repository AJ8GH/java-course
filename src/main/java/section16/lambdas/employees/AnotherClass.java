package section16.lambdas.employees;

public class AnotherClass {
    UpperConcat uc = (s1, s2) -> {
        System.out.println(getClass().getSimpleName());
        return s1.toUpperCase() + s2.toUpperCase();
    };

    public void concatStrings(UpperConcat uc, String s1, String s2) {
        System.out.println(uc.upperAndConcat(s1, s2));
    }

    public void printValue() {
        int number = 25;
        Runnable r = () -> {
            int n = 0;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(number);
        };

        new Thread(r).start();
    }
}
