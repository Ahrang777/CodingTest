package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PyTLqAf4DFAUq&categoryId=AV5PyTLqAf4DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * 초심자의 회문 검사
 */
public class SWEA1989 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int n = str.length();
            boolean isPalindrome = true;
            for (int i = 0; i < n/2; i++) {
                if (str.charAt(i) != str.charAt(n-1-i)) {
                    isPalindrome = false;
                    break;
                }
            }

            if (isPalindrome) System.out.printf("#%d %d\n", tc, 1);
            else System.out.printf("#%d %d\n", tc, 0);
        }
    }
}
