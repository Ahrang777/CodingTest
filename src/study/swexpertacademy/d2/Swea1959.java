package study.swexpertacademy.d2;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PpoFaAS4DFAUq&categoryId=AV5PpoFaAS4DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 두 개의 숫자열
 */
public class Swea1959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            int max = Integer.MIN_VALUE;
            if (N >= M) {
                for (int i = 0; i <= N - M; i++) {
                    int total = 0;
                    for (int j = 0; j < M; j++) {
                        total += A[i + j] * B[j];
                    }
                    max = Math.max(max, total);
                }
            } else {
                for (int i = 0; i <= M - N; i++) {
                    int total = 0;
                    for (int j = 0; j < N; j++) {
                        total += A[j] * B[i + j];
                    }
                    max = Math.max(max, total);
                }
            }

            sb.append("#").append(t + " ").append(max + "\n");
        }
        System.out.println(sb);
    }
}
