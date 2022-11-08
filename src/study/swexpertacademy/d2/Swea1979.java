package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PuPq6AaQDFAUq&categoryId=AV5PuPq6AaQDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 어디에 단어가 들어갈 수 있을까
 */
public class Swea1979 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int res = 0;

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가로
            for (int i = 0; i < N; i++) {
                int rowCnt = 0;
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        if (rowCnt == K) res++;
                        rowCnt = 0;
                    } else {
                        rowCnt++;
                    }

                }
                if (rowCnt == K)	res++;
            }

            // 세로
            for (int i = 0; i < N; i++) {
                int colCnt = 0;
                for (int j = 0; j < N; j++) {
                    if (map[j][i] == 0) {
                        if (colCnt == K)	res++;
                        colCnt = 0;
                    } else {
                        colCnt++;
                    }
                }

                if (colCnt == K) res++;
            }

            System.out.printf("#%d %d\n", tc, res);

        }
    }
}
