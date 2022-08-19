package algorithm;

import java.io.BufferedReader;

import java.io.*;
import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12921
 *
 * 소수 구하기: 에라토스테네스의 체
 */
public class PrimeNumber {

    static int findPrimeNumber(int n) {
        /*int answer = 0;
        boolean[] prime = new boolean[n + 1];
        for(int i=2; i<=n ; i++)
            prime[i]=true; //2~n번째수를 true로 초기화

        //제곱근 구하기
        int root=(int)Math.sqrt(n);
        for(int i=2; i<=root; i++){ //2~루트n까지 검사
            if(prime[i]==true){ //i번째의 수가 소수일 때
      	        for(int j=i; i*j<=n; j++) //그 배수들을 다 false로 초기화(배수는 소수가 아니기 때문)
                    prime[i*j]=false;
            }
        }

        for(int i =2; i<=n; i++) {
            if(prime[i]==true)
                answer++;
        }
        return answer;*/

        int answer = 0;
        // 소수는 false, 소수가 아닌 경우 true
        boolean[] prime = new boolean[n + 1];
        prime[0] = prime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            // prime[i]가 소수라면
            if (!prime[i]) {
                // i배수는 소수가 아닌 처리, i * i 이전까지는 이미 처리한것
                // 예를 들면 i = 3일때 8까지는 이미 처리가 끝남
                // 2에서 2, 4, 6, 8 처리함 3, 5, 7은 소수
                // i * i 이전까지는 소수 아닌것들에 대한 처리가 끝난것
                for (int j = i * i; j <= n; j += i)
                    prime[j] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!prime[i]) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(findPrimeNumber(n));
    }
}
