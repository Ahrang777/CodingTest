package implementation.ch12;

import java.util.*;

/*
n=5

build_frame
[[1,0,0,1],[1,1,1,1],[2,1,0,1],[2,2,1,1],[5,0,0,1],[5,1,0,1],[4,2,1,1],[3,2,1,1]]

결과
[100]
[111]
[210]
[221]
[321]
[421]
[500]
[510]
 */

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 * 
 * 기둥과 보 설치
 */
public class Ex6 {

    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int a;  //기둥: 0 / 보: 1

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public int getA(){
            return a;
        }

        public Node(int x, int y, int a){
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public int compareTo(Node other){
            if (this.x != other.x) {
                return Integer.compare(this.x, other.x);
            } else if (this.y != other.y) {
                return Integer.compare(this.y, other.y);
            }
            return Integer.compare(this.a, other.a);

            /*if(this.x == o.getX() && this.y == o.getY()){
                return Integer.compare(this.a, o.getA());
            }
            if(this.x == o.getX()){
                return Integer.compare(this.y, o.getY());
            }
            return Integer.compare(this.x, o.getX());*/
        }
    }

    public static boolean check(List<Node> answer){

        for(int i = 0; i < answer.size(); i++){

            int x = answer.get(i).getX();
            int y = answer.get(i).getY();
            int a = answer.get(i).getA();

            //기둥
            if (a == 0) {

                boolean flag = false;
                //바닥위
                if(y == 0) flag = true;
                //다른 기둥 위, 보 한쪽 끝 위
                for (int j = 0; j < answer.size(); j++) {
                    //다른 기둥 위
                    if (x == answer.get(j).getX() && y - 1 == answer.get(j).getY() && 0 == answer.get(j).getA()) {
                        flag = true;
                    }

                    //보 왼쪽 위
                    if (x == answer.get(j).getX() && y == answer.get(j).getY() && 1 == answer.get(j).getA()) {
                        flag = true;
                    }

                    //보 오른쪽 위
                    if (x - 1 == answer.get(j).getX() && y == answer.get(j).getY() && 1 == answer.get(j).getA()) {
                        flag = true;
                    }
                }

                if(!flag) return false;
            }
            //보
            else if (a == 1) {

                boolean flag = false;
                boolean left = false;
                boolean right = false;

                for (int j = 0; j < answer.size(); j++) {
                    //보의 왼쪽에 기둥
                    if (x == answer.get(j).getX() && y - 1 == answer.get(j).getY() && 0 == answer.get(j).getA()) {
                        flag = true;
                    }
                    //보의 오른쪽에 기둥
                    if (x + 1 == answer.get(j).getX() && y - 1 == answer.get(j).getY() && 0 == answer.get(j).getA()) {
                        flag = true;
                    }

                    //왼쪽 보
                    if (x - 1 == answer.get(j).getX() && y == answer.get(j).getY() && 1 == answer.get(j).getA()) {
                        left = true;
                    }
                    //오른쪽 보
                    if (x + 1 == answer.get(j).getX() && y == answer.get(j).getY() && 1 == answer.get(j).getA()) {
                        right = true;
                    }
                }

                if (left && right) {
                    flag = true;
                }

                if(!flag) return false;
            }
        }

        return true;
    }

    public static int[][] solution(int n, int[][] build_frame) {

        ArrayList<Node> answer = new ArrayList<>();

        for(int i = 0; i < build_frame.length; i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int op = build_frame[i][3];

            //삭제
            if(op == 0){

                int index = 0;
                for(int j = 0; j < answer.size(); j++){
                    if(x == answer.get(j).getX() && y == answer.get(j).getY() && a == answer.get(j).getA())
                        index = j;
                }

                //삭제 대상
                Node target = answer.get(index);
                //일단 삭제
                answer.remove(index);

                //통과 못함면 원복
                if (!check(answer)) {
                    answer.add(target);
                }

            }
            //설치
            if(op == 1){
                //일단 추가
                Node newNode = new Node(x, y, a);
                answer.add(newNode);

                //통과 못하면 원복
                if (!check(answer)) {
                    answer.remove(answer.size() - 1);
                }
            }
        }

        Collections.sort(answer);

        int[][] result = new int[answer.size()][3];
        for (int i = 0; i < answer.size(); i++) {
            result[i][0] = answer.get(i).getX();
            result[i][1] = answer.get(i).getY();
            result[i][2] = answer.get(i).getA();
        }

        return result;
    }

    public static void main(String[] args) {

        /*
        [[1,0,0,1],[1,1,1,1],[2,1,0,1],[2,2,1,1],[5,0,0,1],[5,1,0,1],[4,2,1,1],[3,2,1,1]]
         */

        int[][] build_frame = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}
        };
        int n = 5;

        int[][] solution = solution(n, build_frame);

        for (int i = 0; i < solution.length; i++) {
            System.out.print("[");
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println("]");
        }
    }
}
