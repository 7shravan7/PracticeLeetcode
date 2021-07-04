package greedy;

/* **Medium**   45. Jump Game II
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

   Each element in the array represents your maximum jump length at that position.

   Your goal is to reach the last index in the minimum number of jumps.

   *** ---- You can assume that you can always reach the last index. -----***
   
   Example 1:
	Input: nums = [2,3,1,1,4]
	Output: 2
	Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, 
				 then 3 steps to the last index.
	
   Example 2:
	Input: nums = [2,3,0,1,4]
	Output: 2
 * 
 * Constraints:
	1 <= nums.length <= 1000
	0 <= nums[i] <= 105
 */
public class JumpGame2 {

	/*
	 * Time Complexity: O(n*n)
	 * Space Complexity: O(n)
	 */
	public int jumpDpBottomUp(int[] nums) {
		int length = nums.length;
		int[] dp = new int[length];
		dp[length-1] =0;
		for(int i=length-2;i>=0;i--) {
			dp[i] = Integer.MAX_VALUE;
			int maxStep = nums[i];
			for(int j=maxStep;j>0;j--) {
				if(i+j>=length) {
					dp[i] = 1;
					break;
				} else if(dp[i+j] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1+dp[i+j]);
				}
			}
		}
		return dp[0];
	}
	
	/*
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	public int jumpGreedy(int[] nums) {
		int farthest=0;
		int currEnd=0;
		int jumps=0;
		for(int i=0;i<nums.length-1;i++) { // not considering last index
			farthest = Math.max(farthest, i+nums[i]);
			if(i == currEnd) {
				jumps++;
				currEnd = farthest;
			}
		}
		return jumps;
		
	}

	public static void main(String[] args) {
		JumpGame2 jumpGame2 = new JumpGame2();
		int[] nums = {3,5,2,1,4,1,6,2};
		System.out.println("Min No of jumps dp BottomUp: "+jumpGame2.jumpDpBottomUp(nums));
		System.out.println("Min No of jumps Greedy: "+jumpGame2.jumpGreedy(nums));
	}

}
