package study.swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWQmA4uK8ygDFAXj&categoryId=AWQmA4uK8ygDFAXj&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * 재미있는 오셀로 게임
 */
public class SWEA4615 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            // 초기 흑백
            map[N / 2 - 1][N / 2 - 1] = 2;
            map[N / 2 - 1][N / 2] = 1;
            map[N / 2][N / 2 - 1] = 1;
            map[N / 2][N / 2] = 2;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());

                map[x][y] = color;
                game(x, y, color);
            }

            int black = 0, white = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) black++;
                    else if (map[i][j] == 2) white++;
                }
            }

            sb.append("#" + tc + " " + black + " " + white + "\n");
        }
        System.out.println(sb);
    }

    public static void game(int x, int y, int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < N; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny] == 0) break;
                    if (map[nx][ny] == color) {
                        changeColor(nx, ny, color, i, j);
                        break;
                    }
                }
            }
        }
    }

    public static void changeColor(int x, int y, int color, int dir, int cnt) {
        for (int i = 1; i <= cnt; i++) {
            map[x][y] = color;
            x -= dx[dir];
            y -= dy[dir];
        }
    }


    /*
    private static int T, res;
    private static int M;
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static int b;
    private static int w;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            w = 0;
            b = 0;

            // 초기화(가운데 배치)
            map[N / 2 - 1][N / 2 - 1] = 2;// 백돌
            map[N / 2][N / 2] = 2;
            w += 2;

            map[N / 2 - 1][N / 2] = 1;
            map[N / 2][N / 2 - 1] = 1;
            b += 2;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int a = Integer.parseInt(st.nextToken());

                placeStone(r, c, a);
            }

            sb.append("#").append(t).append(" ").append(b).append(" ").append(w).append("\n");
        }
        System.out.println(sb);
    }

    private static void placeStone(int r, int c, int type) {

        for (int d = 0; d < dc.length; d++) {
            Queue<Node> q = new LinkedList<>();

            int nr = r + dr[d];
            int nc = c + dc[d];

            while (isRange(nr, nc)) {
                // 빈 곳이라면 스탑
                if(map[nr][nc] == 0) {
                    break;
                } else if(map[nr][nc] !=type) { // 자신과 다른 색깔의 돌이라면
                    q.add(new Node(nr, nc));//돌변경 후보들 큐에 넣기
                } else { // 여기까지 온 건 자신과 같은 색깔의 돌이라는 것
                    check(q, type); //같은 돌이 있으면 그 사이의 돌을 전부 자신의 돌로 변경
                    break;
                }

                nr += dr[d];
                nc += dc[d];
            }
        }
        map[r][c] = type;//주어진 위치에 돌 두기, 돌을 놓을 수 없는 곳은 입력으로 주어지지 않는다
        if (type == 1) {
            b++;
        } else {
            w++;
        }

    }

    private static void check(Queue<Node> q, int type) {

        while (!q.isEmpty()) {

            Node cur = q.poll();
            map[cur.r][cur.c] = type;//자신의 돌로 변경

            if (type == 1) {//개수 변경
                b++;
                w--;
            } else {
                b--;
                w++;
            }
        }

    }

    private static boolean isRange(int nr, int nc) {
        if (!(nr < 0 || nc < 0 || nr >= N || nc >= N)) {
            return true;
        }
        return false;
    }

    static int[] dr = { -1, 1, 0, 0, 1, -1, 1, -1 };
    static int[] dc = { 0, 0, 1, -1, 1, -1, -1, 1 };

    static class Node {
        int r, c, type;

        public Node(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

    }
     */




    /*
    static final int B = 1;
    static final int W = 2;
    static int N;
    static int[][] board;

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            int idx = N / 2;
            board[idx][idx] = W;	board[idx - 1][idx - 1] = W;
            board[idx - 1][idx] = B;	board[idx][idx - 1] = B;
            List<int[]> command = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                command.add(new int[]{x, y, c});
            }
            game(command);
            sb.append("#" + tc + " " + count(B) + " " + count(W) + "\n");
        }
        System.out.println(sb);
	}

    public static void game(List<int[]> command) {
        for (int[] c : command) {
            int nx = c[0];
            int ny = c[1];
            board[nx][ny] = c[2];

            // 가로
            while(true) {
                ny --;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = ny; i <= c[1]; i++) board[nx][i] = c[2];
                    break;
                }
            }
            nx = c[0];
            ny = c[1];
            while(true) {
                ny ++;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = c[1]; i <= ny; i++) board[nx][i] = c[2];
                    break;
                }
            }

            // 세로
            nx = c[0];
            ny = c[1];
            while(true) {
                nx --;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = nx; i <= c[0]; i++) board[i][ny] = c[2];
                    break;
                }
            }

            nx = c[0];
            ny = c[1];
            while(true) {
                nx ++;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = c[0]; i <= nx; i++) board[i][ny] = c[2];
                    break;
                }
            }

            // 대각선
            nx = c[0];
            ny = c[1];
            while(true) {
                nx --;
                ny --;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = 1; i < c[0] - nx; i++) {
                        board[nx + i][ny + i] = c[2];
                    }
                    break;
                }
            }

            nx = c[0];
            ny = c[1];
            while(true) {
                nx ++;
                ny ++;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = 1; i < nx - c[0]; i++) {
                        board[nx - i][ny - i] = c[2];
                    }
                    break;
                }
            }

            // /
            nx = c[0];
            ny = c[1];
            while(true) {
                nx --;
                ny ++;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = 1; i < c[0] - nx; i++) {
                        board[nx + i][ny - i] = c[2];
                    }
                    break;
                }
            }

            nx = c[0];
            ny = c[1];
            while(true) {
                nx ++;
                ny --;
                if (!isRange(nx, ny))	break;

                if (board[nx][ny] == 0)	break;

                if (board[nx][ny] == c[2]) {
                    for (int i = 0; i < nx - c[0]; i++) {
                        board[nx - i][ny + i] = c[2];
                    }
                    break;
                }
            }

        }
    }

    public static boolean isRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N) ? true : false;
    }

    public static int count(int type) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == type)	cnt++;
            }
        }

        return cnt;
    }
     */
}
