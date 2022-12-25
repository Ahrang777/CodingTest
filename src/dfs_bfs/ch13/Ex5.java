package dfs_bfs.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
2
5 6
0 0 1 0

출력
30
30
==========
입력
3
3 4 5
1 0 1 0

출력
35
17
==========
입력
6
1 2 3 4 5 6
2 1 1 1

출력
54
-24
 */

/**
 * https://www.acmicpc.net/problem/14888
 *
 * 연산자 끼워 넣기
 */
public class Ex5 {

    public static int N;
    public static int[] arr = new int[11];

    public static int min = (int) 1e9;
    public static int max = (int) -1e9;

    public static int add, sub, mul, div;

    public static void calculation(int index, int result) {
        if (index == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
        } else {
            if (add > 0) {
                add--;
                calculation(index + 1, result + arr[index]);
                add++;
            }
            if (sub > 0) {
                sub--;
                calculation(index + 1, result - arr[index]);
                sub++;
            }
            if (mul > 0) {
                mul--;
                calculation(index + 1, result * arr[index]);
                mul++;
            }
            if (div > 0) {
                div--;
                calculation(index + 1, result / arr[index]);
                div++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        N = Integer.parseInt(bf.readLine());

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        add = Integer.parseInt(stk.nextToken());
        sub = Integer.parseInt(stk.nextToken());
        mul = Integer.parseInt(stk.nextToken());
        div = Integer.parseInt(stk.nextToken());

        calculation(1, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }

    /*
    static int N;
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;
    static int[] numbers;
    static boolean[] visited;
    static List<Character> op = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cnt[0]; i++) {
            op.add('+');
        }
        for (int i = 0; i < cnt[1]; i++) {
            op.add('-');
        }
        for (int i = 0; i < cnt[2]; i++) {
            op.add('*');
        }
        for (int i = 0; i < cnt[3]; i++) {
            op.add('/');
        }

        char[] out = new char[N - 1];
        permutation(out, visited, 0);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    private static void permutation(char[] out, boolean[] visited, int depth) {
        if (depth == N - 1) {
            int index = 0;
            int result = numbers[index++];

            for (char ch : out) {
                if (ch == '+') {
                    result += numbers[index++];
                } else if (ch == '-') {
                    result -= numbers[index++];
                } else if (ch == '*') {
                    result *= numbers[index++];
                } else if (ch == '/') {
                    result /= numbers[index++];
                }
            }

            minValue = Math.min(minValue, result);
            maxValue = Math.max(maxValue, result);

            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = op.get(i);
                permutation(out, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
     */
}
