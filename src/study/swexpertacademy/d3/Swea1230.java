package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14zIwqAHwCFAYD&categoryId=AV14zIwqAHwCFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * [S/W 문제해결 기본] 8일차 - 암호문3
 */
public class Swea1230 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            List<String> cryption = new LinkedList<>();
            int pos;
            int count;

            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cryption.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String cmd = st.nextToken();

                if (cmd.equals("I")) {
                    insert(cryption, st);
                } else if (cmd.equals("D")) {
                    delete(cryption, st);
                } else if (cmd.equals("A")) {
                    add(cryption, st);
                }
            }

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < 10; i++) {
                sb.append(cryption.get(i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void insert(List<String> cryption, StringTokenizer st) {
        int pos;
        int count;
        pos = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        for (int i = 0; i < count; i++) {
            cryption.add(pos + i, st.nextToken());
        }
    }

    private static void delete(List<String> cryption, StringTokenizer st) {
        int pos;
        int count;
        pos = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        for (int i = 0; i < count; i++) {
            cryption.remove(pos);
        }
    }

    private static void add(List<String> cryption, StringTokenizer st) {
        int count;
        count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < count; i++) {
            cryption.add(st.nextToken());
        }
    }
}
