package interviewQuestions;

/* **Hard**   1335. Minimum Difficulty of a Job Schedule
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish 
 * all the jobs j where 0 <= j < i).

   You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of 
   each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

   Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

   Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

   Example 1:
	Input: jobDifficulty = [6,5,4,3,2,1], d = 2
	Output: 7
	Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
				 Second day you can finish the last job, total difficulty = 1.
				 The difficulty of the schedule = 6 + 1 = 7
				 
   Example 2:
	Input: jobDifficulty = [9,9,9], d = 4
	Output: -1
	Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given 
	jobs.
	
  Example 3:
	Input: jobDifficulty = [1,1,1], d = 3
	Output: 3
	Explanation: The schedule is one job per day. total difficulty will be 3.
	
  Example 4:
	Input: jobDifficulty = [7,1,7,1,7,1], d = 3
	Output: 15
	
  Example 5:
	Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
	Output: 843
	
	Constraints:
		1 <= jobDifficulty.length <= 300
		0 <= jobDifficulty[i] <= 1000
		1 <= d <= 10
 */
public class MinDiffcultyofJobSchedule {
	
	private int minDifficulty(int[] jobDifficulty, int d, int offset, int[][] dp, int[] rightMaxArr) {
		if(offset>jobDifficulty.length-1) {
			return Integer.MAX_VALUE;
		}
		System.out.println("(d,offset):"+d+","+offset);
		if(dp[d][offset]!=0) {
			return dp[d][offset];
		}
		if(d == 0) {
			dp[d][offset] = rightMaxArr[offset];
			return rightMaxArr[offset];
		}
		int currMax = Integer.MIN_VALUE;
		int currDiff = Integer.MAX_VALUE;
		for(int i=offset;i<jobDifficulty.length;i++) {
			currMax = Math.max(currMax, jobDifficulty[i]);
			int jobDiff = minDifficulty(jobDifficulty, d-1, i+1, dp, rightMaxArr);
			if(jobDiff!=Integer.MAX_VALUE) {
				currDiff = Math.min(currDiff, currMax+jobDiff);
			}
		}
		dp[d][offset] = currDiff;
		return dp[d][offset];
	}
	
	private int[] rightMax(int[] jobDifficulty) {
		int len = jobDifficulty.length;
		int[] rightMaxArr = new int[len];
		rightMaxArr[len-1] = jobDifficulty[len-1];
		for(int i=len-2;i>=0;i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i+1], jobDifficulty[i]);
		}
		return rightMaxArr;
	}
	
	/*
	 * Time Complexity : O(nnd)
	 * Space Complexity: O(nd)
	 */
	public int minDifficulty(int[] jobDifficulty, int d) {
		if(jobDifficulty.length<d) {
			return -1;
		}
		int[][] dp = new int[d][jobDifficulty.length];
		int[] rightMaxArr = rightMax(jobDifficulty);
		int minDiff =  minDifficulty(jobDifficulty, d-1, 0, dp, rightMaxArr);
		return minDiff;
	}
	
	public int minDifficultyBottomUp(int[] jobDifficulty, int d) {
		if(jobDifficulty.length<d) {
			return -1;
		}
		int len = jobDifficulty.length;
		int[][] dp = new int[d][len];
		dp[0][0] = jobDifficulty[0];
		for(int i=1;i<len;i++) {
			dp[0][i] = Math.max(jobDifficulty[i], dp[0][i-1]);
		}
		for(int dd=1;dd<d;dd++) {
			for(int n=dd;n<len;n++) {
				dp[dd][n] =Integer.MAX_VALUE;
				int currVal = jobDifficulty[n];
				for(int ways=n;ways>=dd;ways--) {
					currVal = Math.max(currVal, jobDifficulty[ways]);
					dp[dd][n] = Math.min(dp[dd][n], currVal + dp[dd-1][ways-1]);
				}
			}
		}
		return dp[d-1][len-1];
	}

	public static void main(String[] args) {
		MinDiffcultyofJobSchedule minDiffJobSchedule = new MinDiffcultyofJobSchedule();
		int[] job1 = {6,5,4,3,2,1};
		int d1=2;
		System.out.println("Min Difficulty of job1 is "+minDiffJobSchedule.minDifficultyBottomUp(job1, d1));
		int[] job2 = {9,9,9};
		int d2=4;
		System.out.println("Min Difficulty of job2 is "+minDiffJobSchedule.minDifficultyBottomUp(job2, d2));
		int[] job3 = {1,1,1};
		int d3=3;
		System.out.println("Min Difficulty of job3 is "+minDiffJobSchedule.minDifficultyBottomUp(job3, d3));
		int[] job4 = {7,1,7,1,7,1};
		int d4=3;
		System.out.println("Min Difficulty of job4 is "+minDiffJobSchedule.minDifficultyBottomUp(job4, d4));
		int[] job5 = {11,111,22,222,33,333,44,444};
		int d5=6;
		System.out.println("Min Difficulty of job5 is "+minDiffJobSchedule.minDifficultyBottomUp(job5, d5));
	}

}
