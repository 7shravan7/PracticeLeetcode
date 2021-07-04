package interviewQuestions;

import java.util.Arrays;

/* **Medium**   198. House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and
 *  it will automatically contact the police if two adjacent houses were broken into on the same night.

   Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can 
   rob tonight without alerting the police.

	Example 1:
		Input: nums = [1,2,3,1]
		Output: 4
		Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
					 Total amount you can rob = 1 + 3 = 4.
		
	Example 2:
		Input: nums = [2,7,9,3,1]
		Output: 12
		Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
					 Total amount you can rob = 2 + 9 + 1 = 12.
 
	Constraints:
		1 <= nums.length <= 100
		0 <= nums[i] <= 400
 */
public class HouseRobber {
	
	/*
	 * Time : 1ms (5.23% faster than rest)
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	private int robDpTop2Bottom(int[] nums, int index, int[] dp) {
		if(index>=nums.length) {
			return 0;
		}
		if(dp[index]!=-1) {
			return dp[index];
		}
		int currVal = nums[index];
		int currValIncluded = currVal + robDpTop2Bottom(nums, index+2, dp);
		int currValExcluded = robDpTop2Bottom(nums, index+1, dp);
		int maxAmount = Math.max(currValIncluded, currValExcluded);
		dp[index] = maxAmount;
		return maxAmount;
	}
	
	/*
	 * Time : 0ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	private int robDpBottom2Top(int[] nums, int index, int[] dp) {
		for(int i=nums.length-1;i>=0;i--) {
			dp[i] = Math.max(nums[i]+dp[i+2], dp[i+1]);
		}
		return dp[0];
	}
	
	/*
	 * Time : 0ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int robDpBottom2TopOptimized(int[] nums) {
		int len = nums.length;
		int robNextPlusOne = 0;
		int robNext = nums[len-1];
		for(int i=len-2;i>=0;i--) {
			int currMax = Math.max(robNext, nums[i]+robNextPlusOne);
			robNextPlusOne = robNext;
			robNext = currMax;
		}
		return robNext;
	}

	public int rob(int[] nums) {
		int[] dp = new int[nums.length+2]; // to prevent recalculation for subarray twice
		Arrays.fill(dp, -1);
		dp[dp.length-1] = 0;
		dp[dp.length-2] = 0;
		int maxAmount = robDpBottom2Top(nums, 0, dp);
		return maxAmount;
	}

	public static void main(String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		int[] nums1 = {1,2,3,1};
		System.out.println("Maximum amount for nums1 : "+houseRobber.robDpBottom2TopOptimized(nums1));
		int[] nums2 = {2,7,9,3,1,9};
		System.out.println("Maximum amount for nums2 : "+houseRobber.robDpBottom2TopOptimized(nums2));
	}

}
