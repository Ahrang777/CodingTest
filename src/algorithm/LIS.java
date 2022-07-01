package algorithm;

import java.util.Arrays;

/**
 * O(N²)
 *
 * 다이나믹 프로그래밍 이용한 LTS
 *
 * LIS(Longest Increasing subsequence)
 * 가장 긴 증가하는 부분 수열
 *
 * 하나의 수열이 주어졌을 때 값들이 증가하는 형태의 가장 긴 부분 수열을 찾는 것
 */
public class LIS {

    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30, 20, 50};
        int n = arr.length;
        int[] dp = new int[n];


        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //10, 20, 30, 50 이 가장 긴 부분 수열
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }
        System.out.println(maxValue);
    }
}
