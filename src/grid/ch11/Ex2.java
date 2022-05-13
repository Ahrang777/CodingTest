package grid.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
입력1
02984

출력1
576
===========
입력2
567

출력2
210
 */
public class Ex2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        //앞뒤로 연산자 넣어야 하는데 첫번째 값은 앞에 연산자 넣을 수 없어서
        int result = s.charAt(0) - '0';

        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            
            //0, 1의 격우 더하는게 곱하는 것보다 크다
            if (num <= 1 || result <= 1) {
                result += num;
            } else {
                result *= num;
            }
        }

        System.out.println(result);
    }
}
