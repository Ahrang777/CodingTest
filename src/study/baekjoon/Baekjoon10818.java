package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/10818
 * 
 * 최소, 최대
 */
public class Baekjoon10818 {

    public static int n;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int n = Integer.parseInt(bf.readLine());
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int[] arr = new int[n];
        stk = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        System.out.println(min + " " + max);
    }


}
