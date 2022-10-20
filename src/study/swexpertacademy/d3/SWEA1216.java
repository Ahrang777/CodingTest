package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14Rq5aABUCFAYi&categoryId=AV14Rq5aABUCFAYi&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * [S/W 문제해결 기본] 3일차 - 회문2
 * 다시 풀기
 */
public class SWEA1216 {
    static char[][] arr;
    static final int L = 100;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            arr = new char[L][L];

            for (int i = 0; i < L; i++) {
                String str = br.readLine();
                for (int j = 0; j < L; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            for (int i = L; i > 0; i--){
                if (solve(i)) {
                    sb.append("#" + T + " " + i + "\n");
                    break;
                }
            }
        }

        System.out.println(sb);
        br.close();
    }

    public static boolean solve(int l) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j <= (L - l); j++) {	// 시작기준점
                if(solveRow(i, j, l) || solveColumn(j, i, l) ) return true;
            }
        }

        return false;
    }

    // 가로
    public static boolean solveRow(int i, int j, int l) {
        for (int k = 0; k < l / 2; k++) {
            if(arr[i][j + k] != arr[i][j + l - 1 - k]) return false;
        }
        return true;
    }

    // 세로
    public static boolean solveColumn(int i, int j, int l) {
        for (int k = 0; k < l / 2; k++) {
            if(arr[i + k][j] != arr[i + l - 1 - k][j]) return false;
        }
        return true;
    }
}
