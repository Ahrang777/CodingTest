package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
입력
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1

출력
 */
public class Ex7 {
    
    //0 ~ n 까지 번호, m번 연산
    public static int n, m;
    public static int[] parent = new int[100001];
    public static ArrayList<String> results = new ArrayList<>();

    public static int findRoot(int x) {
        if(x == parent[x])  return x;
        return parent[x] = findRoot(parent[x]);
    }

    public static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int op = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if (op == 0) {
                union(a, b);
            }
            else if (op == 1) {
                if(findRoot(a) == findRoot(b)) results.add("YES");
                else results.add("NO");
            }
        }

        for (String result : results) {
            System.out.println(result);
        }

        /*for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int op = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if (op == 0) {
                union(a, b);
            }
            else if (op == 1) {
                if (findRoot(a) == findRoot(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }*/
    }
}
