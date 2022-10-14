package study.swexpertacademy.d2;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PnnU6AOsDFAUq&categoryId=AV5PnnU6AOsDFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=2
 *
 * 날짜 계산기
 */
public class SWEA1948 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int[] date = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int M1 = Integer.parseInt(st.nextToken());
            int D1 = Integer.parseInt(st.nextToken());
            int M2 = Integer.parseInt(st.nextToken());
            int D2 = Integer.parseInt(st.nextToken());
            int res = 0;

            if (M1 == M2) res = D2 - D1 + 1;
            else {
                res += (date[M1] - D1 + D2 + 1);
                for (int i = M1 + 1; i < M2; i++) {
                    res += date[i];
                }
            }

            System.out.printf("#%d %d\n", tc, res);
        }
    }
}
