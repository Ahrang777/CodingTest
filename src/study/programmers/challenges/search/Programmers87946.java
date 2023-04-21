package study.programmers.challenges.search;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 *
 * 피로도
 */
public class Programmers87946 {
    /*
    int dfs(int k, int[][] dungeons) {
        int cnt = 0;
        for(int[] d : dungeons) {
            int a = d[0], b = d[1];
            if(a <= k) {
                d[0] = 9999;
                cnt = Math.max(1 + dfs(k - b, dungeons), cnt);
                d[0] = a;
            }
        }
        return cnt;
    }
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        return dfs(k, dungeons);
    }
     */

    public static int answer = 0;
    public static boolean[] visited;

    public static int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];

        dfs(k, dungeons, 0);
        return answer;
    }


    // 더이상 안될때까지 최대한 깊게 파내려가고 최대한 깊게 간 값과 answer 비교해서 max 갱신
    public static void dfs(int tired, int[][] dungeons, int depth){

        for (int i = 0; i < dungeons.length; i++){
            if (!visited[i] && tired >= dungeons[i][0]){
                visited[i] = true;
                dfs(tired - dungeons[i][1], dungeons, depth + 1);
                visited[i] = false;
            }
        }

        answer = Math.max(answer, depth);
    }

    /*
    int dfs(int k, int[][] dungeons) {
        int cnt = 0;
        for(int[] d : dungeons) {
            int a = d[0], b = d[1];
            if(a <= k) {
                d[0] = 9999;
                cnt = Math.max(1 + dfs(k - b, dungeons), cnt);
                d[0] = a;
            }
        }
        return cnt;
    }

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        return dfs(k, dungeons);
    }
     */

    /*
    static boolean[] visited;
    static int answer = 0;

    public int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];

        permutation(dungeons, new int[dungeons.length], 0, k);

        return answer;
    }

    public int getResult(int[][] dungeons, int[] output, int k) {
        int cnt = 0;

        for (int i : output) {
            if (k < dungeons[i][0]) break;

            k -= dungeons[i][1];
            cnt ++;
        }

        return cnt;
    }

    public void permutation(int[][] dungeons, int[] output, int depth, int k){
        if (depth == dungeons.length){

            answer = Math.max(answer, getResult(dungeons, output, k));

            return;
        }

        for (int i = 0; i < dungeons.length; i++){
            if (!visited[i]){
                visited[i] = true;
                output[depth] = i;
                permutation(dungeons, output, depth+1, k);
                visited[i] = false;
            }
        }
    }
     */

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        System.out.println(solution(k, dungeons));
    }
}
