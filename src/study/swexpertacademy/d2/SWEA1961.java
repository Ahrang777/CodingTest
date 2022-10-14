package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5Pq-OKAVYDFAUq&categoryId=AV5Pq-OKAVYDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 숫자 배열 회전
 */
public class SWEA1961 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] r = new int[N][N];
            String[] res = new String[N];
            for (int i = 0; i < N; i++)	res[i] = "";

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < 3; k++) {
                rotate(map, r, 90*(k+1));

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++){
                        res[i] += r[i][j];
                    }
                    res[i] += " ";
                }
            }

            System.out.println("#" + tc);
            for (String str : res) {
                System.out.println(str);
            }
        }
    }

    public static void rotate(int[][] map, int[][] r, int degree) {
        int N = map.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                switch(degree) {
                    case 90:
                        r[i][j] = map[N - 1 - j][i];	break;
                    case 180:
                        r[i][j] = map[N-1-i][N-1-j];
                        break;
                    case 270:
                        r[i][j] = map[j][N-1-i];
                        break;
                }
        }
    }
}
