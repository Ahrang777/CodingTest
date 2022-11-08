package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AXZuaLsqz9wDFAST&categoryId=AXZuaLsqz9wDFAST&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=4
 *
 * 다트 게임
 */
public class Swea11285 {
    static double[] radius = {Math.pow(20, 2),
            Math.pow(40, 2), Math.pow(60, 2), Math.pow(80, 2),
            Math.pow(100, 2), Math.pow(120, 2), Math.pow(140, 2),
            Math.pow(160, 2), Math.pow(180, 2), Math.pow(200, 2)};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int score = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());

                double distance = Math.pow(x, 2) + Math.pow(y, 2);

                for (int j = 0; j < radius.length; j++) {
                    if (distance <= radius[j]) {
                        score += (10 - j);
                        break;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(score).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
