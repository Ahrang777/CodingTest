package dfs_bfs.ch5;

import java.util.Stack;

public class Ex1Stack {
    public static void main(String[] args) {

        Stack<Integer> s = new Stack<>();

        // 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제()
        s.push(5);
        s.push(2);
        s.push(3);
        s.push(7);
        s.pop();
        s.push(1);
        s.push(4);
        s.pop();

//        while(!s.empty()){
        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();
        }
    }
}
