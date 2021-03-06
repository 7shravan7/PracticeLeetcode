package interviewQuestions;

/* **Medium**  695. Max Area of Island
 * 
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded
 * by water.

   Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
   
   Example 1:
	[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 	 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 	 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 	 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 	 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 	 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 	 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 	 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
		Given the above grid, return 6. Note the answer is not 11, because the island must be connected
			 4-directionally.

  Example 2:
	[[0,0,0,0,0,0,0,0]]
		Given the above grid, return 0.
		
  Note: The length of each dimension in the given grid does not exceed 50.
  
  Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 50
	grid[i][j] is either 0 or 1.
 */
public class MaxAreaOfIsland {
	
	/*
	 * Time complexity  : O(row*col)
	 * Space complexity : O(row*col)
	 */
	public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
		int rowSize = grid.length;
		int colSize = grid[0].length;
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				if(grid[i][j]==1) {
					maxArea = Math.max(maxArea, dfs(grid, i, j));
				}
			}
		}
		return maxArea;
	}
	
	
	private int dfs(int[][] grid, int currRow, int currCol) {
		int rowSize = grid.length;
		int colSize = grid[0].length;
		if(currRow<0 || currRow>=rowSize ||currCol<0  || currCol>=colSize || grid[currRow][currCol]==0) {
			return 0;
		}
		grid[currRow][currCol]=0; // to prevent re compute dfs
		return 1 + dfs(grid, currRow-1, currCol)+ dfs(grid, currRow, currCol-1) + dfs(grid, currRow+1, currCol)
		       + dfs(grid, currRow, currCol+1);
	}

	public static void main(String[] args) {
		MaxAreaOfIsland maxAreaIsland = new MaxAreaOfIsland();
		int[][] grid1 = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,1,1,0,1,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,1,0,0,1,0,1,0,0},
				{0,1,0,0,1,1,0,0,1,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0}};
		System.out.println("Max area of grid1 is : "+maxAreaIsland.maxAreaOfIsland(grid1));
		int[][] grid2 = {{0,0,0,0,0,0,0,0}};
		System.out.println("Max area of grid2 is : "+maxAreaIsland.maxAreaOfIsland(grid2));
		int[][] grid3 = {{1,1,0,0,0},
						 {1,1,0,0,0},
				         {0,0,0,1,1},
				         {0,0,0,1,1}};
		System.out.println("Max area of grid3 is : "+maxAreaIsland.maxAreaOfIsland(grid3));
		int[][] grid4 = {{1}};
		System.out.println("Max area of grid4 is : "+maxAreaIsland.maxAreaOfIsland(grid4));
		int[][] grid5 = {{0,1},{1,0}};
		System.out.println("Max area of grid5 is : "+maxAreaIsland.maxAreaOfIsland(grid5));
	}

}
