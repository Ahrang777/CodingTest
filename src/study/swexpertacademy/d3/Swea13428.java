package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AX4EJPs68IkDFARe&categoryId=AX4EJPs68IkDFARe&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 숫자 조작
 */
public class Swea13428 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String M = br.readLine();
            char[] arr = M.toCharArray();
            int maxValue = Integer.parseInt(M);
            int minValue = Integer.parseInt(M);

            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    char tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;

                    if (arr[0] != '0' && Integer.parseInt(String.valueOf(arr)) < minValue) {
                        minValue = Integer.parseInt(String.valueOf(arr));
                    }
                    if (arr[0] != '0' && Integer.parseInt(String.valueOf(arr)) > maxValue) {
                        maxValue = Integer.parseInt(String.valueOf(arr));
                    }

                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            sb.append("#" + tc + " " + minValue + " " + maxValue + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
