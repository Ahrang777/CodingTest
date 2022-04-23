package dfs_bfs.ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
입력
5 6
101010
111111
000001
111111
111111

출력
10
 */
public class Ex11 {

    static class Node{
        private int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    public static int n, m;
    public static int[][] graph = new int[201][201];

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int bfs(int x, int y){

        Queue<Node> q = new LinkedList();
        q.offer(new Node(x, y));

        while(!q.isEmpty()){
            Node node = q.poll();
            x = node.getX();
            y = node.getY();

            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx <= -1 || nx >= n || ny <= -1 || ny >= m)  continue;

                if (graph[nx][ny] == 0) continue;

                if(graph[nx][ny] == 1){
                    graph[nx][ny] = graph[x][y] + 1;
                    q.offer(new Node(nx, ny));
                }
            }
        }

        return graph[n-1][m-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        //입력
        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }
}
