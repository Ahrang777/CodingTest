package dynamic_programming.ch16;

/*
입력
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

출력
45
========
입력
10
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10

출력
55
========
입력
10
5 10
5 9
5 8
5 7
5 6
5 10
5 9
5 8
5 7
5 6

출력
20
========
입력
10
5 50
4 40
3 30
2 20
1 10
1 10
2 20
3 30
4 40
5 50

출력
90
 */

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/14501
 *
 * 퇴사
 */
public class Ex3 {

    public static int n;
    public static int[] t = new int[15];
    public static int[] p = new int[15];
    public static int[] dp = new int[16];
    public static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            t[i] = Integer.parseInt(stk.nextToken());
            p[i] = Integer.parseInt(stk.nextToken());
        }

        // 문제의 예시를 보면 1, 4, 5 일차가 최대값인데 계산 과정이 1일차 상담 금액 + 4일부터의 최대 상담 금액 >> p[i] + dp[t[i] + i]
        // dp[i] 는 i번째 날부터 마지막 날까지 낼 수 있는 최대 이익
        // 배열을 뒤에서부터 거꾸로 확인
        for (int i = n - 1; i >= 0; i--) {
            int time = t[i] + i;
            // 상담이 기간 안에 끝나는 경우
            if (time <= n) {
                // 점화식에 맞게, 현재까지의 최고 이익 계산
                dp[i] = Math.max(p[i] + dp[time], max);
                max = dp[i];
            }
            // 상담이 기간을 벗어나는 경우
            else dp[i] = max;
        }

        System.out.println(max);
    }
}
