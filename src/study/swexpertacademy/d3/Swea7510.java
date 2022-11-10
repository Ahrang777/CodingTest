package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWoEzJFa2A4DFARq&categoryId=AWoEzJFa2A4DFARq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 상원이의 연속 합
 */
public class Swea7510 {
    static int N;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int cnt = 0;

            // 시작점
            for (int start = 1; start <= N; start++) {
                int sum = 0;
                // 종료지점 >> 시작지점 ~ 종료지점까지 연속해서 더함
                for (int end = start; end <= N; end++) {
                    sum += end;
                    if (sum == N) {
                        cnt++;
                        break;
                    } else if (sum > N) {
                        break;
                    }
                }
            }

            sb.append("#" + tc + " " + cnt + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    /*
    // 이게 더 빠름
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int sqrt = (int) Math.sqrt(N);
            List<Integer> list = new ArrayList<>();
            int cnt = 0;

            for (int i = 1; i <= sqrt; i++) {
                if (N % i == 0) {
                    list.add(i);
                    if (N / i != i) {
                        list.add(N / i);
                    }
                }
            }

            for (int n : list) {
                if (n % 2 == 0) continue;
                if (n == N)	continue;
                cnt++;
            }

            if ((N % 2 == 1))	cnt ++;

            sb.append("#" + tc + " " + cnt + "\n");
        }

        System.out.println(sb);
        br.close();
    }*/

    /*
    N % ? == 0 인 ? 값들 중 홀수 인 값만큼 연속된 숫자들은 조건만족함 >> 자기자신 제외
    N 의 약수 중 홀수이며 N이 아닌 값들은 조건만족함

    단, N 이 홀수인 경우 2도 만족하므로 + 1
     */
}
