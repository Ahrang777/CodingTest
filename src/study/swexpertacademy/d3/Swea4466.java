package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWOUfCJ6qVMDFAWg&categoryId=AWOUfCJ6qVMDFAWg&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 최대 성적표 만들기
 */
public class Swea4466 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<Integer> scores = new ArrayList<>();
            int max = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(scores, Collections.reverseOrder());
            for (int i = 0; i < K; i++) {
                max += scores.get(i);
            }
            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    /*public static void dfs(int depth, int start, int sum) {
        if (depth == K) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(depth + 1, i + 1, sum + scores[i]);
                visited[i] = false;
            }
        }
    }*/

    /*public static void dfs(int depth, int start, int sum) {
        if (depth == K) {
            max = Math.max(max, sum);
            return;
        }
        if (start == N) {
            return;
        }

        dfs(depth + 1, start + 1, sum + scores[start]);
        dfs(depth, start + 1, sum);
    }*/
}
