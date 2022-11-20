package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYCnY9Kqu6YDFARx&categoryId=AYCnY9Kqu6YDFARx&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=6
 * 
 * 숫자가 같은 배수
 *
 * TODO 다시 풀기
 */
public class Swea14361 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int N = Integer.parseInt(str);
            String result = "impossible";
            int i = 1;

            while (true) {
                i++;
                int number = N * i; // 배수
                String numStr = String.valueOf(number);

                if (numStr.length() > str.length()) {
                    break;
                }

                if (check(str, numStr)) {
                    result = "possible";
                    break;
                }
            }

            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    // 원본, 배수
    public static boolean check(String num1, String num2) {
        for (int i = 0; i < num1.length(); i++) {
            int index = num2.indexOf(num1.charAt(i));

            if (index == -1) {
                return false;
            }

            num2 = num2.substring(0, index) + num2.substring(index + 1);

        }

        return true;
    }
}
