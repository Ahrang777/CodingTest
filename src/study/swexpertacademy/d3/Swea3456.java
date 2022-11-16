package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWFPmsqqALwDFAV0&categoryId=AWFPmsqqALwDFAV0&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 직사각형 길이 찾기
 */
public class Swea3456 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            int result = 0;
            for (int i = 0; i < 3; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            boolean check = true;
            result = list.get(0);
            for (int i = 1; i < 3; i++) {
                if (list.get(0) == list.get(i)) {
                    check = false;
                } else {
                    result = list.get(i);
                }
            }
            if (check) result = list.get(0);

            /*Collections.sort(list);
            if (list.get(0) != list.get(1)) {
                result = list.get(0);
            } else {
                result = list.get(2);
            }*/
            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb);
        br.close();
    }
}
