package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5P0-h6Ak4DFAUq&categoryId=AV5P0-h6Ak4DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * 파스칼의 삼각형
 */
public class Swea2005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[][] arr = new int[N][N];
            System.out.println("#" + tc);
            arr[0][0] = 1;
            for (int i = 1; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j ==0 || j == i)	arr[i][j] = 1;
                    else arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
