package study.baekjoon;

import java.util.*;
import java.io.*;

/*
입력1
1
1
1 1

출력1
1
==========
입력2
3
3 4 5
2 2

출력2
7
==========
입력3
5
1000000 1000000 1000000 1000000 1000000
5 7

출력3
714290
==========
입력4
5
10 9 10 9 10
7 20

출력4
10
==========
입력5
5
10 9 10 9 10
7 2

출력5
13
==========
 */

/**
 * https://www.acmicpc.net/problem/13458
 *
 * 시험 감독
 * 삼성전자 공채
 */
public class Baekjoon13458 {

    // n: 시험장 개수, b: 한 시험장에서 총감독관 감시 인원, c: 한 시험장에서 부감독관 감시 인원

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int n = Integer.parseInt(bf.readLine());
        int[] test = new int[n];

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            test[i] = Integer.parseInt(stk.nextToken());
        }

        stk = new StringTokenizer(bf.readLine(), " ");

        int b = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());

        // 자료형 주의 long 타입
        // 기본적으로 총감독은 한명씩 넣으니까 n으로 시작
        long cnt = n;
        for (int i = 0; i < n; i++) {
            // 총감독관은 무조건 한명씩 필요
            test[i] -= b;

            // 부감독관으로 나머지 채우기
            if (test[i] > 0) {
                cnt += test[i] / c;

                if (test[i] % c != 0) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
