package dp.leetcode;

/* **Easy**  53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which 
 * has the largest sum and return its sum.
 * 
 * Example 1:
 * 		Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
		Output: 6
		Explanation: [4,-1,2,1] has the largest sum = 6.
	
   Example 2:
		Input: nums = [1]
		Output: 1
		
   Example 3:
   	    Input: nums = [5,4,-1,7,8]
		Output: 23
		
   Example 4:
   		Input: nums = [-5,-4,-1,-7,-8]
		Output: -1
 */
public class MaximumSumSubarray {
	
	/*
	 * Kadane's Algorithm
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	public int maxSubArray(int[] nums) {
		int maxSoFar = nums[0];
		int currSum = nums[0];
		for(int i=1;i<nums.length;i++){
			if(currSum+nums[i]<nums[i]){
				currSum = nums[i];
			} else {
				currSum += nums[i];
			}
			if(maxSoFar<currSum){
				maxSoFar = currSum;
			}
		}
		return maxSoFar;
	}
	/*
	 * DivideAndConquer approach
	 * Time Complexity: O(nlogn)
	 * Space Complexity: O(logn)
	 */
	public int maxSubArrayDivideAndConquer(int[] nums) {
		// Our helper function is designed to solve this problem for
		// any array - so just call it using the entire input!
		return findBestSubarray(0, nums.length - 1, nums);
	}

	private int findBestSubarray(int left, int right, int[] numsArray) {
		// Base case - empty array.
		if (left > right) {
			return Integer.MIN_VALUE;
		}

		int mid = Math.floorDiv(left + right, 2);
		int curr = 0;
		int bestLeftSum = 0;
		int bestRightSum = 0;

		// Iterate from the middle to the beginning.
		for (int i = mid - 1; i >= left; i--) {
			curr += numsArray[i];
			bestLeftSum = Math.max(bestLeftSum, curr);
		}

		// Reset curr and iterate from the middle to the end.
		curr = 0;
		for (int i = mid + 1; i <= right; i++) {
			curr += numsArray[i];
			bestRightSum = Math.max(bestRightSum, curr);
		}

		// The bestCombinedSum uses the middle element and the best
		// possible sum from each half.
		int bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;

		// Find the best subarray possible from both halves.
		int leftHalf = findBestSubarray(left, mid - 1, numsArray);
		int rightHalf = findBestSubarray(mid + 1, right, numsArray);

		// The largest of the 3 is the answer for any given input array.
		return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
	}


	public static void main(String[] args) {
		MaximumSumSubarray maxSubarray = new MaximumSumSubarray();
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println("MaxSubArraySum : "+maxSubarray.maxSubArray(nums));
		System.out.println("MaxSubArraySum : "+maxSubarray.maxSubArrayDivideAndConquer(nums));
	}

}
