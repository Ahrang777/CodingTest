package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PzOCKAigDFAUq&categoryId=AV5PzOCKAigDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * SW Expert Academy
 * 파리 퇴치
 */
public class Swea2001 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxValue = 0;
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int sum = 0;

                    for (int a = 0; a < M; a++) {
                        for (int b = 0; b < M; b++) {
                            sum += map[i + a][j + b];
                        }
                    }

                    maxValue = Math.max(maxValue, sum);
                }

            }

            System.out.printf("#%d %d\n", tc, maxValue);
        }
    }
}
