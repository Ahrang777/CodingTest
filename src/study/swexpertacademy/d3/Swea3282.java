package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWBJAVpqrzQDFAWr&categoryId=AWBJAVpqrzQDFAWr&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 0/1 Knapsack
 * 다시풀기
 *
 * 참고
 * https://sskl660.tistory.com/88
 */
public class Swea3282 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] items = new int[N + 1][2];
            int[][] dp = new int[N + 1][K + 1];

            // dp[i][j] >> i번째 물건까지 확인, 가방의 부피가 최대 j 까지인 최댓값

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                items[i][0] = Integer.parseInt(st.nextToken()); // 부피
                items[i][1] = Integer.parseInt(st.nextToken()); // 가치
            }

            for (int i = 1; i <= N; i++) {	// i번째 물건까지 반영한 경우
                for (int j = 1; j <= K; j++) {	// i번째 물건까지 반영할때 총 부피가 최대 j까지인 경우
                    if (items[i][0] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // dp[i-1][j - items[i][0]] 에서 j - items[i][0] 이면 현재 확인하는 물건의 부피가 items[i][0] 이므로
                        // 현재 부피를 추가했을때 딱 맞고 [i-1] 로 바로 위에서 찾기에 그나마 가치가 최댓값
                        // 즉, 해당 부피의 이전까지 최대 가치, 현재 물건을 더했을때 딱 맞는 최대가치 비교해서 더 큰 값을 찾는 것
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
                    }
                }
            }

            sb.append("#" + tc + " " + dp[N][K] + "\n");
        }
        System.out.println(sb);
        br.close();
    }

    /*
    public class KnapSack_DP1 {
	static int N, K;
	static int[][] items;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// 물건 당 첫 번째 열에는 물건의 무게를, 두 번째 열에는 물건의 가치를 저장한다.
		// 물건이 없는 경우도 고려하여 인덱스가 0인 경우(패딩)를 추가해준다.
		items = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			items[i][0] = sc.nextInt();
			items[i][1] = sc.nextInt();
		}
		max = 0;

		// 먼저 i번째 물건의 j번째 무게에서 가질 수 있는 최댓값을 저장할 수 있는 2차원 dp테이블을 정의한다.
		// 이때, 기저 조건을 위해 dp테이블에 인덱스가 0인 경우(패딩)를 추가해준다.
		int[][] dp = new int[N + 1][K + 1];

		// 1번째 물건부터 N번째 물건까지 모두 고려한다.
		for (int i = 1; i <= N; i++) {
			// 무게가 1인 경우부터 무게가 K인 경우까지 모두 고려한다.
			for (int j = 1; j <= K; j++) {
				// 해당 위치에 물건을 넣을 수 없는 경우.
				if (items[i][0] > j) {
					// i - 1번째 물건까지 고려했을때 무게 j에서의 최대 가치(최적해)를 그대로 가져온다.
					dp[i][j] = dp[i - 1][j];
				}
				// 해당 위치에 물건을 넣을 수 있는 경우.
				else {
					// i - 1번째 물건까지 고려했을때 무게 j에서의 최대 가치(최적해)와,
					// i - 1번째 물건까지 고려했을때 무게 j - items[i][0](현재 무게)의 최대 가치(최적해) + items[i][1](현재 가치) 중에서
					// 더 큰 값을 선택한다!
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
				}
			}
		}

		// dp[N][K]를 출력한다(dp테이블의 정의에 따르면 N가지 물건을 모두 고려했을때 K무게에서의 최대 가치를 출력하는 것!).
		System.out.println(dp[N][K]);
		sc.close();
	}
}
     */


    // 기존 2차원 dp 를 1차원으로 줄인경우
    /*
    static int N, K;
	static int[][] items;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// 물건 당 첫 번째 열에는 물건의 무게를, 두 번째 열에는 물건의 가치를 저장한다.
		// 물건이 없는 경우도 고려하여 인덱스가 0인 경우(패딩)를 추가해준다.
		items = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			items[i][0] = sc.nextInt();
			items[i][1] = sc.nextInt();
		}
		max = 0;

		// 먼저 i번째 물건의 j번째 무게에서 가질 수 있는 최댓값을 저장할 수 있는 1차원 dp테이블을 정의한다.
		// 이때, 기저 조건을 위해 dp테이블에 무게가 0인 경우(패딩)를 추가해준다.
		int[] dp = new int[K + 1];

		// 1번째 물건부터 N번째 물건까지 모두 고려한다.
		for (int i = 1; i <= N; i++) {
			// 무게가 K인 경우부터 무게가 items[i][0]인 경우까지 모두 고려한다.
			for (int j = K; j >= items[i][0]; j--) {
				// 해당 위치에 물건을 넣을 수 없는 경우, 1차원 테이블이 딱히 갱신이 되지 않는다.
				// 따라서 2차원 dp 테이블을 사용하는 경우와 달리 분기문이 사라진다.

				// 해당 위치에 물건을 넣을 수 있는 경우.
				// i - 1번째 물건까지 고려했을때 무게 j에서의 최대 가치(최적해)와,
				// i - 1번째 물건까지 고려했을때 무게 j - items[i][0](현재 무게)의 최대 가치(최적해) + items[i][1](현재 가치) 중에서
				// 더 큰 값을 선택한다!
				dp[j] = Math.max(dp[j], dp[j - items[i][0]] + items[i][1]);
			}
		}

		// dp[N][K]를 출력한다(dp테이블의 정의에 따르면 N가지 물건을 모두 고려했을때 K무게에서의 최대 가치를 출력하는 것!).
		System.out.println(dp[K]);
		sc.close();
	}
     */
}
