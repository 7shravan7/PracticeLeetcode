package interviewQuestions;

/* **Medium**  200. Number of Islands
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 *  return the number of islands.
	An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	You may assume all four edges of the grid are all surrounded by water.
 
	Example 1:
	Input: grid = [
  					['1','1','1','1','0'],
  					['1','1','0','1','0'],
  					['1','1','0','0','0'],
  					['0','0','0','0','0']
				  ]
    Output: 1
    
	Example 2:
	Input: grid = [
  					['1','1','0','0','0'],
  					['1','1','0','0','0'],
  					['0','0','1','0','0'],
  					['0','0','0','1','1']
				  ]
	Output: 3

	Constraints:
		m == grid.length
		n == grid[i].length
		1 <= m, n <= 300
		grid[i][j] is '0' or '1'
 */
public class NumberOfIslands {
	
	private boolean isValidCell(int rowIndex, int colIndex,char[][] grid, boolean[][] visited) {
		int rowSize = grid.length;
		int coLSize = grid[0].length;
		return rowIndex>=0 && rowIndex<rowSize && colIndex>=0 && colIndex<coLSize && !visited[rowIndex][colIndex];
	}
	
	private void dfs(int rowIndex, int colIndex, char[][] grid, boolean[][] visited) {
		if(isValidCell(rowIndex, colIndex, grid, visited)) {
			visited[rowIndex][colIndex] = true;
			if(grid[rowIndex][colIndex] == '1') {
				dfs(rowIndex-1, colIndex, grid, visited);
				dfs(rowIndex, colIndex+1, grid, visited);
				dfs(rowIndex+1, colIndex, grid, visited);
				dfs(rowIndex, colIndex-1, grid, visited);
			}
		}
	}
	
	/*
	 * Time Complexity  : O(row*col)
	 * Space Complexity : O(row*col)
	 */
	public int numIslands(char[][] grid) {
		int rowSize = grid.length;
		int coLSize = grid[0].length;
		int numOfIslands = 0;
		boolean[][] visited = new boolean[rowSize][coLSize];
		for(int i=0; i<rowSize; i++) {
			for(int j=0; j<coLSize; j++) {
				if(!visited[i][j] && grid[i][j]=='1') {
					dfs(i, j, grid, visited);
					numOfIslands++;
				}
			}
		}
		return numOfIslands;
	}

	public static void main(String[] args) {
		NumberOfIslands numIslands = new NumberOfIslands();
		char[][] grid1 = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}};
		System.out.println("Number of islands in grid1 : "+ numIslands.numIslands(grid1));
		char[][] grid2 = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'}};
		System.out.println("Number of islands in grid2 : "+ numIslands.numIslands(grid2));
	}

}
