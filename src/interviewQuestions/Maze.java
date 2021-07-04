package interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/*
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling 
 * until hitting a wall. When the ball stops, it could choose the next direction.

   Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] 
   and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, 
   otherwise return false.

   You may assume that the borders of the maze are all walls (see examples).

	Example 1:
		Input: maze = [[0,0,1,0,0],
					   [0,0,0,0,0],
					   [0,0,0,1,0],
					   [1,1,0,1,1],
					   [0,0,0,0,0]], start = [0,4], destination = [4,4]
		Output: true
		Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
		
	Example 2:
		Input: maze = [[0,0,1,0,0],
					   [0,0,0,0,0],
					   [0,0,0,1,0],
					   [1,1,0,1,1],
					   [0,0,0,0,0]], start = [0,4], destination = [3,2]
		Output: false
		Explanation: There is no way for the ball to stop at the destination. Notice that you can pass 
						through the destination but you cannot stop there.
						
	Example 3:
		Input: maze = [[0,0,0,0,0],
					   [1,1,0,0,1],
					   [0,0,0,0,0],
					   [0,1,0,0,1],
					   [0,1,0,0,0]], start = [4,3], destination = [0,1]
		Output: false
	
	Constraints:
		m == maze.length
		n == maze[i].length
		1 <= m, n <= 100
		maze[i][j] is 0 or 1.
		start.length == 2
		destination.length == 2
		0 <= startrow, destinationrow <= m
		0 <= startcol, destinationcol <= n
		Both the ball and the destination exist in an empty space, and they will not be in the same position
		 initially.
		The maze contains at least 2 empty spaces.
 */
public class Maze {

	// combine rows[0]cols[0] -> (-1,0):top
	int[] rows = {-1, 0, 1, 0}; 
	int[] cols = { 0,-1, 0, 1};
	
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		boolean isAllStartNeighoursWall = areAllNeighboursWall(maze, start[0], start[1]);
		boolean isAllEndNeighboursWall = areAllNeighboursWall(maze, destination[0], destination[1]);
		if(isAllStartNeighoursWall || isAllEndNeighboursWall) {
			return false;
		}
		if(!isEndStoppable(maze, destination)) {
			return false;
		}
		return bfs(maze, start[0], start[1], destination[0], destination[1]);
	}
	
	private boolean bfs(int[][] maze, int startRow, int startCol, int destRow, int destCol) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{startRow, startCol});
		visited[startRow][startCol] = true;
		while(!queue.isEmpty()) {
			int[] cell = queue.poll();
			System.out.println("queuePoll("+cell[0]+","+cell[1]+")");
			if(cell[0] == destRow && cell[1] == destCol) {
				return true;
			}
			for(int i=0;i<4;i++) {
				int nextRow = cell[0] + rows[i];
				int nextCol = cell[1] + cols[i];
//				System.out.println("("+nextRow+","+nextCol+")");
				while(nextRow>=0 && nextRow<maze.length && nextCol>=0 && nextCol<maze[0].length &&
						maze[nextRow][nextCol]==0) {
//					System.out.println("("+nextRow+","+nextCol+")");
					nextRow += rows[i];
					nextCol += cols[i];
				}
				// subtract one row and one col value to get the last valid cell indexes
				nextRow -= rows[i];
				nextCol -= cols[i];
				if(!visited[nextRow][nextCol]) {
					System.out.println("queueAdd("+nextRow+","+nextCol+")");
					queue.add(new int[] {nextRow, nextCol});
					visited[nextRow][nextCol] = true;
				}
				System.out.println("-----------");
			}
		}
		return false;
	}
	
	private boolean areAllNeighboursWall(int[][] maze, int rowIndex, int colIndex) {
		int rowSize = maze.length;
		int colSize = maze[0].length;
		for(int i=0;i<4;i++) {
			int neighbourRow = rowIndex + rows[i];
			int neighbourCol = colIndex + cols[i];
			if(neighbourRow>=0 && neighbourRow<rowSize && neighbourCol>=0 && neighbourCol<colSize
					&& maze[neighbourRow][neighbourCol]==0) {
				return false;  // if any of neighbours is empty there is path
			}
		}
		return true;
	}
	
	private boolean isEndStoppable(int[][] maze, int[] destination) {
		int destRow = destination[0];
		int destCom = destination[1];
		int count=0;
		for(int i=0;i<4;i++) {
			int neighbourRow = destRow + rows[i];
			int neighbourCol = destCom + cols[i];
			if(!isValidCell(maze, neighbourRow, neighbourCol)) {
				count++;
			}
		}
		return count==3; // if three sides are wall then only it can stop
	}
	
	private boolean isValidCell(int[][] maze, int rowIndex, int colIndex) {
		int rowSize = maze.length;
		int colSize = maze[0].length;
		return !(rowIndex<0 || rowIndex>=rowSize || colIndex<0 || colIndex>=colSize ||
				maze[rowIndex][colIndex]==1);
	}
	
	public static void main(String[] args) {
		Maze m = new Maze();
		int[][] maze1 = {{0,0,1,0,0},
		                 {0,0,0,0,0},
		                 {0,0,0,1,0},
		                 {1,1,0,1,1},
		                 {0,0,0,0,0}};
		int[] start1 = {0,4};
		int[] destination1 = {4,4};
		System.out.println("maze1 has path from start to dest :"+m.hasPath(maze1, start1, destination1));
		int[][] maze2 = {{0,0,1,0,0},
				   		 {0,0,0,0,0},
				   		 {0,0,0,1,0},
				   		 {1,1,0,1,1},
				   		 {0,0,0,0,0}};
		int[] start2 = {0,4};
		int[] destination2 = {3,2};
		System.out.println("maze2 has path from start to dest :"+m.hasPath(maze2, start2, destination2));
		int[][] maze3 = {{0,0,0,0,0},
		   		 		 {1,1,0,0,1},
		   		 		 {0,0,0,0,0},
		   		 		 {0,1,0,0,1},
		   		 		 {0,1,0,0,0}};
		int[] start3 = {4,3};
		int[] destination3 = {0,1};
		System.out.println("maze3 has path from start to dest :"+m.hasPath(maze3, start3, destination3));
	}

}
