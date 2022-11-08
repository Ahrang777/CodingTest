package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV5LsaaqDzYDFAXc&categoryId=AV5LsaaqDzYDFAXc&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * 진기의 최고급 붕어빵
 * 다시 풀기
 */
public class Swea1860 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list);

            boolean isPossible = true;

            for (int i = 0; i < N; i++) {
                int tmp = list.get(i);	// 손님이 온 시간
                int cnt = K * (tmp / M);	// 해당 손님이 온 시간동안 만들었을 붕어빵 수
                if (cnt < i + 1) {	// 만든 붕어빵 수 < 현재까지 손님 수
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                sb.append("#" +  tc + " Possible\n");
            } else {
                sb.append("#" +  tc + " Impossible\n");
            }

        }

        System.out.println(sb);
    }
}
