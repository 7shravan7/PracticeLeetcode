package dp.cci_book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotInGrid {

	// iterative way
	public void uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int col = obstacleGrid[0].length;
		int numberOfWays = 0;
		if(obstacleGrid[0][0] == 1 || obstacleGrid[row-1][col-1] == 1){
			return;
		}
		int[][] dp = new int[row][col];
		dp[0][0] = 1;
		for(int j=1;j<col;j++){
			if(obstacleGrid[0][j]==0){
				dp[0][j] = dp[0][j-1];
			}
		}
		for(int i=1;i<row;i++){
			if(obstacleGrid[i][0]==0){
				dp[i][0] = dp[i-1][0];
			}
		}
		for(int i=1;i<row;i++){
			for(int j=1;j<col;j++){
				if(obstacleGrid[i][j]==0){
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		numberOfWays = dp[row-1][col-1];
		System.out.println("No of ways iterative "+numberOfWays);
	}
	
	// recursive approach without memoization
	private int uniquePathsWithObsRecur(int[][] obstacleGrid, int i, int j) {
		if(i<0 || j<0) {
			return 0;
		}
		if(i==0 && j==0 && obstacleGrid[i][j]==0) {
			return 1;
		}
		int topStep = uniquePathsWithObsRecur(obstacleGrid, i-1,j);
		int leftStep = uniquePathsWithObsRecur(obstacleGrid, i, j-1);
		if(obstacleGrid[i][j]==0) {
			return topStep+leftStep;
		} else {
			return 0;
		}
	}
	
	// recursive approach with memoization
	private int uniquePathsWithObsRecurMem(int[][] obstacleGrid, int[][] dp, int i, int j) {
		// System.out.println("i="+i+",j="+j);
		if(i<0 || j<0) {
			return 0;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}
		if(obstacleGrid[i][j]==1) {
			dp[i][j] = 0;
			return 0;
		} else if (i==0 && j==0) {
			dp[i][j] = 1;
			return 1;
		}
		dp[i][j] = uniquePathsWithObsRecurMem(obstacleGrid, dp, i-1,j) + 
				uniquePathsWithObsRecurMem(obstacleGrid, dp, i, j-1);
		return dp[i][j];
	}
	
	// printUniquePathsWithObsRecurMem to print all paths dp[][] is not needed only to track if already visted or not
	// my version
	private void printUniquePathsWithObsRecurMem(int[][] obstacleGrid, int[][] dp, int i, int j,
			List<Point> pathList) {
		//System.out.println("(i,j)->"+"("+i+","+j+")");
		if(i<0 || j<0) {
			return;
		}
		Point point = new Point(i, j);
		if(i==0 && j==0 && obstacleGrid[i][j]==0) {
			pathList.add(point);
			printPathList(pathList);
			pathList.remove(pathList.size()-1);
			dp[i][j]=1;
		}
		if(dp[i][j]!=-1) {
			if(!pathList.contains(point) && dp[i][j]!=0) {
				pathList.add(point);
			}
		}
		if(obstacleGrid[i][j]==1) {
			dp[i][j] = 0;
			return;
		}
		if(!pathList.contains(point)) {
			pathList.add(point);
		}
		printUniquePathsWithObsRecurMem(obstacleGrid, dp, i-1,j, pathList);
		printUniquePathsWithObsRecurMem(obstacleGrid, dp, i, j-1, pathList);
		if(!pathList.isEmpty()) {
			pathList.remove(pathList.size()-1);
		}
	}
	
	// private 
	private boolean getPath(int[][] maze, int row, int col, List<Point> path, Set<Point> failedPoints) {
		/* If out of bounds or not available, return.*/
		if (col < 0 || row < 0 || maze[row][col]==1) {
			return false;
		}
		Point p = new Point(row, col);
		/* If we've already visited this cell, return. */
		if (failedPoints.contains(p)) { 
			return false;
		}	
		boolean isAtOrigin = (row == 0) && (col == 0);
		/* If there's a path from the start to my current location, add my location.*/
		if (isAtOrigin || getPath(maze, row, col - 1, path, failedPoints) || getPath(maze, row - 1, col, path, failedPoints)) {
			path.add(p);
			return true;
		}
		failedPoints.add(p); // Cache result
		return false;
	} 
	
	// recursion
	public void uniquePathsWithObs(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int col = obstacleGrid[0].length;
		int noOfWays = uniquePathsWithObsRecur(obstacleGrid, row-1, col-1);
		System.out.println("No of ways recur "+noOfWays);
		int[][] dp = new int[row][col];
		// initialize as -1 values
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				dp[i][j] = -1;
			}
		}
		int noOfWaysMem = uniquePathsWithObsRecurMem(obstacleGrid,dp, row-1, col-1);
		System.out.println("No of ways recur with Memoization "+noOfWaysMem);
		// my version/code
		List<Point> pathList = new ArrayList<>();
		int[][] dp1 = new int[row][col];
		// initialize as -1 values
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				dp1[i][j] = -1;
			}
		}
		printUniquePathsWithObsRecurMem(obstacleGrid, dp1, row-1, col-1, pathList);
		// soln in github
		if (obstacleGrid == null || obstacleGrid.length == 0) return;
		List<Point> path = new ArrayList<>();
		Set<Point> failedPoints = new HashSet<>();
		System.out.println("Github solution ");
		if (getPath(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, path, failedPoints)) {
			printPathList(path);
		}
	}
	
	private void printPathList(List<Point> pathList) {
		System.out.println("The Paths ");
		for(int i=pathList.size()-1;i>=0;i--) {
			Point point = pathList.get(i);
			System.out.print("("+point.rowIndex+","+point.colIndex+")");
			if(i!=0) {
				System.out.print(",");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		RobotInGrid rG = new RobotInGrid();
		int[][] obstacleGrid = {{0,0,0,0,0},
				                {0,0,1,0,1},
				                {0,0,0,1,0},
				                {0,0,0,0,0}};
		rG.uniquePathsWithObstacles(obstacleGrid);
		rG.uniquePathsWithObs(obstacleGrid);
	}
	
	private class Point {
		
	    int rowIndex;
		
	    int colIndex;
	    
	    public Point(int rowIndex, int colIndex) {
	    	this.rowIndex = rowIndex;
	    	this.colIndex = colIndex;
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + colIndex;
			result = prime * result + rowIndex;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (colIndex != other.colIndex)
				return false;
			if (rowIndex != other.rowIndex)
				return false;
			return true;
		}

		private RobotInGrid getEnclosingInstance() {
			return RobotInGrid.this;
		}
	    
	    
	    
	}
}
