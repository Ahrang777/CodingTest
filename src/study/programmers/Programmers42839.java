package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 *
 * 소수 찾기
 * 다시 풀기
 */
public class Programmers42839 {

    // 아래와 비슷하지만 처음부터 짝수는 걸러서 반복을 줄인다.
    /*public static int solution(String numbers) {
        HashSet<Integer> set = new HashSet<>();
        permutation("", numbers, set);
        int count = 0;
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int a = it.next();
            if(a==2) count++;
            if(a%2!=0 && isPrime(a)){
                count++;
            }
        }
        return count;
    }

    public static boolean isPrime(int n){
        if(n==0 || n==1) return false;
        for(int i=3; i<=(int)Math.sqrt(n); i+=2){
            if(n%i==0) return false;
        }
        return true;
    }*/

    public static int solution(String numbers) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();

        permutation("", numbers, set);

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int num = it.next();

            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    public static void permutation(String prefix, String str, Set<Integer> set) {
        int n = str.length();
        if (!prefix.equals("")) set.add(Integer.parseInt(prefix));
        for (int i = 0; i < n; i++) {
            // str에서 i 번째를 뽑았으니까 제거해서 방문처리한다고 생각
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), set);
        }
    }

    // 에라토스테네스의 체 이용
    public static boolean isPrime(int num) {

        if (num == 0 || num == 1) return false;

        int limit = (int) Math.sqrt(num);

        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /*static Set<Integer> set;
    static boolean[] visited;

    public static int solution(String numbers) {
        int answer = 0;
        int size = numbers.length();
        set = new HashSet<>();

        char[] arr = new char[size];
        for (int i = 0; i < size; i++){
            arr[i] = numbers.charAt(i);
        }

        for (int i = 1; i <= size; i++){
            visited = new boolean[size];
            permutation(arr, new char[i], 0, i);
        }

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int next = it.next();
            if (isPrime(next))
                answer++;
        }

        return answer;
    }

    static boolean isPrime(int n){
        if (n == 1 || n == 0)   return false;

        int limit = (int) Math.sqrt(n);

        for(int i = 2; i <= limit; i++){
            if (n % i == 0)
                return false;
        }

        return true;
    }

    static void permutation(char[] arr, char[] output, int depth, int cnt){
        if (depth == cnt) {

            // 처음 0이 아닌 거부터 시작
            String str = "";
            boolean flag = true;
            for (int i = 0; i < cnt; i++) {
                if (flag && output[i] == '0')   continue;

                flag = false;
                str += output[i];
            }

            if (!str.equals(""))    set.add(Integer.parseInt(str));

            return;
        }

        for (int i = 0; i < arr.length; i++){
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, depth + 1, cnt);
                visited[i] = false;
            }

        }
    }*/

    public static void main(String[] args) {
        String[] numbers = {"17", "011"};

        for (String s : numbers) {
            System.out.println(solution(s));
        }
    }
}
