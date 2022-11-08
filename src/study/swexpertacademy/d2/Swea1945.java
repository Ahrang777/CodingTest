package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5Pl0Q6ANQDFAUq&categoryId=AV5Pl0Q6ANQDFAUq&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * 간단한 소인수분해
 */
public class Swea1945 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] number = {2, 3, 5, 7, 11};

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] count = new int[number.length];

            for (int i = 0; i < number.length; i++) {
                int target = N;
                while (true) {
                    if (target % number[i] == 0) {
                        count[i]++;
                        target /= number[i];
                    } else {
                        break;
                    }
                }

            }

            System.out.printf("#%d ", tc);
            for (int c : count) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
