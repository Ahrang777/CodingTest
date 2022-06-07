package binary_search.ch15;

import java.io.*;
import java.util.*;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60060
 *
 * 가사 검색
 */
public class Ex4 {

    /*public static Comparator<String> comLen = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {

            if (o1.length() != o2.length()) {
                return (o1.length() < o2.length() ? -1 : 1);
            } else {
                return o1.compareTo(o2);
            }

//            return (o1.length() < o2.length() ? -1 : (o1.length() > o2.length() ? 1 : 0));
        }
    };



    //target의 범위의 첫번째 위치
    public static int lowerBound(String[] words, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if(words[mid].length() >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    //target의 범위의 마지막 위치 + 1
    public static int upperBound(String[] words, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (words[mid].length() > target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(String[] words, String query) {
        
        int leftValue = query.length();
        int rightValue = query.length();

        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        int rightIndex = upperBound(words, rightValue, 0, words.length);
        int leftIndex = lowerBound(words, leftValue, 0, words.length);

        rightIndex = rightIndex - 1;
        //left ~ right 까지가 범위




        String sub = "";
        int index = 0;
        int count = 0;

        //?시작
        if (query.startsWith("?")) {
            index = query.lastIndexOf('?');
            sub = query.substring(index + 1);
//            startWith = true;

            for (int i = leftIndex; i <= rightIndex; i++) {
                if (words[i].startsWith(sub)) {
                    count += 1;
                }
            }
        }
        //?끝
        else {
            index = query.indexOf('?');
            sub = query.substring(0, index);

            for (int i = leftIndex; i <= rightIndex; i++) {
                if (words[i].endsWith(sub)) {
                    count += 1;
                }
            }
        }

        return count;


        *//*while (leftIndex <= rightIndex) {
            
        }*//*

//        return rightIndex - leftIndex;
    }

    *//*public static int count(String[] words, int start, int end, String target) {

        String sub = "";
        int index = 0;
        boolean startWith = false;

        //?시작
        if (target.startsWith("?")) {
            index = target.lastIndexOf('?');
            sub = target.substring(index + 1);
            startWith = true;
        }
        //?끝
        else {
            index = target.indexOf('?');
            sub = target.substring(0, index);
        }


        while (start <= end) {
            //글자 길이
            int mid = (start + end) / 2;

            if (mid == target.length()) {

            } else if (mid > target.length()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return 0;
    }*//*

    public static int[] solution(String[] words, String[] queries) {
//        int[] answer = {};

        *//*Arrays.sort(words, comLen);
        int len = words.length;

        int start = words[0].length();
        int end = words[len - 1].length();

        for (String query : queries) {


        }*//*

        int[] answer = new int[queries.length];
        int len = words.length;

        Arrays.sort(words, comLen); //길이로 정렬하고 길이 같으면 알파벳 순으로 정렬

        int start = words[0].length();
        int end = words[len - 1].length();

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            answer[i] = countByRange(words, query);

//            answer[i] = count(words, start, end, query);
        }

        return answer;
    }*/

    public static int lowerBound(ArrayList<String> arr, String target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            // arr[mid]가 target보다 사전순으로 같거나 뒤에 있다면
            if (arr.get(mid).compareTo(target) >= 0) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int upperBound(ArrayList<String> arr, String target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            // arr[mid]가 target보다 사전순으로 뒤에 있다면
            if (arr.get(mid).compareTo(target) > 0) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(ArrayList<String> arr, String leftValue, String rightValue) {
        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        int rightIndex = upperBound(arr, rightValue, 0, arr.size());
        int leftIndex = lowerBound(arr, leftValue, 0, arr.size());
        return rightIndex - leftIndex;
    }

    // 모든 단어들을 길이마다 나누어서 저장하기 위한 리스트
    public static ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
    // 모든 단어들을 길이마다 나누어서 뒤집어 저장하기 위한 리스트
    public static ArrayList<ArrayList<String>> reversedArr = new ArrayList<ArrayList<String>>();

    public static int[] solution(String[] words, String[] queries) {
        ArrayList<Integer> ans = new ArrayList<Integer>();

        // 단어의 길이는 10,000까지 가능
        for (int i = 0; i < 10001; i++) {
            arr.add(new ArrayList<String>());
            reversedArr.add(new ArrayList<String>());
        }

        // 모든 단어를 접미사 와일드카드 배열, 접두사 와일드카드 배열에 각각 삽입
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            arr.get(word.length()).add(word); // 단어를 삽입
            word = (new StringBuffer(word)).reverse().toString();
            reversedArr.get(word.length()).add(word); // 단어를 뒤집어서 삽입
        }

        // 이진 탐색을 수행하기 위해 각 단어 리스트 정렬 수행
        for (int i = 0; i < 10001; i++) {
            Collections.sort(arr.get(i));
            Collections.sort(reversedArr.get(i));
        }

        // 쿼리를 하나씩 확인하며 처리
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            int res = 0;
            if (q.charAt(0) != '?') { // 접미사에 와일드 카드가 붙은 경우
                res = countByRange(arr.get(q.length()), q.replaceAll("\\?", "a"), q.replaceAll("\\?", "z"));
            }
            else { // 접두사에 와일드 카드가 붙은 경우
                q = (new StringBuffer(q)).reverse().toString();
                res = countByRange(reversedArr.get(q.length()), q.replaceAll("\\?", "a"), q.replaceAll("\\?", "z"));
            }
            // 검색된 단어의 개수를 저장
            ans.add(res);
        }

        // 배열로 바꾸어 반환
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao", "fro"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        /*Arrays.sort(words, comLen);

        for (String word : words) {
            System.out.println(word);
        }*/

        int[] result = solution(words, queries);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
