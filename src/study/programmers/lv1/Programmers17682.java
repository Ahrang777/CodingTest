package study.programmers.lv1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers17682 {
	static int[] sum = new int[3];
	public int solution(String msg){
		String reg = "([0-9]{1,2}[S|T|D][*|#]{0,1})";
		Pattern p = Pattern.compile(reg+reg+reg);
		Matcher m = p.matcher(msg);
		m.find();
		for(int i=1; i<=m.groupCount(); i++){
			Pattern p1 = Pattern.compile("([0-9]{1,2})([S|T|D])([*|#]{0,1})");
			Matcher m1 = p1.matcher(m.group(i));
			m1.find();
			sum[i-1] = (int)Math.pow(Integer.parseInt(m1.group(1)), getpow(m1.group(2)));
			setting(i,m1.group(3));
		}
		return(sum[0]+ sum[1]+ sum[2]);
	}
	private void setting(int idx, String msg){
		if(msg.equals("*")){
			sum[idx - 1] *= 2;
			if(idx > 1 ){
				sum[idx - 2] *=2;
			}
		}
		else if(msg.equals("#")){
			sum[idx - 1] *=-1 ;
		}
	}

	private int getpow(String mag){
		if(mag.equals("S")){
			return 1;
		}
		else if(mag.equals("D")){
			return 2;
		}
		else {
			return 3;
		}
	}

	/*static int[] scores;
	public static int solution(String dartResult) {
		int answer = 0;

		scores = new int[3];

		int len = dartResult.length();
		int index = 0;
		for (int i = 0; i < len; i+=2) {
			int score = 0;
			if (dartResult.charAt(i + 1) == '0') {
				score = Integer.parseInt(dartResult.substring(i, i + 2));
				i++;
			} else {
				score = dartResult.charAt(i) - '0';
			}

			char bonus = dartResult.charAt(i + 1);
			String option = "";
			if (i + 2 < len) {
				option = dartResult.substring(i + 2);
				if (option.startsWith("*") || option.startsWith("#")) {
					i++;
				}
			}

			calc(index, score, bonus, option);
			index++;
		}

		for (int n : scores) {
			answer += n;
		}

		return answer;
	}

	private static void calc(int index, int score, char bonus, String option) {
		System.out.println(score + " " + bonus + " " + option);
		if (bonus == 'D') {
			score = (int) Math.pow(score, 2);
		} else if (bonus == 'T') {
			score = (int) Math.pow(score, 3);
		}

		if (option.startsWith("*")) {
			score *= 2;
			if (index != 0) {
				scores[index - 1] *= 2;
			}
		} else if (option.startsWith("#")) {
			score *= -1;
		}

		scores[index] = score;
	}*/
}
