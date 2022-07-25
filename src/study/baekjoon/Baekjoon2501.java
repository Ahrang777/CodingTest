package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/2501
 *
 * 약수 구하기
 */
public class Baekjoon2501 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();

        // 약수 구하기
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                arr.add(i);
            }
        }

        // 정렬
        Collections.sort(arr);

        // 약수 갯수 < k == 0
        if (arr.size() < k)
            System.out.println(0);
        else
            System.out.println(arr.get(k - 1));
    }
}
