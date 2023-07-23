package study.programmers.lv1;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17681
 * [1차]비밀지도
 */
public class Programmers17681 {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		for (int i = 0; i < n; i++) {
			int result = arr1[i] | arr2[i];
			answer[i] = change(result, n);
		}

		return answer;
	}

	private String change(int n, int len) {
		if (n == 0) {
			if (len > 0) {
				String str = "";
				for (int i = 0; i < len; i++) {
					str += " ";
				}
				return str;
			}
			return "";
		}

		// 끝부터 변경해 나가며 len을 1씩 감소 >> 이진수로 변경했을 때 len길이 보다 작은 경우 앞에 0을 붙이기 위함
		return n % 2 == 0 ? change(n / 2, len - 1) + " " : change(n / 2, len - 1) + "#";
	}


	/*public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] result = new String[n];

		for (int i = 0; i < n; i++) {
			result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
		}

		String pattern = "%" + n + "s";
		for (int i = 0; i < n; i++) {
			result[i] = String.format(pattern, result[i]);
			result[i] = result[i].replaceAll("1", "#");
			result[i] = result[i].replaceAll("0", " ");
		}

		return result;
	}
	*/
	/*public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = arr1[i] | arr2[i];
		}


		for (int i = 0; i < n; i++) {
			String str = Integer.toBinaryString(arr[i]);
			int size = str.length();
			for (int j = 0; j < n - size; j++) {
				str = "0" + str;
			}

			System.out.println(str);
			answer[i] = change(str);
		}

		return answer;
	}

	private String change(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i) == '1' ? '#' : ' ';
			sb.append(ch);
		}
		return sb.toString();
	}*/
}
