package study.baekjoon;

import java.io.*;
import java.util.*;

/*
입력1
3 3
1 1 0
1 1 1
1 0 1
1 1 1

출력1
1
===================
입력2
11 10
7 4 0
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 0 0 1 1 1 1 0 1
1 0 0 1 1 0 0 0 0 1
1 0 1 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 1 1 0 1
1 0 0 0 0 0 1 1 0 1
1 0 0 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1

출력2
57
 */

/**
 * https://www.acmicpc.net/problem/14503
 *
 * 로봇 청소기
 * 삼성전자 공채
 */
public class Baekjoon14503 {

    static class Node {
        int dir, x, y;

        public Node(int dir, int x, int y) {
            this.dir = dir;
            this.x = x;
            this.y = y;
        }
    }

    // n x m, 로봇청소기: (r,c) 방향d
    public static int n, m, r, c, d;

    public static int[][] map = new int[50][50];

    // 북, 동, 남, 서
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static final int BLANK = 0;
    public static final int WALL = 1;
    public static final int CLEAN = 2;
    
    public static Node node;

    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(bf.readLine(), " ");
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        d = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        clean(r, c, d);

        System.out.println(cnt);
        
        /*node = new Node(d, r, c);
        map[r][c] = CLEAN;
        int cnt = 1;

        while (true) {
            int x = node.x;
            int y = node.y;
            int dir = node.dir;

            // 최대 4번 회전
            boolean find = false;
            for (int i = 0; i < 4; i++) {
                dir = rotate(dir);

                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                // 청소하지 않은 빈 공간 발견
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] == BLANK) {
                        find = true;
                        break;
                    }
                }
            }

            // 청소 가능한 칸 발견
            // 청소하기
            if (find) {
                x = x + dx[dir];
                y = y + dy[dir];
                node = new Node(dir, x, y);
                map[x][y] = CLEAN;
                cnt += 1;
            } 
            // 청소 가능한 칸 발견X 후진 or 종료
            else {
                x = x - dx[dir];
                y = y - dy[dir];

                if (x >= 0 && x < n && y >= 0 && y < m) {
                    // 후진 불가능 >> 종료
                    if (map[x][y] == WALL) {
                        break;
                    }
                    // 후진 가능
                    else if (map[x][y] != WALL) {
                        node = new Node(dir, x, y);
                    }
                } else {    // 범위 벗어나는 경우
                    break;
                }
            }
        }

        System.out.println(cnt);*/
    }

    public static void clean(int x, int y, int dir) {
        // 1. 현재 위치를 청소한다.
        if (map[x][y] == BLANK) {
            map[x][y] = CLEAN;
            cnt++;
        }

        // 2. 왼쪽 방향부터 차례로 탐색을 진행한다.
        boolean find = false;
        for (int i = 0; i < 4; i++) {
            // 왼쪽으로 회전
            dir = rotate(dir);
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                // 다음 칸이 아직 청소하지 않은 공간
                if (map[nx][ny] == BLANK) {
                    clean(nx, ny, dir);
                    find = true;
                    break;
                }
            }
        }

        // 네 방향 모두 청소가 되어있거나 벽인 경우
        if (!find) {
            int nx = x - dx[dir];
            int ny = y - dy[dir];

            // 뒤에 벽이 없는 경우 후진
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (map[nx][ny] != WALL) {
                    clean(nx, ny, dir);
                }
            }
        }
    }
    
    // 왼쪽으로 회전
    public static int rotate(int dir){
        if (dir == 0)
            return 3;

        return dir - 1;
    }
}
