package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5Pw_-KAdcDFAUq&categoryId=AV5Pw_-KAdcDFAUq&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * SW Expert Academy
 * 중간 평균값 구하기
 */
public class Swea1984 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int[] arr = new int[10];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int sum = 0;
            int avg = 0;
            for (int i = 1; i < 9; i++)	sum += arr[i];

            if (sum != 0) {
                avg = (int) Math.round(sum / 8.0);
            }

            System.out.printf("#%d %d\n", tc, avg);
        }
    }
}
