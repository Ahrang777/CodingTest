package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 상호의 배틀필드
 */
public class Swea1873 {
    static List<Character> t = Arrays.asList('^', '<', 'v', '>');
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int H, W;
    static char[][] map;
    static int[] pos = new int[2];	// x, y

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);

                    if (t.contains(map[i][j])) {
                        pos[0] = i;
                        pos[1] = j;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            char[] commands = str.toCharArray();

            game(commands);

            sb.append("#" + tc + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    // '^', 'v', '<', '>'
    public static void game(char[] commands) {
        int nx, ny;
        int x, y;
        for (char c : commands) {
            x = pos[0];
            y = pos[1];

            if (c == 'U') {
                map[x][y] = '^';
                nx = x - 1;
                ny = y;
            } else if (c == 'D') {
                map[x][y] = 'v';
                nx = x + 1;
                ny = y;

            } else if (c == 'L') {
                map[x][y] = '<';
                nx = x;
                ny = y - 1;
            } else if (c == 'R') {
                map[x][y] = '>';
                nx = x;
                ny = y + 1;
            } else { // S
                int dir = t.indexOf(map[x][y]);
                nx = x;
                ny = y;

                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];

                    if (!isRange(nx, ny) || map[nx][ny] == '#')	break;
                    if (map[nx][ny] == '*') {
                        map[nx][ny] = '.';
                        break;
                    }
                }

                continue;
            }

            if (isRange(nx, ny) && map[nx][ny] == '.') {
                map[nx][ny] =  map[x][y];
                map[x][y] = '.';
                pos[0] = nx;
                pos[1] = ny;
            }

        }

    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}
