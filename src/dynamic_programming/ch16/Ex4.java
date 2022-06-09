package dynamic_programming.ch16;

import java.io.*;
import java.util.*;

/*
입력
7
15 11 4 8 5 2 4

출력
2
 */

/**
 * https://www.acmicpc.net/problem/18353
 *
 * 병사 배치하기
 */
public class Ex4 {

    public static int n;
    public static ArrayList<Integer> v = new ArrayList<Integer>();
    public static int[] dp = new int[2000];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            v.add(Integer.parseInt(stk.nextToken()));
        }

        // 순서를 뒤집어 '최장 증가 부분 수열(LIS)' 문제로 변환
        Collections.reverse(v);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // 가장 긴 증가하는 부분 수열(LIS) 알고리즘 수행
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (v.get(j) < v.get(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }
        System.out.println(n - maxValue);
    }

//    static int N;
//    static int[] dp;
//    static int answer;
//    static List<Integer> list = new ArrayList<>();
//
//    public static void main(String[] args) {
//        input();
//        solution();
//        System.out.println(getAnswer());
//    }
//
//    static void input() {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        for (int i = 0; i < N; i++) {
//            list.add(sc.nextInt());
//        }
//    }
//
//    static void solution() {
//        dp = new int[list.size()];
//
//        Collections.reverse(list);
//
//        Arrays.fill(dp, Integer.MAX_VALUE);
//        dp[0] = list.get(0);
//
//        int dpIndexByLastUpdated = 0;
//        for (int i = 1; i < list.size(); i++) {
//            int next = list.get(i);
//            int lastValueInDp = dp[dpIndexByLastUpdated];
//            if(lastValueInDp < next) {
//                dp[++dpIndexByLastUpdated] = next;
//            } else {
//                int findIndex = binarySearch(0, dpIndexByLastUpdated, next);
//                dp[findIndex] = next;
//            }
//        }
//    }
//
//    /**
//     * 이진 탐색을 통해서 next 가 dp 의 어느 index 에 들어가야하는지, 알맞은 index 를 찾는다.
//     * @param start 0
//     * @param end dpIndexByLastUpdated
//     * @param target 비교 대상인 다음 값(next)
//     */
//    static int binarySearch(int start, int end, int target) {
//        int index = 0;
//        while(start <= end) {
//            int middle = (start + end) / 2;
//
//            if(dp[middle] >= target) {
//                end = middle - 1;
//                index = middle;
//            }
//            else{
//                start = middle + 1;
//            }
//        }
//        return index;
//    }
//
//    /**
//     * dp 에 Integer.MAX_VALUE 가 아닌 값들에 대해서 개수를 카운트한다.
//     */
//    static int getAnswer() {
//        int count = 0;
//        for(int i = 0; i < dp.length; i++) {
//            if(dp[i] == Integer.MAX_VALUE) {
//                break;
//            }
//            count++;
//        }
//        answer = N - count;
//        return answer;
//    }
}
