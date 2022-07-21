package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17142
 *
 * 연구소3
 * 삼성전자 공채
 * 
 * 시간초과 주의
 */
public class Baekjoon17142 {

    static class Virus {
        int time, x, y;

        public Virus(int time, int x, int y) {
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    public static int[][] map;

    // 초기 바이러스 위치
    public static List<Virus> viruses = new ArrayList<>();

    // 활성화 바이러스
    public static Virus[] active;

    public static int result = Integer.MAX_VALUE;
    public static int originEmpty = 0;


    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static final int BLANK = 0;
    public static final int WALL = 1;
    public static final int VIRUS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        active = new Virus[m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(stk.nextToken());

                map[i][j] = x;

                if (x == BLANK) {
                    originEmpty += 1;
                } else if (x == VIRUS) {
                    viruses.add(new Virus(0, i, j));
                }
            }
        }

        if (originEmpty == 0) {
            System.out.println(0);
        } else {
            dfs(0, 0);
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }
    }
    
    // 활성 바이러스 선택
    // dfs, 백트래킹
    // Combination 사용 (Permutation 일 경우 시간초과)
    public static void dfs(int start, int cnt) {
        if (cnt == m) {
            // 확산
            bfs(originEmpty);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            active[cnt] = viruses.get(i);
            dfs(i + 1, cnt + 1);
        }
    }

    // 바이러스 퍼짐
    public static void bfs(int empty) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] infected = new boolean[n][n];

        for (Virus v : active) {
            infected[v.x][v.y] = true;
            q.offer(v);
        }
        
        while (!q.isEmpty()) {
            Virus now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // copiedMap 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                
                // 이미 활성화 바이러스 전파, 벽
                if (infected[nx][ny] || map[nx][ny] == WALL) continue;

                // 다음이 빈칸인 경우
                if (map[nx][ny] == BLANK) {
                    empty--;
                }

                if (empty == 0) {
                    result = Math.min(result, now.time + 1);
                    return;
                }

                infected[nx][ny] = true;
                q.offer(new Virus(now.time + 1, nx, ny));
            }
        }
    }
}
