package study.programmers.lv1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42889
 * 실패율
 */
public class Programmers42889 {
	static class Stage {
		int index;
		double failure;

		public Stage(int index, double failure) {
			this.index = index;
			this.failure = failure;
		}
	}

	public int[] solution(int N, int[] lastStages) {
		int nPlayers = lastStages.length;
		int[] nStagePlayers = new int[N + 2];
		for (int stage : lastStages) {
			nStagePlayers[stage]++;
		}

		int remainPlayers = nPlayers;
		List<Stage> stages = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			double failure = (double) nStagePlayers[i] / remainPlayers;
			remainPlayers -= nStagePlayers[i];
			stages.add(new Stage(i, failure));
		}

		Collections.sort(stages, Collections.reverseOrder());

		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = stages.get(i).index;
		}

		return answer;
	}

	// static class Stage implements Comparable<Stage> {
	// 	int index;
	// 	double fail;
	//
	// 	public Stage(int index, double fail) {
	// 		this.index = index;
	// 		this.fail = fail;
	// 	}
	//
	// 	@Override
	// 	public int compareTo(Stage o) {
	// 		if (this.fail != o.fail) {
	// 			return Double.compare(o.fail, this.fail);
	// 		}
	//
	// 		return Integer.compare(this.index, o.index);
	// 	}
	// }
	// public int[] solution(int N, int[] stages) {
	// 	int[] answer = new int[N];
	// 	List<Stage> list = new ArrayList<>();
	//
	// 	for (int i = 1; i <= N; i++) {
	// 		int cnt1 = 0;   // 도달O
	// 		int cnt2 = 0;   // 도달O, 클리어x
	// 		for (int s : stages) {
	// 			if (s >= i) {
	// 				cnt1++;
	// 				if (s == i) {
	// 					cnt2++;
	// 				}
	// 			}
	// 		}
	//
	// 		double fail = 0.0;
	// 		if (cnt1 != 0) {
	// 			fail = (double) cnt2 / cnt1;
	// 		}
	//
	// 		list.add(new Stage(i, fail));
	// 	}
	//
	// 	Collections.sort(list);
	// 	for (int i = 0; i < N; i++) {
	// 		answer[i] = list.get(i).index;
	// 	}
	// 	return answer;
	// }



	/*

    static class Stage implements Comparable<Stage>{
        private int index;
        private double fail;

        public Stage(int index, double fail){
            this.index = index;
            this.fail = fail;
        }

        public int getIndex(){
            return index;
        }

        public int compareTo(Stage other){
            if(this.fail != other.fail){
                return Double.compare(other.fail, this.fail);
            }
            return Integer.compare(this.index, other.index);
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        ArrayList<Stage> result = new ArrayList<>();


        //스테이지 번호 1 ~ N
        for (int i = 1; i <= N; i++) {
            int sum = 0;    //스테이지 도달한 사람
            int cnt = 0;    //스테이지 도달했으나 아직 클리어X


            for (int j = 0; j < stages.length; j++) {
                if (i <= stages[j]) {
                    sum++;
                    if (i == stages[j]) {
                        cnt++;
                    }
                }
            }

            double fail = 0;
            if (sum != 0) {
                fail = (double) cnt / sum;
            }

            result.add(new Stage(i, fail));

        }

        Collections.sort(result);

        for(int i=0;i<result.size();i++){
            answer[i] = result.get(i).getIndex();
        }

        return answer;
    }
}
	 */
}
