package study.swexpertacademy.d3;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV_6mRsasV8DFAWS&categoryId=AV_6mRsasV8DFAWS&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 100만 이하의 모든 소수
 */
public class SWEA3131 {
    public static final int SIZE = 1000000;
    public static void main(String args[]) throws Exception {
        int count = 0;
        boolean[] prime = new boolean[SIZE + 1];
        StringBuilder sb = new StringBuilder();

        prime[0] = prime[1] = true;

        for (int i = 2; i * i <= SIZE; i++) {
            if (prime[i] == false) {
                for (int j = i * i; j <= SIZE; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 2; i <= SIZE; i++) {
            if (prime[i] == false) {
                sb.append(i + " ");
            }
        }
        sb.append("\n");
        System.out.println(sb);
    }
}
