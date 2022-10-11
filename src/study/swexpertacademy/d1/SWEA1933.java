package study.swexpertacademy.d1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=1&problemLevel=2&contestProbId=AV5PhcWaAKIDFAUq&categoryId=AV5PhcWaAKIDFAUq&categoryType=CODE&problemTitle=1933&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * 간단한 N 의 약수
 */
public class SWEA1933 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> res = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if ( n % i == 0) {
                if (i * i != n)	res.add(n/i);
                res.add(i);
            }
        }

        Collections.sort(res);
        for (int result : res) {
            System.out.print(result + " ");
        }
    }
}
