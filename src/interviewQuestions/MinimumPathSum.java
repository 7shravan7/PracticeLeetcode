package interviewQuestions;

/* **Medium**    64. Minimum Path Sum
 * 
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, 
 * which minimizes the sum of all numbers along its path.

	Note: You can only move either down or right at any point in time.

	Example 1:
		Input: grid = [[1,3,1],
					   [1,5,1],
					   [4,2,1]]
		Output: 7
		Explanation: Because the path 1 -> 3 -> 1 -> 1 -> 1 minimizes the sum.
		
	Example 2:
		Input: grid = [[1,2,3],
					   [4,5,6]]
		Output: 12
 
	Constraints:
		m == grid.length
		n == grid[i].length
		1 <= m, n <= 200
		0 <= grid[i][j] <= 100
 */
public class MinimumPathSum {

	public int minPathSum(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int[][] dp = new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(i==0 || j==0) {
					if(i==0 && j==0) {
						dp[i][j] = grid[i][j];
					} else {
						if(i==0) {
							dp[i][j] = dp[i][j-1] + grid[i][j];
						} else {
							dp[i][j] = dp[i-1][j] + grid[i][j];
						}
					}
				} else {
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
				}
			}
		}
		return dp[row-1][col-1];
	}

	public static void main(String[] args) {
		MinimumPathSum minPathSum = new MinimumPathSum();
		int[][] grid1 = {{1,3,1},
				   		 {1,5,1},
				   		 {4,2,1}};
		System.out.println("Minimum path sum of grid1 : "+minPathSum.minPathSum(grid1));
		int[][] grid2 = {{1,2,3},
		   		 		 {4,5,6}};
		System.out.println("Minimum path sum of grid2 : "+minPathSum.minPathSum(grid2));
	}

}
