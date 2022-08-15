package study.programmers;

import java.io.*;
import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1845
 *
 * 폰켓몬
 */
public class Programmers1845 {

    public static int solution(int[] nums) {
        int max = nums.length / 2;

        // 중복 제거
        Set<Integer> set = new HashSet<>();
        for (int n : nums){
            set.add(n);
        }

        if(max > set.size()){
            max = set.size();
        }

        return max;
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
