package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
2 0 3

출력1
0
===========
입력2
2 1 3
1 1

출력2
1
===========
입력3
5 5 6
1 1
3 2
2 3
5 1
5 4

출력3
3
===========
입력4
6 5 6
1 1
3 2
1 3
2 5
5 5

출력4
3
===========
입력5
5 8 6
1 1
2 2
3 3
4 4
3 1
4 2
5 3
6 4

출력5
-1
===========
입력6
5 12 6
1 1
1 3
2 2
2 4
3 1
3 3
4 2
4 4
5 1
5 3
6 2
6 4

출력6
-1
===========
입력7
5 6 6
1 1
3 1
5 2
4 3
2 3
1 4

출력7
2
 */

/**
 * https://www.acmicpc.net/problem/15684
 *
 * 사다리 조작
 * 삼성전자 공채
 * 
 * 다시 풀기
 * 참고: https://velog.io/@jh5253/%EB%B0%B1%EC%A4%80-15684-%EC%82%AC%EB%8B%A4%EB%A6%AC-%EC%A1%B0%EC%9E%91-Java%EC%9E%90%EB%B0%94
 */
public class Baekjoon15684 {

    // 세로선 개수, 현재 가로선 개수, 각 세로선마다 가능한 가로선 수
    // h x n
    public static int n, m, h;
    public static int[][] map;

    public static int result = -1;

    public static final int RIGHT = 1;
    public static final int LEFT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        h = Integer.parseInt(stk.nextToken());

        map = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");

            // a번 가로선, b번 세로선 >> b번, b+1번 세로선 사이 a번 가로선
            // 가로선 시작점 (a,b)
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            // 시작점 위치에선 오른쪽으로 이동하는 1
            // 시작점 오른쪽 점에선 왼쪽으로 이동하는 -1
            map[a][b] = RIGHT;
            map[a][b + 1] = LEFT;
        }

        for (int i = 0; i < 4; i++) {
            if (dfs(0, i)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    // i -> i 로 가는지 확인
    public static boolean check() {

        boolean flag = true;

        for (int i = 1; i <= n; i++) {
            int x = 1;
            int y = i;

            while (x <= h) {
                // RIGHT, LEFT를 만난경우 해당 방향으로 이동한 후 아래로 내려감
                // RIGHT, LEFT를 만나지 않은 경우 바로 내려감
                if (map[x][y] == RIGHT) {
                    y += 1;
                } else if (map[x][y] == LEFT) {
                    y -= 1;
                }
                x += 1;
            }
            if (y != i) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static boolean dfs(int cnt, int dest) {
        if (cnt == dest) {

            if (check()) {
                return true;
            }

            return false;
        }

        for (int i = 1; i <= h; i++) {
            // 세로선 +1 도 체크하기에 <= n이 아닌 < n
            for (int j = 1; j < n; j++) {
                // 가로선은 연속되거나 겹칠 수 없다.
                if (map[i][j] != 0 || map[i][j + 1] > 0) continue;

                map[i][j] = RIGHT;
                map[i][j + 1] = LEFT;

                if (dfs(cnt + 1, dest))
                    return true;
//                dfs(cnt + 1, dest);

                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }

        return false;
    }
}
