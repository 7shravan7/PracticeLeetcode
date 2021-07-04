package random;

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
 */
public class MaxAreaOfIsland {
	
	int maxArea = 0;
	
	private boolean isLand(int currRowIndex, int currColIndex, int[][] grid) {
		int rowSize = grid.length;
		int colSize = grid[0].length;
		return currRowIndex>=0 && currRowIndex<rowSize && currColIndex>=0 && currColIndex<colSize 
				&& grid[currRowIndex][currColIndex]==1;
	}
	
	private int dfs(int rowIndex, int colIndex, int[][] grid, boolean[][] visited) {
		int[] rowArr = {-1,0,0,1};
		int[] colArr = {0,-1,1,0};
		int count=0;
		visited[rowIndex][colIndex] = true;
		if(grid[rowIndex][colIndex] == 1) {
			count=1;
		}
		for(int i=0;i<4;i++) {
			int newRowIndex = rowIndex+rowArr[i];
			int newColIndex = colIndex+colArr[i];
			if(isLand(newRowIndex, newColIndex, grid) && !visited[newRowIndex][newColIndex]) {
				count += dfs(newRowIndex, newColIndex, grid, visited);
			}
		}
		if(count>maxArea) {
			maxArea = count;
		}
		return count;
	}
	
	public int maxAreaOfIsland(int[][] grid) {
		int rowSize = grid.length;
		int colSize = grid[0].length;
		boolean[][] visited = new boolean[rowSize][colSize];
		maxArea = 0;
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				if(grid[i][j]==1 && !visited[i][j]) {
					dfs(i, j, grid, visited);
				}
			}
		}
        return maxArea;
    }

	public static void main(String[] args) {
		MaxAreaOfIsland island = new MaxAreaOfIsland();
		int[][] grid1 = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
			 	 		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
			 	 		 {0,1,1,0,1,0,0,0,0,0,0,0,0},
			 	 		 {0,1,0,0,1,1,0,0,1,0,1,0,0},
			 	 		 {0,1,0,0,1,1,0,0,1,1,1,0,0},
			 	 		 {0,0,0,0,0,0,0,0,0,0,1,0,0},
			 	 		 {0,0,0,0,0,0,0,1,1,1,0,0,0},
			 	 		 {0,0,0,0,0,0,0,1,1,0,0,0,0}};
		System.out.println("Max area of grid1 is : "+island.maxAreaOfIsland(grid1));
		int[][] grid2 = {{0,0,0,0,0,0,0,0}};
		System.out.println("Max area of grid2 is : "+island.maxAreaOfIsland(grid2));
		int[][] grid3 = {{1,1,0,0,0},
				 		 {1,1,0,0,0},
				 		 {0,0,0,1,1},
				 		 {0,0,0,1,1}};
		System.out.println("Max area of grid3 is : "+island.maxAreaOfIsland(grid3));
		int[][] grid4 = {{1}};
		System.out.println("Max area of grid4 is : "+island.maxAreaOfIsland(grid4));
		int[][] grid5 = {{0,1},{1,0}};
		System.out.println("Max area of grid5 is : "+island.maxAreaOfIsland(grid5));
	}

}
