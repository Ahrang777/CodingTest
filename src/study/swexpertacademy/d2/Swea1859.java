package study.swexpertacademy.d2;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5LrsUaDxcDFAXc&categoryId=AV5LrsUaDxcDFAXc&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * SW Expert Academy
 * 백만 장자 프로젝트
 */
public class Swea1859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int maxValue = 0;
            long diff = 0;
            for (int i = n - 1; i>=0; i--) {
                if (maxValue < arr[i]) maxValue = arr[i];
                diff += maxValue - arr[i];
            }

            System.out.printf("#%d %d\n", test_case, diff);
        }
    }
}
