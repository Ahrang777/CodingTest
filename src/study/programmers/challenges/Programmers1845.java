package study.programmers.challenges;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1845
 *
 * 폰켓몬
 */
public class Programmers1845 {

    public static int solution(int[] nums) {
        return Arrays.stream(nums).boxed()
            .collect(Collectors.collectingAndThen(Collectors.toSet(),
                phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));

        /*int answer = 0;

        int max = nums.length / 2;

        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(num -> set.add(num));

        max = Math.min(set.size(), max);

        answer = max;
        return answer;*/


        /*int max = nums.length / 2;

        // 중복 제거
        Set<Integer> set = new HashSet<>();
        for (int n : nums){
            set.add(n);
        }

        if(max > set.size()){
            max = set.size();
        }

        return max;*/
    }

    public static void main(String[] args) {
        int[][] nums = {
                {3, 1, 2, 3},
                {3, 3, 3, 2, 2, 4},
                {3, 3, 3, 2, 2, 2}
        };

        for (int i = 0; i < 3; i++) {
            System.out.println(solution(nums[i]));
        }
    }
}
