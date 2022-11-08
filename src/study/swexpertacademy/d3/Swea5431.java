package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWVl3rWKDBYDFAXm&categoryId=AWVl3rWKDBYDFAXm&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 민석이의 과제 체크하기
 */
public class Swea5431 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<Integer> submissions = new ArrayList<>();
            List<Integer> notSubmission = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                submissions.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i <= N; i++) {
                if (submissions.contains(i))	continue;
                notSubmission.add(i);
            }

            Collections.sort(notSubmission);
            sb.append("#" + tc + " ");
            for (int n : notSubmission) {
                sb.append(n + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
