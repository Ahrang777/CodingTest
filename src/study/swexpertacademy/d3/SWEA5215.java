package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * 햄버거 다이어트
 * 다시 풀기
 * 
 * TODO 이런 형태 기억
 */
public class SWEA5215 {
    static int max;
    static int N, L;
    static int[][] info;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());	// 재료수
            L = Integer.parseInt(st.nextToken());	// 제한 칼로리

            info = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                info[i][0] = Integer.parseInt(st.nextToken());
                info[i][1] = Integer.parseInt(st.nextToken());
            }

            max = Integer.MIN_VALUE;
            dfs(0, 0, 0);
            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb);
    }

    // sumT: 재료 맛 합계, sumC: 칼로리 합계
    public static void dfs(int depth, int sumT, int sumC) {
        if (sumC > L)	return;

        if (depth == N) {
            if (sumT > max)	max = sumT;
            return;
        }

        dfs(depth + 1, sumT + info[depth][0], sumC + info[depth][1]);
        dfs(depth + 1, sumT, sumC);
    }
}
