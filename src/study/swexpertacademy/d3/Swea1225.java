package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14uWl6AF0CFAYD&categoryId=AV14uWl6AF0CFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * [S/W 문제해결 기본] 7일차 - 암호생성기
 */
public class Swea1225 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }

            int value = 1;
            while (value != 0) {
                for (int i = 1; i <= 5; i++) {
                    value = q.poll();
                    value -= i;
                    if (value < 0)	value = 0;
                    q.offer(value);
                    if (value ==0)	break;
                }
            }

            sb.append("#" + T + " ");
            for (int i = 0; i < 8; i++) {
                sb.append(q.poll() + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
