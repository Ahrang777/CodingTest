package study.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13305
 *
 * 주유소
 */
public class Baekjoon13305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 도로길이, 리터당 가격이 정수 범위더라도 long으로 처리하자
		// 아래 total에서 정수 범위를 넘어 갈 수 있어서 long으로 처리하는게 맞는데
		// 만약 long total = Integer.MAX_VALUE + Integer.MAX_VALUE 의 경우 이상하게 들어간다
		// Integer.MAX_VALUE + Integer.MAX_VALUE 계산을 먼저하고 long으로 들어가기에 -2가 들어감
		// 물론 long total에 처음 들어가는 값이 정수 범위 내의 경우 total 에 들어가는 순간 long 형태가 되므로
		// 이어서 더해지는 결과는 long으로 문제가 없지만 가급적 실수가 없도록 처음부터 long으로 처리하자
		long[][] cities = new long[N][2];	// 가격, 길이
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			cities[i][1] = Long.parseLong(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cities[i][0] = Long.parseLong(st.nextToken());
		}

		long total = 0;
		long price = cities[0][0];
		for (int i = 0; i < N - 1; i++) {
			price = Math.min(price, cities[i][0]);
			total += price * cities[i][1];
		}

		System.out.println(total);
	}
}
