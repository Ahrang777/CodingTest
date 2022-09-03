package study.programmers;


import java.util.*;
/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12906
 *
 * 같은 숫자는 싫어
 */
public class Programmers12906 {

    public static int[] solution(int []arr) {
        int[] answer = {};

        Stack<Integer> s = new Stack<>();
        s.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (s.peek() != arr[i]) s.push(arr[i]);
        }

        answer = new int[s.size()];

        for (int i = s.size() - 1; i >= 0; i--) {
            answer[i] = s.pop();
        }

        return answer;
    }

    /*
    public int[] solution(int []arr) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        int preNum = 10;
        for(int num : arr) {
            if(preNum != num)
                tempList.add(num);
            preNum = num;
        }
        int[] answer = new int[tempList.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = tempList.get(i).intValue();
        }
        return answer;
    }
     */

    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 3, 3, 0, 1, 1},
                {4, 4, 4, 3, 3}
        };

        for (int i = 0; i < 2; i++) {
            for (int n : solution(arr[i])) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
