package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17144
 *
 * 미세먼지 안녕!
 * 삼성전자 공채
 */

public class Baekjoon17144 {

    public static int r, c, t;

    public static int[][] map = new int[50][50];

    // U, L, D, R
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static ArrayList<Integer> airCleanerRows = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        t = Integer.parseInt(stk.nextToken());


        for (int i = 0; i < r; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < c; j++) {
                int x = Integer.parseInt(stk.nextToken());

                map[i][j] = x;

                // 위에서부터 검사하기에 앞부분이 윗 공기청정기
                // 뒤에 2개가 아래 공기청정기
                if (x == -1) {
                    airCleanerRows.add(i);
                }
            }
        }

        solution();

    }

    public static int getDust() {
        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    sum += map[i][j];
                }
            }
        }

        return sum;
    }

    public static void solution() {
        // T초동안
        for (int i = 0; i < t; i++) {
            // 미세먼지 확산
            map = spread();

            // 공기청정기 작동
            clean();
        }

        System.out.println(getDust());
    }

    public static int[][] spread() {
        // 나중에 한번에 더할 값
        int[][] tmp = new int[r][c];

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (map[x][y] == -1) {
                    tmp[x][y] = -1;
                    continue;
                }

                // 해당 위치에 다른 먼지에서 확산된 먼지가 있을 수 있으니 += 으로 처리
                tmp[x][y] += map[x][y];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if (map[nx][ny] == -1) continue;

                    tmp[nx][ny] += (map[x][y] / 5);
                    tmp[x][y] -= (map[x][y] / 5);
                }
            }
        }

        return tmp;
    }

    public static void clean() {
        
        // 윗 공기청정기: 반시계 방향
        int top = airCleanerRows.get(0);

        // 왼쪽면
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }

        // 윗면
        for (int y = 0; y < c - 1; y++) {
            map[0][y] = map[0][y + 1];
        }

        // 오른쪽 면
        for (int x = 0; x < top; x++) {
            map[x][c - 1] = map[x + 1][c - 1];
        }

        // 아랫면
        for (int y = c - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }

        map[top][1] = 0;

        // 아래 공기청정기: 시계방향
        int bottom = airCleanerRows.get(1);

        // 왼쪽 면
        for (int x = bottom + 1; x < r - 1; x++) {
            map[x][0] = map[x + 1][0];
        }

        // 아랫면
        for (int y = 0; y < c - 1; y++) {
            map[r - 1][y] = map[r - 1][y + 1];
        }

        // 오른쪽 면
        for (int x = r - 1; x > bottom; x--) {
            map[x][c - 1] = map[x - 1][c - 1];
        }

        // 윗 면
        for (int y = c - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }

        map[bottom][1] = 0;
    }
}
