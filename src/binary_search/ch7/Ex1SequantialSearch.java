package binary_search.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * 5 Dongbin
 *
 * Hanul Jonggu Dongbin Taeil Sangwook
 *
 * 출력
 * 3
 */
public class Ex1SequantialSearch {

    /*public static int sequantialSearch(String target, String[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(target)){
                return i + 1;
            }
        }
        return -1;
    }*/

    public static int sequantialSearch(int n, String target, String[] arr) {
        // 각 원소를 하나씩 확인하며
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
            // 현재의 원소가 찾고자 하는 원소와 동일한 경우
            if (arr[i].equals(target)) {
                return i + 1; // 현재의 위치 반환 (인덱스는 0부터 시작하므로 1 더하기)
            }
        }
        return -1; // 원소를 찾지 못한 경우 -1 반환
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        System.out.println("생성할 원소 개수를 입력한 다음 한 칸 띄고 찾을 문자열을 입력하세요.");

        int n = Integer.parseInt(stk.nextToken());
        String target = stk.nextToken();

        System.out.println("앞서 적은 원소 개수만큼 문자열을 입력하세요. 구분은 띄어쓰기 한 칸으로 합니다.");
        String[] arr = bf.readLine().split(" ");

//        System.out.println(sequantialSearch(target, arr));
        System.out.println(sequantialSearch(n, target, arr));
    }
}
