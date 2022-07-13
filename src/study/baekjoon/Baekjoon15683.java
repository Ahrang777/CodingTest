package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0

출력1
20
==================
입력2
6 6
0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5

출력2
15
==================
입력3
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1

출력3
6
==================
입력4
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 5 0 0
0 0 5 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1

출력4
2
==================
입력5
1 7
0 1 2 3 4 5 6

출력5
0
==================
입력6
3 7
4 0 0 0 0 0 0
0 0 0 2 0 0 0
0 0 0 0 0 0 4

출력6
0
 */

/**
 * https://www.acmicpc.net/problem/15683
 *
 * 감시
 * 삼성전자 공채
 *
 * 다시 풀기
 */
public class Baekjoon15683 {

    static class CCTV {
        int index, x, y;

        public CCTV(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;

    // U, R, D, L >> 시계 방향 90도
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    // CCTV 마다 감시 방향의 경우
    public static int[][][] ccDir = {
            {{0}},
            {{1}, {2}, {3}, {0}},   // 1번
            {{1, 3}, {0, 2}},   // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},   // 3번
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},   // 4번
            {{0, 1, 2, 3}}  // 5번 CCTV
    };

    public static int minValue = Integer.MAX_VALUE;

    public static ArrayList<CCTV> cctvs = new ArrayList<>();

    public static final int BLANK = 0;
    public static final int WALL = 6;
    public static final int CHECK = -1; // 감시된 영역

    public static void solution(int cnt, int[][] map) {
        if (cnt == cctvs.size()) {
            minValue = Math.min(minValue, getArea(map));
            return;
        }

        // 원본 유지를 위한 map 복사본
        int[][] copiedMap = new int[n][m];

        // 각 cctv 확인
        CCTV now = cctvs.get(cnt);

        // 해당 cctv를 90도씩 회전하며 감시
        // 해당 cctv가 감시할 수 있는 방향의 경우들
        for (int i = 0; i < ccDir[now.index].length; i++) {
            // 원본 유지를 위한 map 복사
            copyMap(copiedMap, map);

            // 현재 상태에서 감시할 수 있는 방향 모두 감시
            for (int j = 0; j < ccDir[now.index][i].length; j++) {
                int dir = ccDir[now.index][i][j];
                observation(now.x, now.y, dir, copiedMap);
            }

            solution(cnt + 1, copiedMap);
        }
    }

    // 해당 방향으로 모두 감시표시
    public static void observation(int x, int y, int dir, int[][] map) {
        while (true) {
            x += dx[dir];
            y += dy[dir];

            if (x >= 0 && x < n && y >= 0 && y < m && map[x][y] != WALL) {
                if (map[x][y] == BLANK) {
                    map[x][y] = CHECK;
                } else {    // 이미 체크했거나 CCTV인 경우
                    continue;
                }
            } else {
                return;
            }
        }
    }

    // map 복사
    public static void copyMap(int[][] copiedMap, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
    }

    // 사각지대 계산
    public static int getArea(int[][] map){
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == BLANK) {
                    total += 1;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(stk.nextToken());
                map[i][j] = a;

                if (a >= 1 && a <= 5) {
                    cctvs.add(new CCTV(a, i, j));
                }
            }
        }

        solution(0, map);
        System.out.println(minValue);
    }
}
