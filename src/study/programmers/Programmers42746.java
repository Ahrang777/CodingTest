package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 *
 * 가장 큰 수
 * 다시풀기
 */
public class Programmers42746 {


    public static String solution(int[] numbers) {
        String answer = "";

        List<Integer> list = new ArrayList<>();
        for(int n : numbers){
            list.add(n);
        }

        Collections.sort(list, (n1, n2) -> {
            String s1 = String.valueOf(n1);
            String s2 = String.valueOf(n2);

            return (s2 + s1).compareTo(s1 + s2);
        });

        StringBuilder sb = new StringBuilder();
        for (Integer n : list) {
            sb.append(n);
        }

        answer = sb.toString();

        // "0000" or "0" 이런식인 경우
        if (answer.charAt(0) == '0') {
            return "0";
        } else {
            return answer;
        }

        /*String[] arr = new String[numbers.length];

        for(int i = 0;i<numbers.length;i++){
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        if (arr[0].equals("0")) {
            return "0";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str : arr) {
                sb.append(str);
            }
            answer = sb.toString();
        }

        return answer;
         */
    }

    public static void main(String[] args) {
        int[][] numbers = {
                {6, 10, 2},
                {3, 30, 34, 5, 9}
        };

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(solution(numbers[i]));
        }
    }
}
