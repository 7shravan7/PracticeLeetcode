package interviewQuestions;

/* **Medium**     1010. Pairs of Songs With Total Durations Divisible by 60
 * 
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
   Return the number of pairs of songs for which their total duration in seconds is divisible by 60. 
   Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

   Example 1:
		Input: time = [30,20,150,100,40]
		Output: 3
		Explanation: Three pairs have a total duration divisible by 60:
					 (time[0] = 30, time[2] = 150): total duration 180
					 (time[1] = 20, time[3] = 100): total duration 120
					 (time[1] = 20, time[4] = 40): total duration 60
					 
   Example 2:
		Input: time = [60,60,60]
		Output: 3
		Explanation: All three pairs have a total duration of 120, which is divisible by 60.

   Constraints:
		1 <= time.length <= 6 * 104
		1 <= time[i] <= 500
 */
public class PairOfSongsDivisibleby60 {

	public int numPairsDivisibleBy60(int[] time) {
		int[] remainders = new int[60];
		int numPairs = 0;
		for(int t: time) {
			int mod60 = t%60;
			if(mod60 == 0) {
				numPairs += remainders[0];
			} else {
				numPairs += remainders[60-mod60];
			}
			remainders[mod60]++;
		}
		return numPairs;
	}

	public static void main(String[] args) {
		PairOfSongsDivisibleby60 pairOfSongs = new PairOfSongsDivisibleby60();
		int[] time1 = {30,20,150,100,40};
		System.out.println("Number of pair of songs time1 divisible by 60 : "+pairOfSongs.numPairsDivisibleBy60(time1));
		int[] time2 = {60,60,60,120};
		System.out.println("Number of pair of songs time2 divisible by 60 : "+pairOfSongs.numPairsDivisibleBy60(time2));
	}

}
