package dp.leetcode;

import java.util.HashMap;
import java.util.Map;

/* ** Hard **
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, 
		you have to finish all the jobs j where 0 <= j < i).

  You have to finish at least one task every day. The difficulty of a job schedule is the sum of
		difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a 
		job done in that day.

  Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

  Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
  
  Example 1
  	Input: jobDifficulty = [6,5,4,3,2,1], d = 2
	Output: 7
	Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
				 Second day you can finish the last job, total difficulty = 1.
				 The difficulty of the schedule = 6 + 1 = 7
  Example 2
    Input: jobDifficulty = [9,9,9], d = 4
	Output: -1
	Explanation: If you finish a job per day you will still have a free day. 
				 you cannot find a schedule for the given jobs.
				 
   Top down approach
   ------------------
   Time:O(n^2 * d) nd(# of subproblems) and n (for each subproblem) 
   Space:O(nd)
 
  */
		
public class MinDifficultyJobSchedule {
	
	
	private int getMax(int[] arr, int startIndex, int endIndex) {
		int max = Integer.MIN_VALUE;
		for(int i=startIndex;i<endIndex;i++) {
			if(max<arr[i]) {
				max= arr[i];
			}
		}
		return max;
	}
	// time limit exceeded
	private int minDifficulty(int[] jobDifficulty, int d, int offset) {
		if(d==1) {
			return getMax(jobDifficulty, offset, jobDifficulty.length);
		}
		int minDiff = Integer.MAX_VALUE;
		for(int i=offset;i<=jobDifficulty.length-d;i++) {
			int splitOffsetMax = getMax(jobDifficulty, offset, i+1);
			int offsetMinDifficulty = minDifficulty(jobDifficulty, d-1, i+1);
			minDiff = Math.min(minDiff, splitOffsetMax+offsetMinDifficulty);
		}
		return minDiff;
	}

	public int minDifficulty(int[] jobDifficulty, int d) {
		int jobLength = jobDifficulty.length;
		if(jobLength<d) {
			return -1;
		}
		return minDifficulty(jobDifficulty, d, 0);
	}
	
	// -------------------------------------------------------------
	// 322ms &39mb   with getMax for each left
	// 175ms &39.6mb with Math.max(currMax, jobDifficulty[i])
	private int minDifficultyWithMemoization(int[] jobDifficulty, int d, int offset, Map<String,Integer> dpMap) {
		if(d==1) {
			return getMax(jobDifficulty, offset, jobDifficulty.length);
		}
		String dpMapKey = offset+","+d;
		if(dpMap.containsKey(dpMapKey)) {
			return dpMap.get(dpMapKey);
		}
		int currMax= Integer.MIN_VALUE;
		int minDiff = Integer.MAX_VALUE;
		for(int i=offset;i<=(jobDifficulty.length-d);i++) {
			currMax = Math.max(currMax, jobDifficulty[i]);
			int offsetMinDifficulty = minDifficultyWithMemoization(jobDifficulty, d-1, i+1, dpMap);
			minDiff = Math.min(minDiff, currMax+offsetMinDifficulty);
		}
		dpMap.put(dpMapKey, minDiff);
		return minDiff;
	}
	
	public int minDifficultyWithMemoization(int[] jobDifficulty, int d) {
		int jobLength = jobDifficulty.length;
		if(jobLength<d) {
			return -1;
		}
		Map<String,Integer> dpMap = new HashMap<>();
		int min = minDifficultyWithMemoization(jobDifficulty, d, 0, dpMap);
		return min;
	}
// ----------------------------------------------------------------------------------------------
	// 145ms & 39.8mb with right max calcualted previously
	private int minDifficultyWithMemoization(int[] jobDifficulty, int d, int offset, Map<String,Integer> dpMap,
			int[] rightMaxArr) {
		if(d==1) {
			return rightMaxArr[offset];
		}
		String dpMapKey = offset+","+d;
		if(dpMap.containsKey(dpMapKey)) {
			return dpMap.get(dpMapKey);
		}
		int currMax= Integer.MIN_VALUE;
		int minDiff = Integer.MAX_VALUE;
		for(int i=offset;i<=(jobDifficulty.length-d);i++) {
			currMax = Math.max(currMax, jobDifficulty[i]);
			int offsetMinDifficulty = minDifficultyWithMemoization(jobDifficulty, d-1, i+1, dpMap,rightMaxArr);
			minDiff = Math.min(minDiff, currMax+offsetMinDifficulty);
		}
		dpMap.put(dpMapKey, minDiff);
		return minDiff;
	}
	
