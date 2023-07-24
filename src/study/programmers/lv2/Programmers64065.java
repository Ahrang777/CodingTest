package study.programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/64065
 * 튜플
 */
public class Programmers64065 {
	public int[] solution(String s) {
		Set<String> set = new HashSet<>();
		String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

		int[] answer = new int[arr.length];
		int idx = 0;
		for (String str : arr) {
			for (String str1 : str.split(",")) {
				// Set에 add로 값을 넣게 되면 true를 반환 >> 기존에 값이 없는 경우
				if (set.add(str1)) {
					answer[idx++] = Integer.parseInt(str1);
					break;
				}
			}
		}

		return answer;

		/*
		Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String n = matcher.group();
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int size = map.size();
        int[] answer = new int[size];
        for (String key: map.keySet()) {
            answer[size - map.get(key)] = Integer.parseInt(key);
        }
        return answer;
		 */
	}

	// public int[] solution(String s) {
	// 	int[] answer = {};
	//
	// 	String[] arr = s.substring(2, s.length() - 2).replace("},{", "-").split("-");
	// 	Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
	// 	/*Arrays.sort(arr, new Comparator<String>() {
	// 		@Override
	// 		public int compare(String o1, String o2) {
	// 			return Integer.compare(o1.length(), o2.length());
	// 		}
	// 	});*/
	//
	// 	List<Integer> list = new ArrayList<>();
	// 	for (String str : arr) {
	// 		String[] split = str.split(",");
	// 		for (String sp : split) {
	// 			int n = Integer.parseInt(sp);
	// 			if (list.contains(n)) {
	// 				continue;
	// 			}
	//
	// 			list.add(n);
	// 		}
	// 	}
	//
	// 	answer = new int[list.size()];
	// 	for (int i = 0; i < list.size(); i++) {
	// 		answer[i] = list.get(i);
	// 	}
	// 	return answer;
	// }
}
