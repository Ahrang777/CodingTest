package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/2460
 *
 * 지능형 기차2
 */
public class Baekjoon2460 {

    public static int[] out = new int[10];
    public static int[] in = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        for (int i = 0; i < 10; i++) {
            stk = new StringTokenizer(bf.readLine());

            out[i] = Integer.parseInt(stk.nextToken());
            in[i] = Integer.parseInt(stk.nextToken());
        }

        int max = Integer.MIN_VALUE;
        int total = 0;

        for (int i = 0; i < 10; i++) {
            total -= out[i];
            total += in[i];
            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}
