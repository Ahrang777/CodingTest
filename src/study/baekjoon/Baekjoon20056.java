package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/20056
 *
 * 마법사 상어와 파이어볼
 * 삼성전자 공채
 */
public class Baekjoon20056 {

    static class Fire {
        int x, y, m, d, s;

        public Fire() {

        }

        public Fire(int x, int y, int m, int d, int s) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }

    public static int n, m, k;
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static ArrayList<Fire>[][] map;

    public static ArrayList<Fire> fires = new ArrayList<>();

    public static void solution() {
        for (int i = 0; i < k; i++) {
            // 이동
            moveFire();

            process();
        }

        getM();
    }

    public static void getM() {
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!map[i][j].isEmpty()) {
                    for (Fire f : map[i][j]) {
                        total += f.m;
                    }
                }
            }
        }

        System.out.println(total);
    }

    public static void process() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].size() > 1) {
                    int totalM = 0;
                    int totalS = 0;
                    int odd = 0;
                    int even = 0;
                    int size = map[i][j].size();

                    fires.removeAll(map[i][j]);

                    for (int k = 0; k < size; k++) {
                        Fire f = map[i][j].get(k);
                        totalM += f.m;
                        totalS += f.s;

                        if (f.d % 2 == 0) {
                            even++;
                        } else {
                            odd++;
                        }
                    }

                    totalM /= 5;
                    totalS /= size;

                    map[i][j].clear();

                    // 질량 0이면 소멸
                    if (totalM == 0) {
                        continue;
                    }

                    if (even == size || odd == size) {
                        for (int d = 0; d < 8; d += 2) {
                            Fire fire = new Fire(i, j, totalM, d, totalS);
                            map[i][j].add(fire);
                            fires.add(fire);
                        }
                    } else {
                        for (int d = 1; d < 8; d += 2) {
                            Fire fire = new Fire(i, j, totalM, d, totalS);
                            map[i][j].add(fire);
                            fires.add(fire);
                        }
                    }
                }
            }
        }
    }

    public static void moveFire() {
        for (Fire f : fires) {
            int s = f.s;
            int d = f.d;

            f.x = (f.x + n + s * dx[d] % n) % n;
            f.y = (f.y + n + s * dy[d] % n) % n;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!map[i][j].isEmpty()) {
                    map[i][j].clear();
                }
            }
        }

        for (Fire f : fires) {
            map[f.x][f.y].add(f);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            Fire fire = new Fire();
            fire.x = Integer.parseInt(st.nextToken()) - 1;
            fire.y = Integer.parseInt(st.nextToken()) - 1;
            fire.m = Integer.parseInt(st.nextToken());
            fire.s = Integer.parseInt(st.nextToken());
            fire.d = Integer.parseInt(st.nextToken());

            map[fire.x][fire.y].add(fire);
            fires.add(fire);
        }

        solution();
    }
}
