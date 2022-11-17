package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWczm7QaACgDFAWn&categoryId=AWczm7QaACgDFAWn&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=6
 *
 * 삼성시의 버스 노선
 */
public class Swea6485 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];    // A, B

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int P = Integer.parseInt(br.readLine());
            int[] counts = new int[P];
            for (int i = 0; i < P; i++) {
                counts[i] = Integer.parseInt(br.readLine());
            }

            for (int i = 0; i < P; i++) {
                int count = 0;
                for (int[] range : arr) {
                    if (counts[i] >= range[0] && counts[i] <= range[1]) {
                        count++;
                    }
                }
                counts[i] = count;
            }

            sb.append("#" + tc + " ");
            for (int count : counts) {
                sb.append(count).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
