package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWpMsQfaCPMDFAQi&categoryId=AWpMsQfaCPMDFAQi&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 자가 복제 문자열
 */
public class Swea7584 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            Long K = Long.parseLong(br.readLine()) - 1;
            int result = 0;
            while (K > 0) {
                if (K % 2 == 1) {
                    K = (K - 1) / 2;
                }
                if (K % 4 == 0) {
                    result = 0;
                    break;
                } else if (K % 2 == 0) {
                    result = 1;
                    break;
                }
            }
            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
