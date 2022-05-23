package implementation.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://www.acmicpc.net/problem/18406

입력1
123402

출력1
LUCKY
==========
입력2
7755

출력2
READY

 */
public class Ex1 {

    public static String str;
    public static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str = bf.readLine();

        for(int i=0;i<str.length()/2;i++){
            int n = str.charAt(i) - '0';
            sum += n;
        }

        for(int i = str.length()/2; i < str.length(); i++) {
            int n = str.charAt(i) - '0';
            sum -= n;
        }

        if(sum==0)    System.out.println("LUCKY");
        else    System.out.println("READY");
    }
}
