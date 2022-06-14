package graph.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
6 4
1 4
2 3
2 4
5 6

출력
각 원소가 속한 집합: 1 1 1 1 5 5 
부모 테이블: 1 1 2 1 5 5 
 */
public class Ex1DisjointSets {

    public static int v, e;
    public static int[] parent = new int[100001];

    public static int findRoot(int x) {
        if(x==parent[x])    return x;
        return findRoot(parent[x]);

        /*
        //개선된 형태
        //부모 테이블에 루트노드를 저장해버림
        //연결형태는 모르지만 같은 집합인지 구분하기는 좋다.
        //위 코드의 경우는 부모테이블에 부모노드가 있어서 연결 상태를 알 수 있음, 반면 아래코드는 부모테이블에 루트노드가 있음
        if(parent[x] != x) parent[x] = findRoot(parent[x]);
        return parent[x];*/
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

        for (int i = 0; i < e; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            union(a, b);
        }

        System.out.print("각 원소가 속한 집합: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(findRoot(i) + " ");
        }
        System.out.println();

        // 부모 테이블 내용 출력하기
        System.out.print("부모 테이블: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }
}
