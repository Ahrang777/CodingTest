package sort.ch14;

import java.io.*;
import java.util.*;

/*
입력
4
5 1 7 9

출력
5
 */

/**
 * https://www.acmicpc.net/problem/18310
 *
 * 안테나
 */
public class Ex2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        ArrayList<Integer> houses = new ArrayList<>();

        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            houses.add(Integer.parseInt(stk.nextToken()));
        }

        Collections.sort(houses);

        // 중간값(median)을 출력
        System.out.println(houses.get((n - 1) / 2));
    }
}
