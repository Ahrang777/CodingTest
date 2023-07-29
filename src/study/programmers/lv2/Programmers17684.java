package study.programmers.lv2;

import java.util.ArrayList;
import java.util.List;

/**
 * [3차]압축
 */
public class Programmers17684 {
	static List<String> list;
	static List<Integer> result;

	public int[] solution(String msg) {
		int[] answer = {};

		list = new ArrayList<>();
		result = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			list.add(String.valueOf((char)('A' + i)));
		}

		int idx = 0;
		while (idx != msg.length()) {
			int cnt = findEnd(msg, idx);
			idx += cnt;
		}

		int len = result.size();
		answer = new int[len];
		for (int i = 0; i < len; i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}

	private int findEnd(String msg, int idx) {
		int cnt = 1;
		while (true) {
			if (idx + cnt == msg.length() + 1) {
				result.add(list.indexOf(msg.substring(idx)) + 1);
				break;
			}

			String str = msg.substring(idx, idx + cnt);

			if (!list.contains(str)) {
				String s = msg.substring(idx, idx + cnt - 1);
				result.add(list.indexOf(s) + 1);
				list.add(str);
				break;
			}

			cnt++;
		}

		return cnt - 1;
	}
}
