package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
5 5
1 2 3 4 5
5 4 3 2 1
2 3 4 5 6
6 5 4 3 2
1 2 1 2 1

출력1
19
=================
입력2
4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5

출력2
20
=================
입력3
4 10
1 2 1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1 2 1
1 2 1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1 2 1

출력3
7
=================
 */

/**
 * https://www.acmicpc.net/problem/14500
 * 
 * 테트로미노
 * 삼성전자 공채
 * 
 * dfs, 백트래킹을 이용하여 연속된 4개 묶음을 찾는 방식으로 해결 >> 회전, 대칭 가능하기때문에 4개 묶음으로 가능
 * 단, ㅗ 모양은 dfs로 해결할 수 없기에 별도로 처리
 */
public class Baekjoon14500 {

    public static int n, m;
    public static int[][] map = new int[500][500];

    public static boolean[][] visited = new boolean[500][500];
    public static int maxValue;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void dfs(int cnt, int sum, int x, int y) {
        if (cnt == 4) {
            maxValue = Math.max(maxValue, sum);

            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(cnt + 1, sum + map[nx][ny], nx, ny);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    // ㅗ 모양을 찾는다. 가운데 있는 좌표를 기준으로 세 방향을 탐색한다.
    public static void another(int x, int y) {
        // 1. 맵의 꼭지점일때는 ㅗ 모양 불가능
        if ((x == 0 || x == n - 1) && (y == 0 || y == m - 1)) return;

        int sum = map[x][y];

        // 2. 맵의 테두리일때는 모양이 하나
        if (x == 0)
            sum += map[x][y - 1] + map[x][y + 1] + map[x + 1][y];
        else if (x == n - 1)
            sum += map[x][y - 1] + map[x][y + 1] + map[x - 1][y];
        else if (y == 0)
            sum += map[x - 1][y] + map[x + 1][y] + map[x][y + 1];
        else if (y == m - 1)
            sum += map[x - 1][y] + map[x + 1][y] + map[x][y - 1];
        else {
            // 3. 맵의 테두리가 아닐 때는 4 개의 모양을 비교
            sum = Math.max(sum, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1]);
            sum = Math.max(sum, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
            sum = Math.max(sum, map[x][y] + map[x][y + 1] + map[x - 1][y] + map[x + 1][y]);
            sum = Math.max(sum, map[x][y] + map[x][y - 1] + map[x - 1][y] + map[x + 1][y]);
        }

        maxValue = Math.max(maxValue, sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 처음 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(0, 0, i, j);
                visited[i][j] = false;
                another(i, j);
            }
        }

        System.out.println(maxValue);
    }
}
