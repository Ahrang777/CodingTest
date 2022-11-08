package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV15FZuqAL4CFAYD&categoryId=AV15FZuqAL4CFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
 */
public class Swea1240 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        String[] numbers = {
                "0001101",
                "0011001",
                "0010011",
                "0111101",
                "0100011",
                "0110001",
                "0101111",
                "0111011",
                "0110111",
                "0001011"
        };

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            String str = "";
            String number = "";

            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            for (int i = 0; i < N; i++) {
                if (arr[i].lastIndexOf("1") != -1) {
                    str = arr[i];
                    break;
                }
            }

            int len = str.lastIndexOf("1") + 1;

            sb.append("#" + tc + " ");

            for (int i = 0; i < 8; i++) {
                String tmp = str.substring(len - 7, len);
                for (int j = 0; j <= 9; j++) {
                    if (numbers[j].equals(tmp)) {
                        number += j;
                        break;
                    }
                }
                len -= 7;
            }

            int sum = 0;
            int res = 0;
            for (int i = 0; i < 8; i++) {
                int n = number.charAt(i) - '0';
                res += n;

                if (i % 2 == 0) {
                    sum += n;
                } else {
                    sum += 3 * n;
                }
            }

            if (sum % 10 != 0) {
                res = 0;
            }

            sb.append(res + "\n");
        }

        System.out.println(sb);
    }
}
