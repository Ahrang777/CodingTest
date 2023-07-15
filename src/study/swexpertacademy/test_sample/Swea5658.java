package study.swexpertacademy.test_sample;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
 * 보물상자 비밀번호
 */
public class Swea5658 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());


			Set<Integer> set = new HashSet<>();
			String str = br.readLine();
			String[] arr = str.split("");

			int size = N / 4;
			for (int i = 0; i < size; i++) {	// 이동칸수
				String tmp = arr[N - 1];
				for (int j = N - 1; j > 0; j--) {
					arr[j] = arr[j - 1];
				}
				arr[0] = tmp;

				for (int j = 0; j < N; j+=size) {	// 각변 시작점
					StringBuilder number = new StringBuilder();
					for (int k = 0; k < size; k++) {
						number.append(arr[j + k]);
					}
					set.add(Integer.parseInt(number.toString(), 16));
				}
			}

			// set 정렬 후 K번째 값 출력
			List<Integer> results = new ArrayList<>(set);
			Collections.sort(results, Collections.reverseOrder());

			sb.append("#").append(tc).append(" ").append(results.get(K - 1)).append("\n");
		}

		System.out.println(sb);
	}
}
