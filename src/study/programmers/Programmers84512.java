package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 *
 * 모음사전
 * 다시풀기
 */
public class Programmers84512 {

    /*public static int solution(String word) {
        String str = "AEIOU";

        // 마지막 자리 AEIOU 순서대로 바뀔때마다 1씩 증가
        // 4번째 자리 AEIOU 순서대로 바뀔때마다 (5 * 1 + 1) = 6씩 증가
        // 3번째 자리 AEIOU 순서대로 바뀔때마다 (5 * 6 + 1) = 31씩 증가
        // 2번째 자리 AEIOU 순서대로 바뀔때마다 (5 * 31 + 1) = 156씩 증가
        // 1번째 자리 AEIOU 순서대로 바뀔때마다 (5 * 156 + 1) = 781씩 증가
        // A, AA, AAA 처럼 해당 자리수 만큼 시작지점 설정하고 그 자릿수만큼의 AAA,,, 에서 각 자리의 변화반영
        int[] arr = {781, 156, 31, 6, 1};

        int answer = word.length();
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = str.indexOf(word.charAt(i));
            answer+=arr[i] * index;
        }

        return answer;
    }*/

    /*public static int solution(String word) {
        int answer = 0, per = 3905;
        for(String s : word.split("")) answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
        return answer;
    }*/

    public static List<String> list = new ArrayList<>();

    public static void dfs(String str, int len) {
        if (len > 5) return;
        list.add(str);
        for (int i = 0; i < 5; i++) {
            dfs(str + "AEIOU".charAt(i), len + 1);
        }
    }

    public static int solution(String word) {
        dfs("", 0);
        return list.indexOf(word);
    }



    public static void main(String[] args) {
        String[] word = {"AAAAE", "AAAE", "I", "EIO"};

        for (int i = 0; i < word.length; i++) {
            System.out.println(solution(word[i]));
        }
    }
}
