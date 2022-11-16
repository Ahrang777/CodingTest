package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWYygN36Qn8DFAVm&categoryId=AWYygN36Qn8DFAVm&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 현주의 상자 바꾸기
 */
public class Swea5789 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int[] arr = new int[N + 1];

            for (int i = 1; i <= Q; i++) {
                st = new StringTokenizer(br.readLine());
                int L = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());

                for (int j = L; j <= R; j++) {
                    arr[j] = i;
                }
            }

            sb.append("#" + tc + " ");
            for (int i = 1; i <= N; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
