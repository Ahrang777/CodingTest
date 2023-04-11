package study.programmers.beginner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120956
 * 옹알이(1)
 */
public class Programmers120956 {
	public static int solution(String[] babbling) {
		int answer = 0;
		String[] targets = {"aya", "ye", "woo", "ma"};
		String del = ",";

		List<String> results = Arrays.stream(babbling).collect(Collectors.toList());

		for (String target : targets) {
			results = results.stream().map(str -> str.replace(target, del)).collect(Collectors.toList());
		}

		List<String> check = Arrays.asList(",", ",,", ",,,", ",,,,");
		for (String result : results) {
			if (check.contains(result)) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		String[][] str = {
			{"aya", "yee", "u", "maa", "wyeoo"},
			{"ayaye", "uuuma", "ye", "yemawoo", "ayaa"}
		};

		for (String[] strings : str) {
			System.out.println(solution(strings));
		}
	}
}
