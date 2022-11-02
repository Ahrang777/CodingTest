package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14_DEKAJcCFAYD&categoryId=AV14_DEKAJcCFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3&&&&&&&&&&&&&&&&&&&&
 *
 * [S/W 문제해결 기본] 10일차 - 비밀번호
 */
public class SWEA1234 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            String number = st.nextToken();

            sb.append("#" + tc + " " + getPassword(number, size) + "\n");
        }
        System.out.println(sb);

    }

    public static String getPassword(String number, int size) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            if (stack.isEmpty()) {
                stack.push(number.charAt(i));
                continue;
            }

            if (stack.peek() == number.charAt(i)) {
                stack.pop();
            } else {
                stack.push(number.charAt(i));
            }
        }

        String password = "";
        while (!stack.isEmpty()) {
            password = stack.pop() + password;
        }

        return password;
    }

    /*
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            String inputs = br.readLine().split(" ")[1];

            char prev = '\0';
//          System.out.println(prev == '\0');
            int idx = 0;
            while(true) {

                if(inputs.length() == idx) break;

                if(prev == '\0') {
                    prev = inputs.charAt(idx++);
                    continue;
                }

                if(prev == inputs.charAt(idx)) {
                    inputs = inputs.substring(0, idx-1) + inputs.substring(idx+1);
                    idx = 0;
                    prev = '\0';
                } else {
                    prev = inputs.charAt(idx++);
                }

//              System.out.println(inputs + " " + idx);
            }

            System.out.printf("#%d %s\n",test_case, inputs);
        }
    }
     */
}
