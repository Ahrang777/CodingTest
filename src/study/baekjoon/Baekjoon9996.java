package study.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9996
 * 한국이 그리울 땐 서버에 접속하지
 */
public class Baekjoon9996 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), "*");
		String start = st.nextToken();
		String end = st.nextToken();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			// 패턴: a*a 이고 str이 a 인 경우
			// start = end = a 이기때문에 길이 비교를 하지 않으면 true가 되버린다.
			if (start.length() + end.length() > str.length()) {
				sb.append("NE").append("\n");
				continue;
			}

			if (str.startsWith(start) && str.endsWith(end)) {
				sb.append("DA").append("\n");
				continue;
			}

			sb.append("NE").append("\n");
		}

		System.out.println(sb);
	}
}
