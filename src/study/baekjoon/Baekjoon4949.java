package study.baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon4949 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> inputs = new ArrayList<>();
        Stack<Character> s = new Stack<>();

        while (true) {
            String input = br.readLine();
            if (input.charAt(0) == '.') {
                break;
            }

            inputs.add(input);
        }

        for (String in : inputs) {
            s.clear();

            for (int i = 0; i < in.length(); i++) {
                char ch = in.charAt(i);
                if (ch == '(' || ch == '[') {
                    s.push(ch);
                } else if (ch == ')' || ch == ']') {
                    if (s.isEmpty() || ch == ')' && s.peek() != '(' || ch == ']' && s.peek() != '[') {
                        s.push(ch);
                        break;
                    }

                    s.pop();
                }
            }

            if (s.isEmpty()) {
                System.out.println("yes");
            }else
                System.out.println("no");
        }

    }
}