	/*
	 * to avoid repition of calculating max
	 */
	private int[] getRightMax(int arr[]) {
		int len = arr.length;
		int[] rightMaxArr = new int[len];
		rightMaxArr[len-1] = arr[len-1];
		for(int i=len-2;i>=0;i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i+1], arr[i]);
		}
		return rightMaxArr;
	}
	
	public int minDifficultyWithMemoization1(int[] jobDifficulty, int d) {
		int jobLength = jobDifficulty.length;
		if(jobLength<d) {
			return -1;
		}
		Map<String,Integer> dpMap = new HashMap<>();
		int[] rightMaxArr = getRightMax(jobDifficulty);
		int min = minDifficultyWithMemoization(jobDifficulty, d, 0, dpMap, rightMaxArr);
		return min;
	}
	
	// ************************************************
	
	public int minDifficultyBottom2Top(int[] jobDifficulty, int D) {
        final int N = jobDifficulty.length;
        if(N < D) return -1;
        int[][] dp = new int[D][N];

        dp[0][0] = jobDifficulty[0];
        for(int j = 1; j < N; ++j){
            dp[0][j] = Math.max(jobDifficulty[j], dp[0][j - 1]);
        }

        for(int d = 1; d < D; ++d){
            for(int len = d; len < N; ++len){
                int localMax = jobDifficulty[len];
                dp[d][len] = Integer.MAX_VALUE;
                for(int schedule = len; schedule >= d; --schedule){
                    localMax = Math.max(localMax, jobDifficulty[schedule]);
                    dp[d][len] = Math.min(dp[d][len], dp[d - 1][schedule - 1] + localMax);
                }
            }
        }

        return dp[D - 1][N - 1];
    }

	public static void main(String[] args) {
		MinDifficultyJobSchedule minJobSchedule = new MinDifficultyJobSchedule();
//		int[] jobDifficulty = {380,302,102,681,863,676,243,671,651,612,162,561,394,856,601,30,6,257,921,405,716,
//				126,158,476,889,699,668,930,139,164,641,801,480,756,797,915,275,709,161,358,461,938,914,557,121,
//				964,315};
		int[] jobDifficultyLarge = {270,340,359,593,689,75,923,738,564,582,553,776,324,871,734,259,915,195,538,
				247,174,82,642,44,50,786,448,762,46,526,458,443,274,859,701,466,417,917,543,87,414,69,196,372,
				150,106,154,611,686,15,478,260,961,248,980,387,629,233,410,637,588,786,924,137,164,501,338,205,
				303,28,957,851,370,17,484,187,791,340,789,866,300,499,831,300,133,811,204,536,875,87,850,451,
				749,905,620,990,291,713,623,741,25,133,14,7,631,249,507,136,298,47,315,841,667,88,808,173,618,
				715,937,877,737,313,203,579,817,596,700,787,414,41,852,152,212,246,925,304,375,307,717,734,215,
				464,390,192,496,796,530,666,590,0,688,141,230,20,504,24,150,952,608,878,805,709,519,798,17,439,
				540,516,723,898,249,606,468,75,96,390,731,43,411,7,799,816,639,405,101,567,67,881,721,16,175,248
				,876,461,864,48,51,474,90,508,266,72,294,545,447,343,216,909,424,432,799,533,52,377,368,961,231,
				56,217,638,425,9,946,206,915,428,980,686};
		int dLarge=8;
		int[] jobDifficulty = {6,5,4,3,2,1};
		int d=2;
		int minDifficulty = minJobSchedule.minDifficultyWithMemoization1(jobDifficulty, d);
		System.out.println("Min difficulty is "+minDifficulty);
		int[] jobDifficulty1 = {11,111,22,222,33,333,44,444};
		int d1=6;
		System.out.println("Min difficulty is "+minJobSchedule.minDifficultyBottom2Top(jobDifficultyLarge, dLarge));
	}

}
