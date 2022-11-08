package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AXWXH_h695kDFAST&categoryId=AXWXH_h695kDFAST&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=16
 * <p>
 * 제곱수 만들기
 * <p>
 * 다시 풀기
 */
public class Swea10965 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int maxNum = (int) Math.pow(10, 7) + 1;
        List<Integer> prime = new ArrayList<>();    // 소수 저장 >> 작은 값부터 순서대로 저장

        boolean[] isPrime = new boolean[maxNum];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i * i < maxNum; i++) {
            if (!isPrime[i]) {
                prime.add(i);
                for (int j = i * i; j < maxNum; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        List<Integer> sq = new ArrayList<>();   // 소수² 저장 >> 작은 값부터 순서대로 저장
        for (Integer i : prime) {
            sq.add(i * i);
        }

        for (int tc = 1; tc <= T; tc++) {
            int a = Integer.parseInt(br.readLine());

            for (Integer divNum : sq) {
                if (a < divNum) break;  // 가장 작은 소수² 보다 작으면 해당 값을 제곱하는게 최솟값 >> 1, 2, 3 생각

                // a 에서 가능한 소수² 들은 모두 나누고 남은 값만큼만 곱하면 제곱형태가 된다. >> 남은 값이 최솟값
                // ex) 3 x 6² >> / 2² : 3 x 3² >> / 3² : 3 >>>> 따라서 a에 3만큼만 곱하면 제곱형태가 된다.
                while (true) {
                    if (a % divNum == 0) a /= divNum;
                    else break;
                }
            }

            sb.append("#" + tc + " " + a + "\n");
        }
        System.out.println(sb);
        br.close();

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int maxNum = (int) Math.pow(10, 7) + 1;
        ArrayList<Integer> primeArr = new ArrayList<>();
        int sqrt = (int) Math.sqrt(maxNum)+1;
        boolean[] isPrime = new boolean[sqrt+1];
        for (int i = 2; i <= sqrt; i++) {
            if(isPrime[i]) continue;
            primeArr.add(i);
            for (int j = 1; j*i <= sqrt; j++) {
                isPrime[j*i] = true;
            }
        }

        ArrayList<Integer> squArr = new ArrayList<>();
        for (Integer i : primeArr) {
            squArr.add(i*i);
        }

        for (int tc = 1; tc <= T; tc++) {
            int a = Integer.parseInt(br.readLine());

            for (int i = 0; i <= sqrt; i++) {
                int divnum = squArr.get(i);
                if(a < divnum) break;

                while(true) {
                    if(a%divnum == 0) a /= divnum;
                    else break;
                }
            }

            sb.append("#"+tc+" "+a+"\n");
        }
        System.out.println(sb);
        br.close();*/
    }

    /*
    public static final int MAX = 10000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] arr = new int[MAX];
        int pos = 0;

        // false가 소수
        boolean[] prime = new boolean[MAX + 1];
        prime[0] = prime[1] = true;
        for (int i = 2; i * i <= MAX; i++) {
            if (!prime[i]) {
                arr[pos++] = i;
                for (int j = i * i; j <= MAX; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int tc = 1; tc <= T; tc++) {
            int A = Integer.parseInt(br.readLine());

            if (!prime[A]) {
                System.out.printf("#%d %d\n", tc, A);
                continue;
            }

            int index = 0;
            int B = 1;
            int cnt = 0;
            while (A > 1) {
                if (A % arr[index] == 0) {
                    cnt++;
                    A /= arr[index];
                }

                if (A % arr[index] != 0) {
                    if (cnt % 2 != 0) {
                        B *= arr[index];
                    }
                    if (!prime[A]) {
                        B *= A;
                        break;
                    }
                    index++;
                    if (index == pos) {
                        B *= A;
                        break;
                    }

                    cnt = 0;
                }
            }

            System.out.printf("#%d %d\n", tc, B);
        }
    }
     */
}
