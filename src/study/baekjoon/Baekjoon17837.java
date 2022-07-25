package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17837
 *
 * 새로운 게임2
 * 삼성전자 공채
 * 
 * 다시 풀기
 * 실수 주의
 */
public class Baekjoon17837 {

    static class Piece {
        int x, y, dir;

        public Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static int n, k;
    public static int[][] board = new int[12][12];

    // R, U, L, D
    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {1, 0, -1, 0};

    public static List<Piece> pieces = new ArrayList<>();
    public static int turn = 0;

    public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;

    public static List<Integer>[][] check = new ArrayList[12][12];

    public static void solution() {
        while (turn++ <= 1000) {
            for (int i = 1; i <= k; i++) {

                Piece now = pieces.get(i - 1);
                int dir = now.dir;
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                // 경계 외부 or 파랑
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == BLUE) {

                    // 방향 전환
                    dir = (dir + 2) % 4;
                    now.dir = dir;
                    nx = now.x + dx[dir];
                    ny = now.y + dy[dir];

                    // 방향 바꿔서 이동한게 경계 외부 or 파랑
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == BLUE) {
                        continue;
                    }

                    // 경계 내부, 빨강, 하양인 경우
                    i--;
                    continue;
                } else if (board[nx][ny] == WHITE || board[nx][ny] == RED) {

                    int from = check[now.x][now.y].indexOf(i);
                    int to = check[now.x][now.y].size();

                    List<Integer> move = new ArrayList<>(check[now.x][now.y].subList(from, to));  // 이동할 말들
                    check[now.x][now.y].removeAll(move);    // 이전 위치에서 제거

                    // 빨강일때는 순서 뒤집어서
                    if (board[nx][ny] == RED) {
                        Collections.reverse(move);
                    }

                    // 해당 위치에 쌓는다
                    check[nx][ny].addAll(move);

                    // 말들 위치 반영
                    for (Integer num : move) {
                        pieces.get(num - 1).x = nx;
                        pieces.get(num - 1).y = ny;
                    }
                }

                if (check[nx][ny].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
                check[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= k; i++) {
            stk = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            int dir = Integer.parseInt(stk.nextToken()) - 1;

            if (dir == 1)
                dir = 2;
            else if (dir == 2)
                dir = 1;

            pieces.add(new Piece(x, y, dir));
            check[x][y].add(i);
        }

        solution();
    }
}
