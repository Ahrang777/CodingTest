package study.baekjoon;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/16235
 *
 * 나무 재테크
 * 삼성전자 공채채
 */
public class Baekjoon16235 {

    static class Tree implements Comparable<Tree> {
        int age, x, y;

        public Tree(int age, int x, int y) {
            this.age = age;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Tree other) {
            return Integer.compare(this.age, other.age);
        }
    }

    public static int n, m, k;

    // S2D2 가 지역마다 추가하는 양분
    public static int[][] a;

    // 양분
    public static int[][] map;

    // 살아있는 나무
    // 중간 삽입, 삭제가 많기에 LinkedList로 해결
    static Queue<Tree> trees = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        a = new int[n + 1][n + 1];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = 5;
                a[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());

            trees.add(new Tree(z, x, y));
        }

        solution();

        System.out.println(trees.size());
    }
    
    public static void solution() {
        // 정렬을 위해서 형변환
        Collections.sort((List<Tree>) trees);

        for (int i = 0; i < k; i++) {
            springToSummer();
            fail();
            winter();
        }
    }

    public static void springToSummer() {
        // 죽은 나무
        ArrayList<Tree> removed = new ArrayList<>();

        int size = trees.size();

        while (size-- > 0) {
            Tree t = trees.poll();
            int x = t.x;
            int y = t.y;
            int age = t.age;

            if (map[x][y] < age) {
                removed.add(new Tree(age / 2, x, y));
            } else {
                map[x][y] -= age;
                trees.add(new Tree(age + 1, x, y));
            }
        }

        // 여름
        for (Tree t : removed) {
            map[t.x][t.y] += t.age;
        }
    }

    public static void fail() {
        // 큐에 크기 순으로 넣기 위해서 
        // 자식 먼저 큐에 넣고 부모를 나중에 넣기위해 부모를 임시로 저장할 리스트
        ArrayList<Tree> parents = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
        
        int size = trees.size();

        while (size-- > 0) {
            Tree t = trees.poll();

            // 나이가 5의 배수인 경우 번식 
            if (t.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];

                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                        trees.add(new Tree(1, nx, ny));
                    }
                }
            }

            parents.add(t);
        }

        // 자식들 먼저 넣었고 부모 추가
        trees.addAll(parents);
        /*for (Tree t : parents) {
            trees.add(t);
        }*/
    }

    public static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] += a[i][j];
            }
        }
    }

    /*
    // 우선순위 큐를 이용한 방법
    public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		plus=new int[N][N];
		value=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				plus[i][j]=sc.nextInt();
			}
		}
		PriorityQueue<Tree> Trees=new PriorityQueue<Tree>((Tree a,Tree b)-> a.age-b.age);
		for(int i=0; i<M; i++) {
			Trees.add(new Tree(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt()));
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				value[i][j]=5;
			}
		}
		for(int k=0; k<K; k++) {
			Queue<Tree> alive=new LinkedList<Tree>();
			Queue<Tree> dead=new LinkedList<Tree>();
			while(!Trees.isEmpty()) {
				Tree t=Trees.poll();
				if(value[t.x][t.y]>=t.age) {
					value[t.x][t.y]-=t.age;
					alive.add(new Tree(t.x,t.y,t.age+1));
				}else {
					dead.add(t);
				}
			}
			while(!dead.isEmpty()) {
				Tree t=dead.poll();
				value[t.x][t.y]+=t.age/2;
			}
			while(!alive.isEmpty()) {
				Tree t=alive.poll();
				if(t.age%5==0) {
					for(int d=0; d<8; d++) {
						int nx=t.x+dx[d];
						int ny=t.y+dy[d];
						if(nx>=0 && ny>=0 && nx<N && ny<N) {
							Trees.add(new Tree(nx,ny,1));
						}
					}
				}
				Trees.add(t);
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					value[i][j]+=plus[i][j];
				}
			}
		}
		System.out.println(Trees.size());

	}
     */
    
    /*
    // 아래 방법은 시간초과 발생
    // 추가, 삭제가 많을때는 ArrayList 보다는 LinkedList

    // 나무 리스트
    public static ArrayList<Tree> trees = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        a = new int[n + 1][n + 1];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = 5;
                a[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());

            trees.add(new Tree(z, x, y));
        }

        solution();

        System.out.println(trees.size());
    }

    // k년 후 나무 개수
    public static void solution() {

        for (int i = 0; i < k; i++) {
            // 나이 순으로 정렬
            Collections.sort(trees);

            // 봄, 여름
            springToSummer();

            // 가을
            autumn();

            // 겨울
            winter();
        }
    }

    // trees 는 나이 순으로 정렬되었기에 순서대로 확인하면 된다.
    public static void springToSummer() {
        ArrayList<Tree> removed = new ArrayList<>();

        for (Tree t : trees) {
            int x = t.x;
            int y = t.y;
            int age = t.age;

            // 양분 부족한 경우
            // 제거 리스트에 추가
            if (map[x][y] < age) {
//                아래의 경우 ConcurrentModificationException 발생
//                바로바로 remove로 제거하는 경우 index가 바뀌는데 그 인덱스로 다음 내용을 찾고하기에 문제발생, 제거할것들 따로 저장하고 removeAll로 for문 끝나면 한번에 제거
//                trees.remove(t);
                removed.add(t);
                continue;
            }

            map[x][y] -= age;
            t.age += 1;
        }

        // 양분 반영
        for (Tree r : removed) {
            map[r.x][r.y] += (r.age / 2);
        }

        // 나무 제거
        trees.removeAll(removed);
    }

    // 나무 번식
    public static void autumn() {
        ArrayList<Tree> tmp = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};

        for (Tree t : trees) {
            int x = t.x;
            int y = t.y;
            int age = t.age;

            // 나무 나이가 5의 배수인 경우
            if (age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                        tmp.add(new Tree(1, nx, ny));
                    }
                }
            }
        }

        trees.addAll(tmp);
    }

    // S2D2가 양분 추가
    public static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] += a[i][j];
            }
        }
    }
    */
}
