package interviewQuestions;

/*   **Medium**   256. Paint House
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. 
 * The cost of painting each house with a certain color is different. 
 * You have to paint all the houses such that no two adjacent houses have the same color.

   The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

   For example, costs[0][0] is the cost of painting house 0 with the color red; 
   costs[1][2] is the cost of painting house 1 with color green, and so on...
   Return the minimum cost to paint all houses.

	Example 1:
		Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
		Output: 10
		Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
					 Minimum cost: 2 + 5 + 3 = 10.
					 
	Example 2:
		Input: costs = [[7,6,2]]
		Output: 2

	Constraints:
		costs.length == n
		costs[i].length == 3
		1 <= n <= 100
		1 <= costs[i][j] <= 20
 */
public class PaintHouse {
	
	private int getMinCost(int[][] costs, int currRow, int previousCol, int rowSize, int colSize) {
		if(currRow>=rowSize) {
			return 0;
		}
		int currPaintCost = costs[currRow][previousCol];
		int minCost = Integer.MAX_VALUE;
		int nextRowColIndex1 = (previousCol+1)%3;
		int nextRowColIndex2 = (previousCol+2)%3;
		int minCostCurrRow = Math.min(getMinCost(costs, currRow+1, nextRowColIndex1, rowSize, colSize),
				getMinCost(costs, currRow+1, nextRowColIndex2, rowSize, colSize));
		return minCostCurrRow+currPaintCost;
	}
	
	private int getMinCost(int[][] costs, int currRow, int previousCol, int rowSize, int colSize, int[][] dp) {
		if(currRow>=rowSize) {
			return 0;
		}
		if(dp[currRow][previousCol]!=0) {
			return dp[currRow][previousCol];
		}
		int currPaintCost = costs[currRow][previousCol];
		int minCost = Integer.MAX_VALUE;
		int nextRowColIndex1 = (previousCol+1)%3;
		int nextRowColIndex2 = (previousCol+2)%3;
		int minCostCurrRow = Math.min(getMinCost(costs, currRow+1, nextRowColIndex1, rowSize, colSize, dp),
				getMinCost(costs, currRow+1, nextRowColIndex2, rowSize, colSize, dp));
		dp[currRow][previousCol] = minCostCurrRow+currPaintCost;
		return minCostCurrRow+currPaintCost;
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(n*n)
	 */
	public int minCostBottom2Top(int[][] costs) {
		int rowSize = costs.length;
		int colSize = 3; // red,green,blue
		int minCost = Integer.MAX_VALUE;
		int[][] dp = new int[rowSize][3];
		minCost = Math.min(minCost, getMinCost(costs, 0, 0, rowSize, colSize, dp));
		minCost = Math.min(minCost, getMinCost(costs, 0, 1, rowSize, colSize, dp));
		minCost = Math.min(minCost, getMinCost(costs, 0, 2, rowSize, colSize, dp));
		return minCost;
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int minCost(int[][] costs) {
		int rowSize = costs.length;
		int redPaintCost = costs[0][0];
		int greenPaintCost = costs[0][1];
		int bluePainCost = costs[0][2];
		for(int i=1;i<rowSize;i++) {
			int currRedPaintCost = costs[i][0] + Math.min(greenPaintCost, bluePainCost);
			int currGreenPaintCost = costs[i][1] + Math.min(redPaintCost, bluePainCost);
			int currBluePainCost = costs[i][2] + Math.min(redPaintCost, greenPaintCost);
			redPaintCost = currRedPaintCost;
			greenPaintCost = currGreenPaintCost;
			bluePainCost = currBluePainCost;
		}
		return Math.min(redPaintCost, Math.min(greenPaintCost, bluePainCost));
	}
	

	public static void main(String[] args) {
		PaintHouse paintHouse = new PaintHouse();
		int[][] costs1 = {{17,2,17},
						  {16,16,5},
						  {14,3,19}};
		System.out.println("Min Cost of cost1 : "+paintHouse.minCost(costs1));
		int[][] costs2 = {{7,6,2}};
		System.out.println("Min Cost of cost2 : "+paintHouse.minCost(costs2));
		int[][] costs3 = {{17, 2, 17}, 
						  { 8, 4, 10}, 
						  { 6, 3, 19}, 
						  { 4, 8, 12}};
		System.out.println("Min Cost of costs3 : "+paintHouse.minCost(costs3));
	}

}
