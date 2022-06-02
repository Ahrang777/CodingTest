package sort.ch14;

import java.io.*;
import java.util.*;

/*
입력
12
Junkyu 50 60 100
Sangkeun 80 60 50
Sunyoung 80 70 100
Soong 50 60 90
Haebin 50 60 100
Kangsoo 60 80 100
Donghyuk 80 60 100
Sei 70 70 70
Wonseob 70 70 90
Sanghyun 70 70 80
nsj 80 80 80
Taewhan 50 60 90

출력
Donghyuk
Sangkeun
Sunyoung
nsj
Wonseob
Sanghyun
Sei
Kangsoo
Haebin
Junkyu
Soong
Taewhan
 */

/**
 * https://www.acmicpc.net/problem/10825
 */
public class Ex1 {

    static class Student implements Comparable<Student> {

        private String name;
        private int kor;
        private int eng;
        private int math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Student other) {

            if (this.kor != other.kor) {    //내림차순으로
                return Integer.compare(other.kor, this.kor);
            } else if (this.eng != other.eng) { //오름차순으로
                return Integer.compare(this.eng, other.eng);
            } else if (this.math != other.math) {
                return Integer.compare(other.math, this.math);
            } else {
                return this.name.compareTo(other.name);
            }

            /*if (this.kor == other.kor && this.eng == other.eng && this.math == other.math) {
                return this.name.compareTo(other.name);
            }
            if (this.kor == other.kor && this.eng == other.eng) {
                return Integer.compare(other.math, this.math);
            }
            if (this.kor == other.kor) {
                return Integer.compare(this.eng, other.eng);
            }
            return Integer.compare(other.kor, this.kor);*/
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
            String name = stk.nextToken();
            int kor = Integer.parseInt(stk.nextToken());
            int eng = Integer.parseInt(stk.nextToken());
            int math = Integer.parseInt(stk.nextToken());

            students.add(new Student(name, kor, eng, math));
//            students[i] = new Student(name, kor, eng, math);
        }

//        Arrays.sort(students);

        Collections.sort(students);

        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
