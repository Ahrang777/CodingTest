package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/3460
 *
 * 이진수
 */
public class Baekjoon3460 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bf.readLine());

        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(bf.readLine());

            // 이진수 구하기

            ArrayList<Integer> arr = new ArrayList<>();

            int i = 0;
            while (n != 0) {
                if (n % 2 == 1) {
                    arr.add(i);
                }

                n /= 2;
                i += 1;
            }

            for (int index : arr) {
                System.out.print(index + " ");
            }
        }
    }
}
