package study.programmers.challenges.hash;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42579
 *
 * 베스트앨범
 */
public class Programmers42579 {
    /*
    static class Genre implements Comparable<Genre> {
        String genre;
        int total;

        public Genre(String genre, int total) {
            this.genre = genre;
            this.total = total;
        }

        @Override
        public int compareTo(Genre o) {
            return Integer.compare(o.total, this.total);
        }
    }
    static class Music implements Comparable<Music> {
        int play, index;

        public Music(int play, int index) {
            this.play = play;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (this.play != o.play) {
                return Integer.compare(o.play, this.play);
            }
            return Integer.compare(this.index, o.index);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, List<Music>> genreMusic = new HashMap<>();
        Map<String, Integer> genrePlays = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            genreMusic.put(genres[i], genreMusic.getOrDefault(genres[i], new ArrayList<>()));
            List<Music> musicList = genreMusic.get(genres[i]);
            musicList.add(new Music(plays[i], i));
            genrePlays.put(genres[i], genrePlays.getOrDefault(genres[i], 0) + plays[i]);
        }

        PriorityQueue<Genre> pq = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : genrePlays.entrySet()) {
            pq.offer(new Genre(entry.getKey(), entry.getValue()));
        }

        while (!pq.isEmpty()) {
            Genre now = pq.poll();
            List<Music> musicList = genreMusic.get(now.genre);
            Collections.sort(musicList);

            result.add(musicList.get(0).index);
            if (musicList.size() > 1) {
                result.add(musicList.get(1).index);
            }
        }

        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
     */


    /*
    static class Music implements Comparable<Music> {
        int play, index;

        public Music(int play, int index) {
            this.play = play;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (this.play != o.play) {
                return Integer.compare(o.play, this.play);
            }
            return Integer.compare(this.index, o.index);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genresOrder = new ArrayList<>();
        while (!map.isEmpty()) {
            int max = -1;
            String genre = "";

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (max < entry.getValue()) {
                    max = entry.getValue();
                    genre = entry.getKey();
                }
            }

            genresOrder.add(genre);
            map.remove(genre);
        }

        List<Integer> result = new ArrayList<>();
        for (String genre : genresOrder) {
            List<Music> tmp = new ArrayList<>();

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(genre)) {
                    tmp.add(new Music(plays[i], i));
                }
            }

            Collections.sort(tmp);

            result.add(tmp.get(0).index);

            if (tmp.size() > 1) {
                result.add(tmp.get(1).index);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
     */

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
