package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 약수 구하기(최적화)
 */
public class Divisor {
    public static void main(String[] args) {
        int n = 100;
        int sqrt = (int) Math.sqrt(n);
        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if ((n / i) != i) {
                    divisors.add(n / i);
                }
            }
        }

        Collections.sort(divisors);
        System.out.printf("%d 약수의 개수: %d\n", n, divisors.size());
        for (int divisor : divisors) {
            System.out.print(divisor + " ");
        }
    }
}
