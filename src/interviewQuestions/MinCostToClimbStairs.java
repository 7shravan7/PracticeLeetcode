package interviewQuestions;

/* **Easy**    746. Min Cost Climbing Stairs
 * 
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. 
 * Once you pay the cost, you can either climb one or two steps.

   You can either start from the step with index 0, or the step with index 1.

   Return the minimum cost to reach the top of the floor.

	Example 1:
		Input: cost = [10,15,20]
		Output: 15
		Explanation: Cheapest is: start on cost[1], pay that cost, and go to the top.
		
	Example 2:
		Input: cost = [1,100,1,1,1,100,1,1,100,1]
		Output: 6
		Explanation: Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
 
	Constraints:
		2 <= cost.length <= 1000
		0 <= cost[i] <= 999
 */
public class MinCostToClimbStairs {
	
	/*
	 * Time : 1ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	public int minCostClimbingStairsBottomTop(int[] cost) {
        int[] dp =new int[cost.length];
        dp[cost.length-1] = cost[cost.length-1];
        dp[cost.length-2] = cost[cost.length-2];
        for(int i=cost.length-3;i>=0;i--){
        	dp[i] = Math.min(dp[i+1], dp[i+2]) + cost[i];
        }
        return Math.min(dp[0], dp[1]);
    }
	
	/* Time : 0ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int minCostClimbingStairs(int[] cost) {
        int oldPrevVal = cost[0];
        int prevVal = cost[1];
        for(int i=2;i<cost.length;i++){
        	int currVal = Math.min(prevVal, oldPrevVal) + cost[i];
        	oldPrevVal = prevVal;
        	prevVal = currVal;
        }
        return Math.min(oldPrevVal, prevVal);
    }

	public static void main(String[] args) {
		MinCostToClimbStairs climbStairs = new MinCostToClimbStairs();
		int[] cost1 = {10,15,20};
		System.out.println("Min Cost of cost1 : "+climbStairs.minCostClimbingStairs(cost1));
		int[] cost2 = {1,100,1,1,1,100,1,1,100,1};
		System.out.println("Min Cost of cost2 : "+climbStairs.minCostClimbingStairs(cost2));
		int[] cost3 = {1,1,0,0};
		System.out.println("Min Cost of cost3 : "+climbStairs.minCostClimbingStairs(cost3));
	}

}
