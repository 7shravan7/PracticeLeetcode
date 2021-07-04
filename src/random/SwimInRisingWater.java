package random;

import java.util.PriorityQueue;

/* **Hard**   778. Swim in Rising Water
 * 
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

   Now rain starts to fall. At time t, the depth of the water everywhere is t. 
   You can swim from a square to another 4-directionally adjacent square if and only if the elevation of 
   both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must 
   stay within the boundaries of the grid during your swim.

   You start at the top left square (0, 0). What is the least time until you can reach the bottom right 
   square (N-1, N-1)?
   
   Example 1:
	Input: [[0,2],[1,3]]
	Output: 3
	Explanation:
	    [0 2]
	    [1 3]
		At time 0, you are in grid location (0, 0).
		You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
		You cannot reach point (1, 1) until time 3.
		When the depth of water is 3, we can swim anywhere inside the grid.
		
   Example 2:
	Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
	Output: 16
	Explanation:
 		0.  1.  2.  3.  4.
		24  23  22  21  5.
		12. 13. 14. 15. 16.
		11. 17  18  19  20
		10.  9.  8.  7.  6.
		The final route is marked in bold(here dot).
		We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
	
	Note:
	2 <= N <= 50.
	grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater {

	class Point {
		int rowIndex;
		int colIndex;
		int elevation;
		public Point(int rowIndex, int colIndex, int elevation) {
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
			this.elevation = elevation;
		}
	}
	
	private boolean isValidCell(int rowIndex, int colIndex, int size) {
		return rowIndex>=0 && rowIndex<size && colIndex>=0 && colIndex<size;
	}
	
	/*
	 * Priority Queue method(min heap) to get least elevation and proceed
	 * Time complexity : O(n^2logn) n^2 nodes and each will need logn time
	 * Space complexity : O(n*n) maximum size of heap
	 * Leetcode runtime : 7ms
	 */
	public int swimInWater(int[][] grid) {
		int size = grid.length;
		boolean[][] visited = new boolean[size][size];
		PriorityQueue<Point> minHeap = new PriorityQueue<>((a,b)->a.elevation-b.elevation);
		minHeap.add(new Point(0, 0, grid[0][0])); // use pq.offer too
		visited[0][0] = true;
		int maxElevation = 0;
		int[] rowArr = {-1,0,1, 0};
		int[] colArr = { 0,1,0,-1};
		while(!minHeap.isEmpty()) {
			Point point = minHeap.poll();
			int currRowIndex = point.rowIndex;
			int currColIndex = point.colIndex;
			int currElevation = point.elevation;
//			System.out.println(currElevation);
			maxElevation = Math.max(maxElevation, currElevation);
			if(currRowIndex == size-1 && currColIndex == size-1) {
				break;
			}
			for(int i=0;i<4;i++) {
				int newRowIndex = currRowIndex + rowArr[i];
				int newColIndex = currColIndex + colArr[i];
				if(isValidCell(newRowIndex, newColIndex, size) && !visited[newRowIndex][newColIndex]) {
//					System.out.println("(newRowIndex,newColIndex):("+newRowIndex+","+newColIndex+")");
					visited[newRowIndex][newColIndex] = true;
					minHeap.add(new Point(newRowIndex, newColIndex, grid[newRowIndex][newColIndex]));
				}
			}
		}
		return maxElevation;
	}
	
	/*
	 * BinarySearch since elevations are 0 to (n*n)-1 and it is a monotonic function
	 * Time complexity : O(n^2logn) n^2 nodes and each will need logn time
	 * Space complexity : O(n*n) maximum size of dfs stack
	 * Leetcode runtime : 2ms
	 */
	public int swimInWaterBinarySearch(int[][] grid) {
		int size = grid.length;
		int low = 0;
		int high = (size*size)-1;
		while(low<high) {
			int mid = low + ((high-low)/2);
			if(dfs(0,0,mid, grid, new boolean[size][size])) {
				high = mid;
				System.out.println("high : "+high);
			} else {
				low = mid+1;
				System.out.println("low : "+low);
			}
		}
		return low;
	}

	private boolean dfs(int rowIndex, int colIndex, int val, int[][] grid, boolean[][] visited) {
		int size = grid.length;
		if(rowIndex<0 || rowIndex>=size || colIndex<0 || colIndex>=size || grid[rowIndex][colIndex]>val ||
				visited[rowIndex][colIndex]) {
			return false;
		}
		visited[rowIndex][colIndex] = true;
		if(rowIndex == size-1 && colIndex == size-1) return true;
		return dfs(rowIndex-1, colIndex, val, grid, visited) || dfs(rowIndex+1, colIndex, val, grid, visited)
				|| dfs(rowIndex, colIndex-1, val, grid, visited) || dfs(rowIndex, colIndex+1, val, grid, visited);
	}

	public static void main(String[] args) {
		SwimInRisingWater swim = new SwimInRisingWater();
		int[][] grid1 = {{0,2},
						 {1,3}};
		System.out.println("Least time to reach bottom in grid1(MinHeap) : "+swim.swimInWater(grid1));
		System.out.println("Least time to reach bottom in grid1(BinarySearch) : "+swim.swimInWaterBinarySearch(grid1));
		int[][] grid2 =  {{0,   1, 12,  3,  4},
						  {2,  24, 10, 21,  5},
						  {23, 13, 14, 15, 16},
						  {11, 17, 18, 19, 20},
						  {22,  9,  8,  7,  6}};
		System.out.println("Least time to reach bottom in grid2(MinHeap) : "+swim.swimInWater(grid2));
		System.out.println("Least time to reach bottom in grid2(BinarySearch) : "+swim.swimInWaterBinarySearch(grid2));
	}

}
