package dp.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 */
public class MaxProfitJobSchedule {
	
	// with normal binary search Time complexity is O(n*logn) 
	// Time : 38ms
	private int getNextJobIndexBinarySearch(List<Job> jobsList, int currentEndTime, int start) {
		int end = jobsList.size()-1;
		int nextGreaterIndex = -1;
		while(start<=end) {
			int mid = start + (end-start)/2;
			if(jobsList.get(mid).startTime>=currentEndTime) {
				nextGreaterIndex = mid;
				end = mid-1;
			} else {
				start = mid+1;
			}
		}
		return nextGreaterIndex;
	}
	
	// with normal linear search Time complexity is O(n*n) 
	// Time : 27ms
	private int getNextJobIndex(List<Job> jobsList, int currentEndTime, int start) {
		for(int i=start;i<jobsList.size();i++) {
			if(jobsList.get(i).startTime>=currentEndTime) {
				return i;
			}
		}
		return -1;
	}
	
	private int getMaxProfit(List<Job> jobsList, int currentIndex,Map<Integer,Integer> dpMap) {
		if(currentIndex>jobsList.size()-1) {
			return 0;
		}
		if(dpMap.containsKey(currentIndex)) {
			return dpMap.get(currentIndex);
		}
		Job currJob = jobsList.get(currentIndex);
		int nextJobIndex = getNextJobIndexBinarySearch(jobsList, currJob.endTime, currentIndex+1);
		int includedCurrJob = currJob.profit + ((nextJobIndex==-1) ? 0 : getMaxProfit(jobsList, nextJobIndex, dpMap));
		int excludedCurrJob = getMaxProfit(jobsList, currentIndex+1, dpMap);
		int maxProfit = Math.max(includedCurrJob, excludedCurrJob);
		dpMap.put(currentIndex, maxProfit);
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
		List<Job> jobsList = new ArrayList<>();
		for(int i=0;i<startTime.length;i++) {
			jobsList.add(new Job(startTime[i], endTime[i], profit[i]));
		}
		jobsList.sort(Comparator.comparingInt(a->a.startTime));
		//jobsList.sort((a,b)->a.startTime-b.startTime);
		Map<Integer,Integer> dpMap = new HashMap<>();
		return getMaxProfit(jobsList, 0, dpMap);
	}

	public static void main(String[] args) {
		MaxProfitJobSchedule maxProfitJs = new MaxProfitJobSchedule();
		int[] startTime = {1,2,1,4,6};
		int[] endTime = {3,5,10,6,9};
		int[] profit = {20,20,200,70,60};
		System.out.println("Max profit is "+ maxProfitJs.jobScheduling(startTime, endTime, profit));
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
