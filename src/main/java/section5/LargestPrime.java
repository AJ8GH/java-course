package section5;

import java.util.*;

public class LargestPrime {

    public static void main(String[] args) {
        System.out.println(getLargestPrime(12));
    }

    public static int getLargestPrime(int number) {
        if (number < 0) return -1;

        List<Integer> factors = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= number; i++) {
            if (number % i == 0) factors.add(i);
        }

        for (Integer factor : factors) {
            if (factor == 2 || factor == 3) {
                primes.add(factor);
            } else {
                for (int j = 2; j <= factor / 2; j++) {
                    if (factor % j == 0) break;
                    if (j >= factor / 2) primes.add(factor);
                }
            }
        }

        if (primes.size() == 0) return -1;
        return Collections.max(primes);
    }
}
