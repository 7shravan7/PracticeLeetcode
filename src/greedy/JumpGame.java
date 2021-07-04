package greedy;

/* **Medium**    55. Jump Game
 * 
 * 	Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
	Each element in the array represents your maximum jump length at that position.
	Determine if you are able to reach the last index
	
	Example 1:
		Input: nums = [2,3,1,1,4]
		Output: true
		Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
		
	Example 2:
		Input: nums = [3,2,1,0,4]
		Output: false
		Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, 
		which makes it impossible to reach the last index.
		
	Constraints:
		1 <= nums.length <= 3 * 104
		0 <= nums[i] <= 105
 */
public class JumpGame {
	
	/*
	 * BottomUp Approach DP
	 * Time Complexity : O(n*n)
	 * Space Complexity : O(n)
	 */
	public boolean canJumpDpBottomTop(int[] nums) {
		int length = nums.length;
		if(length==0) {  // base case ...just to be sure of nums.length even though not a constraint
			return false;
		}
		boolean[] dp = new boolean[length];
		dp[length-1] = true;
		for(int i=length-2;i>=0;i--) {
			int maxCurrStep = nums[i];
			for(int j=maxCurrStep;j>0;j--) {
				if(i+j>=length-1 || dp[i+j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[0];
	}
	
	/*
	 * Greedy approach
	 * Time Complexity : O(n)
	 * Space Complexity : O(1)
	 */
	public boolean canJumpDpGreedy(int[] nums) {
		int length = nums.length;
		int endReachIndex = length-1;
		for(int i=length-2;i>=0;i--) {
			if(nums[i]+i>=endReachIndex) {
				endReachIndex = i;
			}
		}
		return endReachIndex==0;
	}

	public static void main(String[] args) {
		JumpGame jumpGame = new JumpGame();
		int[] nums = {2,3,1,1,4};
		System.out.println("Can jump DP(BottomTop) : "+jumpGame.canJumpDpBottomTop(nums));
		System.out.println("Can jump Greedy : "+jumpGame.canJumpDpGreedy(nums));
	}

}
