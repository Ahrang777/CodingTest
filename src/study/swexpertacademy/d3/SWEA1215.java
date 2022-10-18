package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14QpAaAAwCFAYi&categoryId=AV14QpAaAAwCFAYi&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 3일차 - 회문1
 */
public class SWEA1215 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());

            char[][] arr = new char[8][8];
            for (int i = 0; i < 8; i++) {
                String str = br.readLine();
                for (int j = 0; j < 8; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            int cnt = 0;
            // 가로
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= 8 - N; j++) {
                    String row = "";
                    String col = "";
                    for (int k = 0; k < N; k++) {
                        row += arr[i][j+k];
                        col += arr[j+k][i];
                    }
                    if (isPalindrome(row))	cnt++;
                    if (isPalindrome(col))	cnt++;
                }
            }

            System.out.printf("#%d %d\n", tc, cnt);

        }

    }

    public static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len/2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i))	return false;
        }

        return true;

    }
}
