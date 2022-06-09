package dynamic_programming.ch16;

import java.io.*;
import java.util.*;

/*
입력
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5

출력
30
 */

/**
 * https://www.acmicpc.net/problem/1932
 *
 * 정수 삼각형
 */
public class Ex2 {

    public static int n;
    public static int[][] dp = new int[500][500];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int upLeft, up;

                // 왼쪽 위에서 내려오는 경우
                if (j == 0) upLeft = 0;
                else upLeft = dp[i - 1][j - 1];

                // 바로 위에서 내려오는 경우
                if (j == i) up = 0;
                else up = dp[i - 1][j];

                // 최대 합을 저장
                dp[i][j] = dp[i][j] + Math.max(upLeft, up);
            }
        }

        int result = 0;
        for (int j = 0; j < n; j++) {
            result = Math.max(result, dp[n - 1][j]);
        }
        System.out.println(result);
    }
}
