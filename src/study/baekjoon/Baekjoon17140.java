package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17140
 *
 * 이차원 배열과 연산
 * 삼성전자 공채
 * 
 * 다시 풀기
 */

public class Baekjoon17140 {

    static class Pair implements Comparable<Pair> {
        int index, cnt;

        public Pair(int index, int cnt) {
            this.index = index;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.cnt != other.cnt) {
                return Integer.compare(this.cnt, other.cnt);
            }

            return Integer.compare(this.index, other.index);
        }
    }

    public static int r, c, k;
    public static int[][] map;
    public static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        map = new int[3][3];

        for (int i = 0; i < 3; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        solution();
    }

    public static void solution() {

        while (true) {
            if (time > 100) {
                time = -1;
                break;
            }

            if (r - 1 < map.length && c - 1 < map[0].length) {
                if (map[r - 1][c - 1] == k) {
                    break;
                }
            }

            // R 연산
            if (map.length >= map[0].length) {
                opR();
            }
            // C 연산
            else {
                opC();
            }

            time += 1;
        }

        System.out.println(time);
    }

    // R 연산
    // 행 갯수 증가
    public static void opR() {
        int[][] tmp = new int[100][100];
        int max = Integer.MIN_VALUE;

        // 연산
        for (int i = 0; i < map.length; i++) {
            int[] count = new int[101];
            ArrayList<Pair> arr = new ArrayList<>();

            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {
                    count[map[i][j]] += 1;
                }
            }

            for (int j = 1; j < count.length; j++) {
                if (count[j] != 0) {
                    arr.add(new Pair(j, count[j]));
                }
            }

            Collections.sort(arr);

            int c = 0;
            for (Pair p : arr) {
                if (c >= 100)    break;

                int index = p.index;
                int cnt = p.cnt;

                tmp[i][c] = index;
                tmp[i][c + 1] = cnt;

                c += 2;
            }
            max = Math.max(max, c);
        }

        // 나머지 행 뒤에 0
        if (max > 100) max = 100;

        map = new int[map.length][max];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    // C 연산
    // 열 갯수 증가
    public static void opC() {
        int[][] tmp = new int[100][100];
        int max = Integer.MIN_VALUE;

        // 연산
        for (int i = 0; i < map[0].length; i++) {
            int[] count = new int[101];
            ArrayList<Pair> arr = new ArrayList<>();

            for (int j = 0; j < map.length; j++) {
                if (map[j][i] != 0) {
                    count[map[j][i]] += 1;
                }
            }

            for (int j = 1; j < count.length; j++) {
                if (count[j] != 0) {
                    arr.add(new Pair(j, count[j]));
                }
            }

            Collections.sort(arr);

            int r = 0;
            for (Pair p : arr) {
                if (r >= 100)   break;

                int index = p.index;
                int cnt = p.cnt;

                tmp[r][i] = index;
                tmp[r + 1][i] = cnt;

                r += 2;
            }
            max = Math.max(max, r);
        }

        // 나머지 행 뒤에 0
        if (max > 100) max = 100;

        map = new int[max][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }
}
