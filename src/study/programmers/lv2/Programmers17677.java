package study.programmers.lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17677
 * 뉴스 클러스터링
 */
public class Programmers17677 {
	public int solution(String str1, String str2) {
		int answer = 0;

		Map<String, Integer> m1 = new HashMap<>();
		Map<String, Integer> m2 = new HashMap<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			String str = str1.substring(i, i + 2);
			if (Pattern.matches("^[a-zA-Z]{2}$", str)) {
				str = str.toUpperCase();
				m1.put(str, m1.getOrDefault(str, 0) + 1);
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			String str = str2.substring(i, i + 2);
			if (Pattern.matches("^[a-zA-Z]{2}$", str)) {
				str = str.toUpperCase();
				m2.put(str, m2.getOrDefault(str, 0) + 1);
			}
		}

		int n1 = 0; // 교
		int n2 = 0; // 합
		Set<String> set1 = m1.keySet();
		Set<String> set2 = m2.keySet();

		for (String k1 : set1) {
			if (set2.contains(k1)) {
				int min = Math.min(m1.get(k1), m2.get(k1));
				int max = Math.max(m1.get(k1), m2.get(k1));
				n1 += min;
				n2 += max;
				continue;
			}

			n2 += m1.get(k1);
		}

		for (String k2 : set2) {
			if (set1.contains(k2))  continue;
			n2 += m2.get(k2);
		}

		return answer = n2 == 0 ? 65536 : (int) (65536 * ((double) n1) / n2);
	}
}
