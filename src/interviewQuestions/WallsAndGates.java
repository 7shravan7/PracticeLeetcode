package interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/* **Medium**      286. Walls and Gates
 * 
 * You are given an m x n grid rooms initialized with these three possible values.
	i)-1 A wall or an obstacle.
	ii)0 A gate.
	iii)INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may 
	assume that the distance to a gate is less than 2147483647.
	Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, 
	it should be filled with INF.

	Example 1:
		Input: rooms = [[2147483647,        -1,         0,2147483647],
						[2147483647,2147483647,2147483647,		  -1],
						[2147483647,		-1,2147483647,		  -1],
						[		  0,		-1,2147483647,2147483647]]
				Output: [[3,-1,0, 1],
						 [2, 2,1,-1],
						 [1,-1,2,-1],
						 [0,-1,3, 4]]
	Example 2:
		Input: rooms = [[-1]]
		Output: [[-1]]
		
	Example 3:
		Input: rooms = [[2147483647]]
		Output: [[2147483647]]
		
	Example 4:
		Input: rooms = [[0]]
		Output: [[0]]

	Constraints:
		m == rooms.length
		n == rooms[i].length
		1 <= m, n <= 250
		rooms[i][j] is -1, 0, or 231 - 1.
 */
public class WallsAndGates {
	
	/*
	 * Time Complexity : O(m*n)
	 * Space Complexity: O(m*n)
	 */
	public void wallsAndGates(int[][] rooms) {
		int rowSize = rooms.length;
		int colSize = rowSize>0 ? rooms[0].length : 0;
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				if(rooms[i][j]==0) {
					queue.add(new int[] {i,j});
				}
			}
		}
		boolean[][] visited = new boolean[rowSize][colSize];
		int level=0;
		int[] rows = {-1,0,1,0};
		int[] cols = {0,-1,0,1};
		while(!queue.isEmpty()) {
			int size = queue.size();
			level++;
			while(size>0) {
				int[] index = queue.poll();
				int row = index[0];
				int col = index[1];
				visited[row][col] = true;
				for(int i=0;i<4;i++) {
					int newRowIndex = row+rows[i];
					int newColIndex = col+cols[i];
					if(isInfinityCell(rooms, newRowIndex, newColIndex, rowSize, colSize) && 
							!visited[newRowIndex][newColIndex]) {
						rooms[newRowIndex][newColIndex] = level;
						queue.add(new int[] {newRowIndex,newColIndex});
						visited[newRowIndex][newColIndex] = true;
					}
				}
				size--;
			}
		}
	}
	
	private boolean isInfinityCell(int[][] rooms,int row, int col, int rowSize, int colSize) {
		return !(row<0 || col<0 || row>=rowSize || col>=colSize || rooms[row][col]==0 || 
					rooms[row][col]==-1);
	}
	
	public static void printMatrix(int[][] rooms) {
		int rowSize = rooms.length;
		int colSize = rowSize>0 ? rooms[0].length : 0;
		for(int i=0;i<rowSize; i++) {
			for(int j=0; j<colSize; j++) {
				System.out.print(rooms[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		WallsAndGates wallsAndGates = new WallsAndGates();
		int[][] rooms1 = {{2147483647,        -1,         0,2147483647},
						  {2147483647,2147483647,2147483647,		-1},
						  {2147483647,		  -1,2147483647,		-1},
						  {		    0,		  -1,2147483647,2147483647}};
		wallsAndGates.wallsAndGates(rooms1);
		printMatrix(rooms1);
		int[][] rooms2 = {{-1}};
		wallsAndGates.wallsAndGates(rooms2);
		printMatrix(rooms2);
		int[][] rooms3 = {{2147483647}};
		wallsAndGates.wallsAndGates(rooms3);
		printMatrix(rooms3);
		int[][] rooms4 = {{0}};
		wallsAndGates.wallsAndGates(rooms4);
		printMatrix(rooms4);
	}

}
