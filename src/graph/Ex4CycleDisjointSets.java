package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
서로소 집합을 활용한 사이클 판별

입력
3 3
1 2
1 3
2 3

출력
사이클이 발생했습니다.
 */
public class Ex4CycleDisjointSets {

    public static int v, e;
    public static int[] parent = new int[100001];

    public static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        }
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

        v = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        boolean cycle = false;

        for (int i = 0; i < e; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if (findRoot(a) == findRoot(b)) {
                cycle = true;
                break;
            } else {
                union(a, b);
            }
        }

        if (cycle) {
            System.out.println("사이클이 발생했습니다.");
        }
        else {
            System.out.println("사이클이 발생하지 않았습니다.");
        }
    }
}
