package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/20058
 *
 * 마법사 상어와 파이어스톰
 * 삼성전자 공채
 */
public class Baekjoon20058 {

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N, Q, n;
    public static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void process(int l) {
        
        int size = (int) Math.pow(2, l);
        int[][] tmp = new int[n][n];

        // 칸 나누기
        // 시계 방향 90 도 회전
        for (int i = 0; i < n; i += size) {
            for (int j = 0; j < n; j += size) {
                
                // 회전한 내용 임시보관
                tmp = new int[size][size];

                for (int a = 0; a < size; a++) {
                    for (int b = 0; b < size; b++) {
                        tmp[b][size - a - 1] = map[a + i][b + j];
                    }
                }

                for (int a = 0; a < size; a++) {
                    for (int b = 0; b < size; b++) {
                        map[a + i][b + j] = tmp[a][b];
                    }
                }

            }
        }

        // 얼음 양 줄이기
        melt();
    }

    public static void melt() {
        ArrayList<Position> remove = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {

                int cnt = 0;
                if (map[x][y] == 0) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    // 인접한 칸 얼음 있는 경우
                    if (map[nx][ny] > 0) {
                        cnt++;
                    }
                }

                // 인접한 칸에 얼음 있는 칸이 3칸 이상 있는 경우
                if (cnt < 3) {
                    remove.add(new Position(x, y));
                }
            }
        }

        for (Position p : remove) {
            map[p.x][p.y]--;
        }
    }

    public static void getMaxIce() {
        ArrayList<Integer> iceCount = new ArrayList<>();
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        // 덩어리 찾기 >> dfs or bfs
        
        // 해당 덩어리 칸 수 
        
        // max 랑 크기 비교

        int total = 0;
        int cnt = 0;
        int max = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (map[x][y] > 0) {
                    total += map[x][y];
                }

                if (map[x][y] > 0 && !visited[x][y]) {

                    cnt = 1;
                    q.offer(new Position(x, y));
                    visited[x][y] = true;

                    while (!q.isEmpty()) {
                        Position now = q.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = now.x + dx[i];
                            int ny = now.y + dy[i];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                            if (!visited[nx][ny] && map[nx][ny] > 0) {
                                cnt++;
                                visited[nx][ny] = true;
                                q.offer(new Position(nx, ny));
                            }
                        }
                    }

                    max = Math.max(max, cnt);
                }
            }
        }

        System.out.println(total);
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2, N);
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int l = Integer.parseInt(st.nextToken());

            process(l);
        }

        // 남아있는 얼음 합
        // 가장 큰 덩어리의 칸의 개수
        getMaxIce();
    }

    /*
    static int N, Q, land, totalIce;
    static int[][] A;
    static int[] L;
    static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < Q; i++) {
            A = divide(L[i]);
            A = melt();
        }
        findBiggest();
        System.out.println(totalIce);
        System.out.println(land);
    }

    static int[][] divide(int L) {
        int[][] temp = new int[N][N];
        L = (int) Math.pow(2, L);

        for (int i = 0; i < N; i += L) {
            for (int j = 0; j < N; j += L) {
                rotate(i, j, L, temp);
            }
        }
        return temp;
    }

    static void rotate(int r, int c, int L, int[][] temp) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                temp[r + j][c + L - i - 1] = A[r + i][c + j];
            }
        }
    }

    static int[][] melt() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(A[i], N);
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int cnt = 0;
                if (A[r][c] == 0)
                    continue;
                for (int i = 0; i < 4; i++) {
                    int nr = r + deltas[i][0];
                    int nc = c + deltas[i][1];
                    if (isIn(nr, nc) && A[nr][nc] > 0) {
                        cnt++;
                    }
                }
                if (cnt < 3) {
                    temp[r][c]--;
                }
            }
        }
        return temp;
    }

    static void findBiggest() {
        boolean[][] visited = new boolean[N][N];
        Stack<int[]> stack = new Stack<>();
        land = 0;
        totalIce = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] > 0 && !visited[i][j]) {
                    stack.push(new int[] { i, j });
                    visited[i][j] = true;
                    totalIce += A[i][j];
                    int count = 1;
                    while (!stack.isEmpty()) {
                        int[] temp = stack.pop();
                        int r = temp[0];
                        int c = temp[1];

                        for (int k = 0; k < 4; k++) {
                            int nr = r + deltas[k][0];
                            int nc = c + deltas[k][1];

                            if (isIn(nr, nc) && A[nr][nc] > 0 && !visited[nr][nc]) {
                                count++;
                                visited[nr][nc] = true;
                                stack.push(new int[] { nr, nc });
                                totalIce += A[nr][nc];
                            }
                        }
                    }
                    land = Math.max(count, land);

                }
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
     */
}
