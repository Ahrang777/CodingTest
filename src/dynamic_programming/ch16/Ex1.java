package dynamic_programming.ch16;

import java.io.*;
import java.util.*;

/*
입력
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2

출력
19
16
 */
public class Ex1 {

    public static int t, n, m;
    public static int[][] arr = new int[20][20];
    public static int[][] dp = new int[20][20];
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        
        t = Integer.parseInt(bf.readLine());

        ArrayList<Integer> results = new ArrayList<>();

        //test case 반복
        for (int k = 0; k < t; k++) {

            stk = new StringTokenizer(bf.readLine()," ");

            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(bf.readLine(), " ");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = arr[i][j];
                }
            }

            // 다이나믹 프로그래밍 진행
            for (int j = 1; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int leftUp, leftDown, left;
                    // 왼쪽 위에서 오는 경우
                    // 0번 행의 경우 왼쪽 위에서 올 수 없기에 leftUp = 0
                    if (i == 0) leftUp = 0;
                    else leftUp = dp[i - 1][j - 1];

                    // 왼쪽 아래에서 오는 경우
                    // n-1번 행의 경우 왼쪽 아래에서 올 수 없기에 leftDown = 0
                    if (i == n - 1) leftDown = 0;
                    else leftDown = dp[i + 1][j - 1];

                    // 왼쪽에서 오는 경우
                    left = dp[i][j - 1];
                    dp[i][j] = dp[i][j] + Math.max(leftUp, Math.max(leftDown, left));
                }
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, dp[i][m - 1]);
            }

            results.add(result);
        }

        for (int result : results) {
            System.out.println(result);
        }
    }
}
