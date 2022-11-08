package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWXGKdbqczEDFAUo&categoryId=AWXGKdbqczEDFAUo&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=3
 *
 * [Professional] 조합
 * TODO [re] 다시 풀기
 *
 * 참고
 * https://minkwon4.tistory.com/25
 * https://diane21.tistory.com/44
 *
 * TODO 페르마의 소정리
 */
public class Swea5607 {
    /*
    static long N,R;
	static long p = 1234567891;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			R = sc.nextInt();

			long a = 1;
			long b = 1;

			long t = Math.min(R, N-R);

			for(int i=0; i<t; i++) {
				a = a*(N-i)%p;
				b = b*(t-i)%p;
			}
			long ans = (a%p*div(b,p-2)%p)%p;

			System.out.println("#"+tc+" "+ans);
		}
	}

	static long div(long a, long b) {
		if(b==1)
			return a;
		long tmp = div(a, b/2);
		if(b%2 == 1)
			return tmp*tmp%p*a%p;
		else
			return tmp*tmp%p;
	}
     */

    static int mod = 1234567891;
    static int N, R;
    static long[] factorial;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        factorial = new long[1000000 + 1];
        factorial[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            factorial[i] = (factorial[i - 1] * i) % mod;        //factorial 초기화
        }

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long top = factorial[N] % mod;
            long bottom = ((factorial[N - R] % mod) * (factorial[R] % mod)) % mod;

            long moveToTop = powCalc(bottom,mod-2);

            sb.append("#" + tc + " " + ((top * moveToTop) % mod) + "\n");
        }
        System.out.println(sb);
    }

    public static long powCalc(long num, int p) {
        if(p==0)
            return 1;

        long half = powCalc(num, p/2);

        if(p%2==0)
            return ((half % mod) * (half % mod)) % mod;
        else
            return (((half * num) % mod) * (half % mod)) % mod;
    }

    /**
     * 모듈러연산의 특징
     * 모듈려는 /을 제외한 +, -, * 에 대해서 다음과 같은 특징을 가진다.
     * (a mod n + b mod n) mod n = (a + b) mod n
     * (a mod n - b mod n) mod n = (a - b) mod n
     * (a mod n * b mod n) mod n = (a * b) mod n
     *
     *
     * 합동관계
     * 두 a, b숫자가 n을 mod한 결과 값이 같다면 모듈러 합동관계라 한다.
     * 즉, a mod n = b mod n의 관계를 a ≡ b (mod n)으로 표현할 수 있다.
     *
     *
     * 페르마의 소정리
     * p가 소수이고 a가 p로 나누어지지 않는 정수이면 다음과 같은 특징을 가진다.
     * a^p ≡ a (mod p)
     * a^(p-1) ≡ 1 (mod p)
     * a^(p-2) ≡ a^-1 (mod p)
     *
     *
     * 조합
     * Combination, 서로 다른 n개에서 r개를 뽑는 경우의 수이다.
     * nCr = n! / ((n-r)! * r!)
     * nCr = (n-1)C(r-1) + (n-1)Cr
     *
     *
     * 거듭제곱의 분할정복
     * a^n 을 구할때 다음과 같이 구할 수 있다.
     * IF n%2==0, a^n = a^(n/2) * a^(n/2) = ...
     * IF n%2!=0, a^n = a * a^(n/2) * a^(n/2) = ...
     *
     * 문제로 돌아와 예로써 7C5 mod 13 을 해보기로 가정한다면, 위의 특징들을 사용하여 연산을 한다면 다음과 같다.
     * 7C5 mod 13
     * = (7! mod 13 / (2! mod 13) * (5! mod 13)) mod 13
     * = (7! mod 13 / 240 mod 13) mod 13
     * = (7! mod 13 / 6 mod 13) mod 13 이 된다.
     */
}
