package interviewQuestions;

/* **Hard**   329. Longest Increasing Path in a Matrix
 * 
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.

   From each cell, you can either move in four directions: left, right, up, or down. 
   You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

	Example 1:
		Input: matrix = [[9,9,4],
						 [6,6,8],
						 [2,1,1]]
		Output: 4
		Explanation: The longest increasing path is [1, 2, 6, 9].
		
	Example 2:
		Input: matrix = [[3,4,5],
						 [3,2,6],
						 [2,2,1]]
		Output: 4
		Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
			
	Example 3:
		Input: matrix = [[1]]
		Output: 1

	Constraints:
		m == matrix.length
		n == matrix[i].length
		1 <= m, n <= 200
		0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathInMatrix {

	public int longestIncreasingPath(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] dp = new int[row][col];
		int longestPath = 1;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				dp[i][j] = dfs(matrix, dp, i, j, row, col);
				longestPath = Math.max(longestPath, dp[i][j]);
			}
		}
		return longestPath;
	}

	private int dfs(int[][] matrix, int[][] dp, int i, int j, int row, int col) {
		if(dp[i][j]!=0) {
			return dp[i][j];
		}
		int maxPath = 0;
		int currVal = matrix[i][j];
		if(isWithinBoundary(i-1, j, row, col) && matrix[i-1][j]>currVal) {   // top
			maxPath = Math.max(maxPath, dfs(matrix, dp, i-1, j, row, col));
		}
		if(isWithinBoundary(i, j-1, row, col) && matrix[i][j-1]>currVal) {   // left
			maxPath = Math.max(maxPath, dfs(matrix, dp, i, j-1, row, col));
		}
		if(isWithinBoundary(i+1, j, row, col) && matrix[i+1][j]>currVal) {   // bottom
			maxPath = Math.max(maxPath, dfs(matrix, dp, i+1, j, row, col));
		}
		if(isWithinBoundary(i, j+1, row, col) && matrix[i][j+1]>currVal) {   // right 
			maxPath = Math.max(maxPath, dfs(matrix, dp, i, j+1, row, col));
		}
		dp[i][j] = 1 + maxPath;
		return dp[i][j];
	}
	
	private boolean isWithinBoundary(int i, int j, int row, int col) {
		return !(i<0 || i>=row || j<0 || j>=col);
	}

	public static void main(String[] args) {
		LongestIncreasingPathInMatrix lipMatrix = new LongestIncreasingPathInMatrix();
		int[][] matrix1 = {{9,9,4},
				 		   {6,6,8},
				 		   {2,1,1}};
		System.out.println("longestIncreasingPath of matrix1 : "+lipMatrix.longestIncreasingPath(matrix1));
		int[][] matrix2 = {{3,4,5},
		 		   		   {3,2,6},
		 		   		   {2,2,1}};
		System.out.println("longestIncreasingPath of matrix2 : "+lipMatrix.longestIncreasingPath(matrix2));
		int[][] matrix3 = {{1}};
		System.out.println("longestIncreasingPath of matrix3 : "+lipMatrix.longestIncreasingPath(matrix3));
		int[][] matrix4 = {{9,9,10},
		   		   		   {6,6,11},
		   		   		   {6,2, 1}};
		System.out.println("longestIncreasingPath of matrix4 : "+lipMatrix.longestIncreasingPath(matrix4));
	}

}
