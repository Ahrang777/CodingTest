package study.swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AXxNn6GaPW4DFASZ&categoryId=AXxNn6GaPW4DFASZ&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 교환학생
 * TODO 다시풀기
 */
public class Swea13038 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[7];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 7; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < 7; i++) {
                if (arr[i] == 0) continue;

                int start = i;
                int end = i;
                int cnt = 0;

                while (true) {
                    if (arr[end % 7] == 1) cnt++;
                    if (cnt == N) {
                        min = Math.min(min, end - start + 1);   // start ~ end 이동횟수 계산
                        break;
                    }
                    end++;
                }
            }

            sb.append("#" + tc + " " + min + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    /**
     * 생각할 점
     * 처음 나온 1이 시작점이 아닐수 있다.
     *
     * N = 2
     * 0 1 0 0 0 1 1
     * 인 경우 처음 위치 1에서 시작하면 5가 되지만
     * 위치 5에서 시작하면 2가 되서 최솟값은 2이다.
     */
}
