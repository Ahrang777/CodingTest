package study.swexpertacademy.d2;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PwGK6AcIDFAUq&categoryId=AV5PwGK6AcIDFAUq&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * 조교의 성적 매기기
 */
public class SWEA1983 {
    static class Student implements Comparable<Student> {
        int index;
        double score;

        public Student(int index, double score){
            this.index = index;
            this.score = score;
        }

        public int compareTo(Student other) {
            return Double.compare(other.score, this.score);
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        String[] arr = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<Student> list = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                double score = a * 0.35 + b * 0.45 + c * 0.2;
                list.add(new Student(i, score));
            }

            Collections.sort(list);
            int cnt = 0;
            int index = 0;
            for (Student s : list) {
                if (cnt >= N/10) {
                    index++;
                    cnt = 0;
                }
                if (s.index == K) {
                    System.out.printf("#%d %s\n", tc, arr[index]);
                }
                cnt++;
            }
        }
    }
}
