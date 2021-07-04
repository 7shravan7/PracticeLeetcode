package interviewQuestions;

/* **Easy**  53. Maximum Subarray
 * 
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest 
 * sum and return its sum.

	Example 1:
		Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
		Output: 6
		Explanation: [4,-1,2,1] has the largest sum = 6.
		
	Example 2:
		Input: nums = [1]
		Output: 1
		
	Example 3:
		Input: nums = [5,4,-1,7,8]
		Output: 23
 
	Constraints:
		1 <= nums.length <= 3 * 104
		-105 <= nums[i] <= 105
 
	Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer 
	approach,which is more subtle.
 */
public class MaximumSubArray {

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

	public static void main(String[] args) {
		MaximumSubArray maxSubArray = new MaximumSubArray();
		int[] nums1 = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println("Max subArray of nums1 : "+maxSubArray.maxSubArray(nums1));
		int[] nums2 = {1};
		System.out.println("Max subArray of nums2 : "+maxSubArray.maxSubArray(nums2));
		int[] nums3 = {5,4,-1,7,8};
		System.out.println("Max subArray of nums3 : "+maxSubArray.maxSubArray(nums3));
		
	}

}
