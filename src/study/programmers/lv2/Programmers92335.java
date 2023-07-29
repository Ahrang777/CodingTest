package study.programmers.lv2;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/92335
 * k진수에서 소수 개수 구하기
 */
public class Programmers92335 {
	public int solution(int n, int k) {
		int answer = 0;

		String result = change(n, k);
		String[] arr = result.split("0{1,}");

		for (String num : arr) {
			if (isPrime(Long.parseLong(num))) {
				answer++;
			}
		}

		return answer;
	}

	private static boolean isPrime(long n) {
		if (n == 0 || n == 1)   return false;

		if (n == 2) return true;

		int limit = (int) Math.sqrt(n);

		for (int i = 2; i <= limit; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	private String change(int n, int k) {
		//         String result = "";

		//         while (n > 0) {
		//             result = (n % k) + result;
		//             n /= k;
		//         }
		//         return result;

		if (n == 0) {
			return "";
		}

		return change(n / k, k) + (n % k);
	}
}
