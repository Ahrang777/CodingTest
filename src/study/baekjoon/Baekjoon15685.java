package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
3
3 3 0 1
4 2 1 3
4 2 2 1

출력1
4
===============
입력2
4
3 3 0 1
4 2 1 3
4 2 2 1
2 7 3 4

출력2
11
===============
입력3
10
5 5 0 0
5 6 0 0
5 7 0 0
5 8 0 0
5 9 0 0
6 5 0 0
6 6 0 0
6 7 0 0
6 8 0 0
6 9 0 0

출력3
8
===============
입력4
4
50 50 0 10
50 50 1 10
50 50 2 10
50 50 3 10

출력4
1992
 */

/**
 * https://www.acmicpc.net/problem/15685
 *
 * 드래곤 커브
 * 삼성전자 공채
 *
 * 다시풀기 (2)
 *
 * 괜히 어렵게 생각하지 말자
 */
public class Baekjoon15685 {

    // x 축 ->, y 축 ↓ 이 양의 방향
    // 오른쪽(x 증가), 위(y 감소), 왼쪽(x 감소), 아래(y 증가)
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static boolean[][] map = new boolean[101][101];
    public static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int g = Integer.parseInt(stk.nextToken());

            dragonCurve(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    result += 1;
                }
            }
        }

        System.out.println(result);
    }

    public static void dragonCurve(int x, int y, int d, int g) {
        ArrayList<Integer> dList = new ArrayList<>();
        dList.add(d);

        // 이전 세대 방향들 역순으로 돌면서 다음 방향 정함
        // 방향을 180도 돌리고 90도 돌린다 == 반시계 90도
        for (int i = 1; i <= g; i++) {
            for (int j = dList.size() - 1; j >= 0; j--) {
                dList.add((dList.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;

        for (Integer dir : dList) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }

}
