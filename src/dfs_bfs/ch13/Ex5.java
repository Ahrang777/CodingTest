package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
2
5 6
0 0 1 0

출력
30
30
==========
입력
3
3 4 5
1 0 1 0

출력
35
17
==========
입력
6
1 2 3 4 5 6
2 1 1 1

출력
54
-24
 */

/**
 * https://www.acmicpc.net/problem/14888
 *
 * 연산자 끼워 넣기
 */
public class Ex5 {

    public static int n;
    public static int[] arr = new int[11];

    public static int min = (int) 1e9;
    public static int max = (int) -1e9;

    public static int add, sub, mul, div;

    public static void calculation(int i, int now) {
        if (i == n) {
            min = Math.min(min, now);
            max = Math.max(max, now);
        } else {
            if (add > 0) {
                add--;
                calculation(i + 1, now + arr[i]);
                add++;
            }
            if (sub > 0) {
                sub--;
                calculation(i + 1, now - arr[i]);
                sub++;
            }
            if (mul > 0) {
                mul--;
                calculation(i + 1, now * arr[i]);
                mul++;
            }
            if (div > 0) {
                div--;
                calculation(i + 1, now / arr[i]);
                div++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        n = Integer.parseInt(bf.readLine());

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        add = Integer.parseInt(stk.nextToken());
        sub = Integer.parseInt(stk.nextToken());
        mul = Integer.parseInt(stk.nextToken());
        div = Integer.parseInt(stk.nextToken());

        calculation(1, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }
}
