package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60058
 *
 * 괄호 변환
 */
public class Ex4 {

    public static String str;

    public static int balanceIndex(String w) {
        //index까지 하나의 균형잡힌 괄호 >> 이를 기준으로 나누기
        int index = 0;
        int count = 0;
        for (int i = 0; index < w.length(); i++) {
            char ch = w.charAt(i);

            if(ch=='(')
                count++;
            else
                count--;
            if(count==0){
                index = i;
                break;
            }
        }
        return index;
    }

    public static String solution(String p) {
        String answer = "";
        if(p.equals("")) return "";

        int index = balanceIndex(p);

        String u = p.substring(0, index + 1);
        String v = p.substring(index + 1);

        //이미 u는 균형잡힌 괄호니까 ( 시작인것만 확인
        //u가 올바른 괄호 문자열 >> 3-1
        if (u.startsWith("(")) {
            answer = u + solution(v);
        } else {   //u가 올바른 괄호 문자열x 4
            answer = "(" + solution(v) + ")";
            u = u.substring(1, u.length() - 1);
            for (int i = 0; i < u.length(); i++) {
                if(u.charAt(i)=='(')
                    answer += ")";
                else
                    answer += "(";
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        str = bf.readLine();

        System.out.println(solution(str));
    }
}
