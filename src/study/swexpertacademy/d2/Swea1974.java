package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1974 {

    public static void main(String[] args) throws IOException {
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int[][] map = new int[9][9];

            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean isDuplicated = false;
            // 행
            for (int i = 0; i < 9 && !isDuplicated; i++) {
                int[] arr = new int[9];
                for (int j = 0; j < 9; j++) {
                    arr[map[i][j] - 1]++;
                }

                for (int j = 0; j < 9; j++) {
                    if (arr[j] == 0) {
                        isDuplicated = true;
                        break;
                    }
                }
            }

            // 열
            for (int i = 0; i < 9 && !isDuplicated; i++) {
                int[] arr = new int[9];
                for (int j = 0; j < 9; j++) {
                    arr[map[j][i] - 1]++;
                }

                for (int j = 0; j < 9; j++) {
                    if (arr[j] == 0) {
                        isDuplicated = true;
                        break;
                    }
                }
            }

            // 격자
            for (int i = 0; i < 9 && !isDuplicated; i += 3) {
                for (int j = 0; j < 9; j += 3) {
                    int[] arr = new int[9];
                    for (int r = i; r < i + 3; r++) {
                        for (int c = j; c < j + 3; c++) {
                            arr[map[r][c] - 1]++;
                        }
                    }

                    for (int k = 0; k < 9; k++) {
                        if (arr[k] == 0) {
                            isDuplicated = true;
                            break;
                        }
                    }
                }
            }

            int result = isDuplicated ? 0 : 1;
            sb.append("#").append(tc + " ").append(result + "\n");
        }

        System.out.println(sb);
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int[][] map = new int[9][9];
            boolean flag = true;
            int res;

            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 행
            for (int i = 0; i < 9; i++) {
                int[] arr = new int[9];
                for (int j = 0; j < 9; j++) {
                    arr[map[i][j] - 1]++;
                }

                for (int j = 0; j < 9; j++) {
                    if (arr[j] == 0) {
                        flag = false;
                        break;
                    }
                }
            }

            // 열
            for (int i = 0; i < 9; i++) {
                int[] arr = new int[9];
                for (int j = 0; j < 9; j++) {
                    arr[map[j][i] - 1]++;
                }

                for (int j = 0; j < 9; j++) {
                    if (arr[j] == 0) {
                        flag = false;
                        break;
                    }
                }
            }

            // 격자
            for (int i = 0; i < 9; i+=3) {
                for (int j = 0; j < 9; j+=3) {
                    int[] arr = new int[9];
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            arr[map[i+r][j+c] - 1]++;
                        }
                    }

                    for (int k = 0; k < 9; k++) {
                        if (arr[k] == 0) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) res = 1;
            else res = 0;
            System.out.printf("#%d %d\n", tc, res);
        }
    }
}
