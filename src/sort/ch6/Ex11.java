package sort.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력
 * 2
 * 홍길동 95
 * 이순신 77
 *
 * 출력
 * 이순신 홍길동
 */
public class Ex11 {

    public static class Student implements Comparable<Student> {

        private String name;
        private int score;

        public Student(String name, int score){
            this.name = name;
            this.score = score;
        }

        public String getName(){
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Student other){
            //if(this.score < )
            return (score < other.score ? -1 : (score > other.score ? 1 : 0));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(bf.readLine());

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            students.add(new Student(stk.nextToken(), Integer.parseInt(stk.nextToken())));
        }

        /*for (Student student : students) {
            System.out.println(student.getName() + ", " + student.getScore());
        }*/

        Collections.sort(students);

        for (int i = 0; i < students.size(); i++) {
            System.out.print(students.get(i).getName() + " ");
        }
    }
}
