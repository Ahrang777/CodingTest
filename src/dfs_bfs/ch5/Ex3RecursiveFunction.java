package dfs_bfs.ch5;

public class Ex3RecursiveFunction {

    public static void recursiveFunction(){
        System.out.println("재귀함수 호출");
        recursiveFunction();
    }

    public static void main(String[] args) {
        recursiveFunction();
    }
}
