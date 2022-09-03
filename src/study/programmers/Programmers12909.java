package study.programmers;

public class Programmers12909 {

    /*boolean solution(String s) {
        boolean answer = true;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(1);
            } else if (s.charAt(i) == ')') {
                if (st.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    st.pop();
                }
            }
        }

        if(!st.isEmpty()) {

            answer = false;

        }

        return answer;
    }*/

    static boolean solution(String s) {
        boolean answer = true;

        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')  cnt++;
            else if(ch == ')')  cnt--;

            if (cnt < 0) {
                break;
            }
        }

        if (cnt != 0)   answer = false;

        return answer;
    }

    public static void main(String[] args) {
        String[] s = {
                "()()",
                "(())()",
                ")()(",
                "(()("
        };

        for (int i = 0; i < s.length; i++) {
            System.out.println(solution(s[i]));
        }
    }
}
