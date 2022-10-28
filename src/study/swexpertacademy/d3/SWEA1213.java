package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14P0c6AAUCFAYi&categoryId=AV14P0c6AAUCFAYi&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * [S/W 문제해결 기본] 3일차 - String
 */
public class SWEA1213 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            String target = br.readLine();
            String str = br.readLine();
            int cnt = 0;

            while (str.contains(target)) {
                str = str.substring(str.indexOf(target) + target.length());
                cnt++;
            }

            sb.append("#" + tc + " " + cnt + "\n");
        }
        System.out.println(sb);
    }

    /*public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            String target = br.readLine();
            String str = br.readLine();

            int cnt = check(target, str, 0);

            sb.append("#" + tc + " " + cnt + "\n");
        }
        System.out.println(sb);
    }*/

    public static int check(String target, String str, int cnt) {
        if (target.length() > str.length())	return cnt;

        if (str.startsWith(target))	return check(target, str.substring(1), cnt + 1);
        else return check(target, str.substring(1), cnt);
    }
}
