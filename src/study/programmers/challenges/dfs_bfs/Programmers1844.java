package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 *
 * 게임 맵 최단거리
 */
public class Programmers1844 {

    static class Position {
        int x, y, cnt;

        public Position(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static int solution(int[][] maps) {
        // (0, 0) ~ (n-1, m-1)
        int n = maps.length;
        int m = maps[0].length;

        int answer = bfs(maps, n, m);

        return answer;
    }

    public static int bfs(int[][] maps, int n, int m) {
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(0, 0, 1));
        maps[0][0] = 2;

        while (!q.isEmpty()){
            Position now = q.poll();

            if (now.x == n-1 && now.y == m-1){
                return now.cnt;
            }

            for (int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (maps[nx][ny] == 1){
                    q.offer(new Position(nx, ny, now.cnt + 1));
                    maps[nx][ny] = 2;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][][] maps = {
                {
                        {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}
                },
                {
                        {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}
                }
        };

        for (int i = 0; i < maps.length; i++) {
            System.out.println(solution(maps[i]));
        }
    }
}
