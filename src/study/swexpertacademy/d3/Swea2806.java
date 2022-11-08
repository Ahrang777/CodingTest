package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV7GKs06AU0DFAXB&categoryId=AV7GKs06AU0DFAXB&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * N-Queen
 * 다시 풀기
 */
public class Swea2806 {

    static int cnt;
    static int N;
    static int[] arr;	// 인덱스: 열, 원소값: 행

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            cnt = 0;
            arr = new int[N];
            dfs(0);

            System.out.printf("#%d %d\n", tc, cnt);
        }
    }

    public static void dfs(int depth) {
        if(depth == N){
            cnt++;
            return;
        }

        // depth 번째 열, i번째 행에 퀸을 놓는 경우
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (isPossible(depth)) {
                dfs(depth + 1);
            }
        }

    }

    public static boolean isPossible(int col) {
        // 이전 열에 대해서는 모두 자리잡은 상태
        for (int i = 0; i < col; i++) {
            // 같은 행에 존재하는 경우
            if (arr[col] == arr[i])	return false;

                // 대각선 상에 놓인 경우 >> 열 이동거리, 행 이동거리 같으면 대각선으로 이동
                // ex) +1, -1 : 대각션, +2, -2 : 대각션
            else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i]))	return false;
        }

        return true;
    }
}
