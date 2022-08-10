package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/20057
 *
 * 마법사 상어와 토네이도
 * 삼성전자 공채
 */
public class Baekjoon20057 {

    /*public static int n;

    // n x n 에서 위, 아래, 왼쪽, 오른쪽 +2
    public static int[][] map;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void solution(int x, int y) {
        int moveCnt = 1;    // 처음은 1칸씩 이동 >> 1칸 이동 2번 하면 2칸으로,, 2칸 이동 2번하면 3칸으로 ,,
        int cnt = 0;    // 동일한 칸 이동 몇번 하는지 카운트
        int d = 1;  // 처음은 왼쪽방향

        while (true) {

            if (cnt++ < 2) {

                for (int i = 0; i < moveCnt; i++) {
                    if (x == 2 && y == 2) {
                        return;
                    }

                    // y 위치
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    // y의 모래 총합
                    int value = map[nx][ny];
                    int total = 0;

                    // 왼쪽 방향 기준 >> 현재 방향 기준으로 
                    int down = (d + 1) % 4;
                    int up = (d + 3) % 4;

                    // map[x][y] 에 있던 모래 분배
                    total += (int) (value * 0.01) * 2;
                    total += (int) (value * 0.07) * 2;
                    total += (int) (value * 0.02) * 2;
                    total += (int) (value * 0.1) * 2;
                    total += (int) (value * 0.05);

                    map[x + dx[down]][y + dy[down]] += (int) (value * 0.01);
                    map[x + dx[up]][y + dy[up]] += (int) (value * 0.01);
                    map[nx + dx[down]][ny + dy[down]] += (int) (value * 0.07);
                    map[nx + dx[down] * 2][ny + dy[down] * 2] += (int) (value * 0.02);
                    map[nx + dx[up]][ny + dy[up]] += (int) (value * 0.07);
                    map[nx + dx[up] * 2][ny + dy[up] * 2] += (int) (value * 0.02);

                    map[nx + dx[d] + dx[down]][ny + dy[d] + dy[down]] += (int) (value * 0.1);
                    map[nx + dx[d] + dx[up]][ny + dy[d] + dy[up]] += (int) (value * 0.1);
                    map[nx + dx[d] * 2][ny + dy[d] * 2] += (int) (value * 0.05);

                    map[nx + dx[d]][ny + dy[d]] += (value - total);
                    map[nx][ny] = 0;

                    x = nx;
                    y = ny;
                }
                
                // 이동칸 만큼 이동했으면 방향 전환
                d = (d + 1) % 4;
            }

            // 같은 칸수 이동을 2번 했을 경우 >> ex) 1칸 이동 2번
            if (cnt == 2) {
                cnt = 0;
                moveCnt++;
            }
        }
    }

    public static int getSum() {
        int result = 0;

        // 처음 두줄
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n + 4; j++) {
                result += map[i][j];
            }
        }

        // 마지막 두줄
        for (int i = n + 2; i < n + 4; i++) {
            for (int j = 0; j < n + 4; j++) {
                result += map[i][j];
            }
        }

        // 옆 부분
        for (int i = 2; i < n + 2; i++) {
            for (int j = 0; j < 2; j++) {
                result += map[i][j];
            }
        }

        for (int i = 2; i < n + 2; i++) {
            for (int j = n + 2; j < n + 4; j++) {
                result += map[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n + 4][n + 4];

        for (int i = 2; i < n + 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 2; j < n + 2; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(n / 2 + 2, n / 2 + 2);
        System.out.println(getSum());
    }*/

    static int N, curX, curY, curDir, answer;
    static int[][] map;
    //서 남 동 북
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int[][][] direct = {
            // 모래 위치 기준으로 방향에 따라서 나눠주기
            //서
            { { 1, -1, 2, -2, 0, 1, -1, 1, -1, 0 }, { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } },
            // 남
            { { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } },
            // 동
            { { 1, -1, 2, -2, 0, 1, -1, 1, -1, 0 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } },
            // 북
            { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }
    };
    static int[] percent = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        answer = 0;
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        curX = N/2;
        curY = N/2;

        int idx = -1;
        int len = 0;
        curDir = 0;
        while(true) {
            if(++idx%2 == 0) {
                len ++;
            }
            for(int i = 0 ; i < len ; i++) {
                sandMove();
                windMove();
            }

            curDir = (curDir+1) % 4;

            if(len == N) {
                break;
            }
        }
        System.out.println(answer);
    }

    private static void windMove() {
        curX = curX + dx[curDir];
        curY = curY + dy[curDir];
    }

    private static void sandMove() {
        int nx = curX + dx[curDir];
        int ny = curY + dy[curDir];
        int sandX = nx;
        int sandY = ny;
        if(0> nx || nx>= N || 0>ny || ny>=N) return;

        int sand = map[nx][ny];

        if(sand == 0) return;

        for(int i = 0 ; i < 9 ; i++) {
            nx = sandX + direct[curDir][0][i];
            ny = sandY + direct[curDir][1][i];

            int amount = map[sandX][sandY] * percent[i] / 100;
            sand -= amount;

            if(0> nx || nx>= N || 0>ny || ny>=N) {
                answer += amount;
            }
            else map[nx][ny] += amount;
        }

        // 알파 계산
        nx = sandX + direct[curDir][0][9];
        ny = sandY + direct[curDir][1][9];

        if(0> nx || nx>= N || 0>ny || ny>=N) {
            answer += sand;
        }
        else map[nx][ny] += sand;

        map[sandX][sandY] = 0;
    }
}
