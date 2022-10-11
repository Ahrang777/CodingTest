package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
10101111
01111101
11001110
00000010
2
3 -1
1 1

출력1
7
============
입력2
11111111
11111111
11111111
11111111
3
1 1
2 1
3 1

출력2
15
============
입력3
10001011
10000011
01011011
00111101
5
1 1
2 1
3 1
4 1
1 -1

출력3
6
============
입력4
10010011
01010011
11100011
01010101
8
1 1
2 1
3 1
4 1
1 -1
2 -1
3 -1
4 -1

출력4
5
============
 */

/**
 * https://www.acmicpc.net/problem/14891
 * 
 * 톱니바퀴
 * 삼성전자 공채
 *
 * 참고: https://bcp0109.tistory.com/33
 * 다시 풀기 (2)
 */
public class Baekjoon14891 {

    public static int[][] wheel = new int[4][8];
    public static int k;

    // N극, S극
    public static final int N = 0;
    public static final int S = 1;

    // R: 시계 방향, L: 반시계 방향
    public static final int R = 1;
    public static final int L = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        
        for (int i = 0; i < 4; i++) {
            String str = bf.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(bf.readLine());
        
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int index = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());

            // 톱니 번호 1~4, 인덱스 0~3
            solution(index - 1, dir);
        }

        // 점수 계산
        int score = 0;
        for (int i = 0; i < 4; i++) {

            /*
            if (wheel[i][0] == 1) {
                answer += Math.pow(2, i);
            }
             */
            //////// 이부분 기억하기
            score += wheel[i][0] * (1 << i);
        }

        System.out.println(score);
    }

    public static void solution(int index, int dir) {

        // 왼쪽 확인
        left(index - 1, -dir);
        
        // 오른쪽 확인
        right(index + 1, -dir);
        rotate(index, dir);

    }

    public static void left(int index, int dir) {
        if (index < 0) return;

        if (wheel[index][2] != wheel[index + 1][6]) {
            left(index - 1, -dir);
            rotate(index, dir);
        }
    }

    public static void right(int index, int dir) {
        if (index > 3) return;

        if (wheel[index][6] != wheel[index - 1][2]) {
            right(index + 1, -dir);
            rotate(index, dir);
        }
    }

    // 톱니 번호, 회전 방향
    public static void rotate(int index, int dir) {
        // 시계 방향 >> 오른쪽으로 한 칸씩 이동
        if (dir == R) {
            int tmp = wheel[index][7];
            for (int i = 7; i > 0; i--) {
                wheel[index][i] = wheel[index][i - 1];
            }
            wheel[index][0] = tmp;
        } else {    // 반시계 방향 >> 왼쪽으로 한 칸씩 이동
            int tmp = wheel[index][0];
            for (int i = 0; i < 7; i++) {
                wheel[index][i] = wheel[index][i + 1];
            }
            wheel[index][7] = tmp;
        }
    }
}
