package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42579
 *
 * 베스트앨범
 */
public class Programmers42579 {

    static class Music implements Comparable<Music> {

        int play, index;

        public Music(int play, int index) {
            this.play = play;
            this.index = index;
        }

        @Override
        public int compareTo(Music other){
            // 재생수 >> 내림차순 sorting
            if (this.play != other.play) {
                return Integer.compare(other.play, this.play);
            }

            // 고유번호 >> 오름차순 sorting
            return Integer.compare(this.index, other.index);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < plays.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        ArrayList<String> genres_order = new ArrayList<>();
        while(!map.isEmpty()){
            int max = -1;
            String max_key = "";

            for (String key : map.keySet()){
                if(max < map.get(key)){
                    max = map.get(key);
                    max_key = key;
                }
            }

            genres_order.add(max_key);
            map.remove(max_key);
        }

        ArrayList<Music> arr = new ArrayList<>();
        for (String key : genres_order){

            // 현재 장르에서 음악 순서를 정하기위한 list
            ArrayList<Music> tmp = new ArrayList<>();

            for (int i = 0; i < genres.length; i++){
                if (key.equals(genres[i])){
                    tmp.add(new Music(plays[i], i));
                }
            }
            Collections.sort(tmp);

            // 재생수 제일 많은음악은 항상 포함된다.
            arr.add(tmp.get(0));
            
            // 해당 장르 음악이 1개뿐일 경우 고려
            if(tmp.size() > 1){
                arr.add(tmp.get(1));
            }

        }

        int[] answer = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++){
            answer[i] = arr.get(i).index;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] arr = solution(genres, plays);

        for (int n : arr) {
            System.out.print(n + " ");
        }
    }
}
