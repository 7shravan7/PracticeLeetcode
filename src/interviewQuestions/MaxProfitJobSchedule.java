package interviewQuestions;

import java.util.Arrays;

/*  ** Hard ** 1235. Maximum Profit in Job Scheduling
 * 
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], 
 * obtaining a profit of profit[i].

  You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that 
  there are no two jobs in the subset with overlapping time range.

  If you choose a job that ends at time X you will be able to start another job that starts at time X.
  
  Example 1
  	Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
  	Output: 120
  	Explanation: The subset chosen is the first and fourth job. 
  	Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
  
  Example 2
  	Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
	Output: 150
	Explanation: The subset chosen is the first, fourth and fifth job. 
	Profit obtained 150 = 20 + 70 + 60.

  Example 3
    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
	Output: 6
	
  Constraints:
	1 <= startTime.length == endTime.length == profit.length <= 5 * 104
	1 <= startTime[i] < endTime[i] <= 109
	1 <= profit[i] <= 104
 */
public class MaxProfitJobSchedule {
	
	// with normal binary search Time complexity is O(n*logn) 
	// Time : 26ms
	private int getNextJobIndexBinarySearch(Job[] jobArr, int currentEndTime, int start) {
		int end = jobArr.length-1;
		int nextJobIndex = -1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(jobArr[mid].startTime>=currentEndTime) {
				nextJobIndex = mid;
				end = mid-1;
			} else {
				start = mid+1;
			}
		}
		return nextJobIndex;
	}
	
	// with normal linear search Time complexity is O(n*n) 
	// Time : 12ms
	private int getNextJobIndex(Job[] jobArr, int currentEndTime, int start) {
		for(int i=start;i<jobArr.length;i++) {
			if(jobArr[i].startTime>=currentEndTime) {
				return i;
			}
		}
		return -1;
	}
	
	private int getMaxProfit(Job[] jobArr, int currentIndex, int[] dp) {
		if(currentIndex>jobArr.length-1) {
			return 0;
		}
		if(dp[currentIndex]!=0) {
			return dp[currentIndex];
		}
		int maxProfit = 0;
		Job currJob = jobArr[currentIndex];
		int nextJobIndex = getNextJobIndexBinarySearch(jobArr, currJob.endTime, currentIndex+1);
		int profitCurrJobIncluded = currJob.profit + (nextJobIndex == -1 ? 0 : getMaxProfit(jobArr, nextJobIndex, dp));
		int profitCurrJobExcluded = getMaxProfit(jobArr, currentIndex+1, dp);
		maxProfit = Math.max(profitCurrJobIncluded, profitCurrJobExcluded);
		dp[currentIndex] = maxProfit;
		return maxProfit;
	}
	
	/*
	 * Logic:
	 * 1.Sort the job list by start time
	 * 2.(i) can include this job as part of max profit
	 *        add curr index and then find next job which has start time >= currJob.endTime ,
	 *        so that they can be scheduled.(we can use Binary search since it will be sorted)
	 *   (ii) can ignore this job 
	 */
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int startTimeLength = startTime.length;
		int[] dp = new int[startTimeLength];
		Job[] jobArr = new Job[startTimeLength];
		for(int i=0;i<startTimeLength;i++) {
			jobArr[i] = new Job(startTime[i], endTime[i], profit[i]);
		}
		Arrays.sort(jobArr, (a,b)->a.startTime-b.startTime);
		int maxProfit = getMaxProfit(jobArr, 0, dp);
		return maxProfit;
	}

	public static void main(String[] args) {
		MaxProfitJobSchedule maxProfitJs = new MaxProfitJobSchedule();
		int[] startTime1 = {1,2,1,4,6};
		int[] endTime1 = {3,5,10,6,9};
		int[] profit1 = {20,20,200,70,60};
		System.out.println("Max profit1 is "+ maxProfitJs.jobScheduling(startTime1, endTime1, profit1));
		int[] startTime2 = {1,2,3,4,6};
		int[] endTime2 = {3,5,10,6,9};
		int[] profit2 = {20,20,100,70,60};
		System.out.println("Max profit2 is "+ maxProfitJs.jobScheduling(startTime2, endTime2, profit2));
	}
	
	class Job {
		int startTime;
		int endTime;
		int profit;
		Job(int startTime, int endTime, int profit){
			this.startTime = startTime;
			this.endTime = endTime;
			this.profit = profit;
		}
	}

}
