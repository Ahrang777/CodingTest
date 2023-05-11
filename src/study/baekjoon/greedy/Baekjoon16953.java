package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16953
 *
 * A -> B
 */
public class Baekjoon16953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int cnt = 0;

		while (true) {
			if (A == B) {
				cnt++;
				break;
			}

			if ((A > B) || (B % 10 != 1 && B % 2 == 1)) {
				cnt = -1;
				break;
			}

			if (B != 1 && B % 10 == 1) {
				cnt++;
				B /= 10;
				continue;
			}

			if (B % 2 == 0) {
				cnt++;
				B /= 2;
			}
		}

		System.out.println(cnt);
	}
}
