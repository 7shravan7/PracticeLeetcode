package interviewQuestions.google;

/* **Medium**    562. Longest Line of Consecutive One in Matrix
 * 
 * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.

   The line could be horizontal, vertical, diagonal, or anti-diagonal.

	Example 1:
		Input: mat = [[0,1,1,0],
					  [0,1,1,0],
					  [0,0,0,1]]
		Output: 3
		
	Example 2:
		Input: mat = [[1,1,1,1],
					  [0,1,1,0],
					  [0,0,0,1]]
		Output: 4

	Constraints:
		m == mat.length
		n == mat[i].length
		1 <= m, n <= 104
		1 <= m * n <= 104
		mat[i][j] is either 0 or 1.
 */
public class LongestLineofConsecutiveOneinMatrix {
	
	int longestOne;

	public int longestLine(int[][] mat) {
		longestOne = 0;
		int row = mat.length;
		int col = mat[0].length;
		int max = Math.max(row, col);
		Cell[][] cell = new Cell[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(mat[i][j]==1) {
					cell[i][j] = new Cell(1);
					Cell c = cell[i][j];
					// horizontal left
					if(isValidCell(i, j-1, mat, row, col)) {
						c.h = cell[i][j-1].h + 1;
					}
					// vertical top
					if(isValidCell(i-1, j, mat, row, col)) {
						c.v = cell[i-1][j].v + 1;
					}
					// diagonal
					if(isValidCell(i-1, j-1, mat, row, col)) {
						c.d = cell[i-1][j-1].d + 1;
					}
					// anti-diagonal
					if(isValidCell(i-1, j+1, mat, row, col)) {
						c.a = cell[i-1][j+1].a + 1;
					}
					longestOne = Math.max(longestOne, Math.max(Math.max(c.h,c.v),Math.max(c.d,c.a)));
					if(longestOne == max) {
						return longestOne;
					}
				}
			}
		}
		return longestOne;
	}
	
	private boolean isValidCell(int i,int j, int[][] mat, int row, int col) {
		return  i>=0 && i<row && j>=0 && j<col && mat[i][j]==1;
	}

	public static void main(String[] args) {
		LongestLineofConsecutiveOneinMatrix longestOne = new LongestLineofConsecutiveOneinMatrix();
		int[][] mat1 = {{0,1,1,0},
					    {0,1,1,0},
					    {0,0,0,1}};
		System.out.println("Longest line of one in mat1 : "+longestOne.longestLine(mat1));
		int[][] mat2 = {{1,0,1,1},
			    		{0,1,1,0},
			    		{1,1,0,0}};
		System.out.println("Longest line of one in mat2 : "+longestOne.longestLine(mat2));
	}
	

	class Cell {
		int h; // horizontal
		int v; // vertical
		int d; // diagonal
		int a; // anti-diagonal
		public Cell(int val) {
			h=v=d=a=val;
		}
	}

}
