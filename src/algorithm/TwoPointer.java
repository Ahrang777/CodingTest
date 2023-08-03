package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 투포인터
 * 시간복잡도 : O(N)
 */
public class TwoPointer {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0, e = 0, sum = 0, cnt = 0;
		while (s < N) {
			if (sum >= M || e == N) {
				sum -= arr[s];
				s++;
			} else {
				sum += arr[e];
				e++;
			}

			if (sum == M) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}
