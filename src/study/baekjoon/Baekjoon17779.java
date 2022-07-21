package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17779
 *
 * 게리맨더링2
 * 삼성전자 공채
 * 
 * 다시 풀기
 */
public class Baekjoon17779 {

    public static int n;
    public static int[][] people;

    public static int minValue = Integer.MAX_VALUE;
    public static int total = 0;

    public static void solution() {

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        // 좌우
                        if (y - d1 < 0 || y + d2 >= n) continue;
                        // 상하
                        if (x < 0 || x + d1 + d2 >= n) continue;

                        process(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(minValue);
    }

    public static void process(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[n][n];

        // 경계선 세팅
        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int[] peopleSum = new int[5];

        // 1 구역 인구수
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                peopleSum[0] += people[i][j];
            }
        }

        // 2 구역 인구수
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (border[i][j]) break;
                peopleSum[1] += people[i][j];
            }
        }

        // 3 구역 인구수
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                peopleSum[2] += people[i][j];
            }
        }

        // 4 구역 인구수
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                peopleSum[3] += people[i][j];
            }
        }

        // 5 구역 인구수
        peopleSum[4] = total;

        for (int i = 0; i < 4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        // 정렬
        Arrays.sort(peopleSum);

        // 최대 - 최소
        minValue = Math.min(minValue, peopleSum[4] - peopleSum[0]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(bf.readLine());
        people = new int[n][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                people[i][j] = Integer.parseInt(stk.nextToken());
                total += people[i][j];
            }
        }

        solution();
    }
}
