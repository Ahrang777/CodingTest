package grid.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
입력
0001100

출력
1
 */
public class Ex3 {

    public static String s;
    /*public static int cntTo0 = 0;   // 1->0
    public static int cntTo1 = 0;   // 0->1*/

    public static int count0 = 0;   //전부 0으로 바꾸는 경우
    public static int count1 = 0;   //전부 1로 바꾸는 경우

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();

        // 첫 번째 원소에 대해서 처리
        if (s.charAt(0) == '1') {
            count0 += 1;
        }
        else {
            count1 += 1;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                if (s.charAt(i + 1) == '1') {
                    count0++;
                } else {
                    count1++;
                }
            }
        }

        System.out.println(Math.min(count0, count1));
        
        /*for (int i = 0; i < s.length() -1 ; i++) {
            int now = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';

            if ((now - next) < 0) {
                cntTo1++;
            } else if ((now - next) > 0) {
                cntTo0++;
            }
        }

        System.out.println(Math.min(cntTo0, cntTo1));*/
    }
}
