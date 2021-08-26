package interviewQuestions;

import java.util.Stack;

/* **Hard**    85. Maximal Rectangle
 * 
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing 
 * only 1's and return its area.

	Example 1:
		Input: matrix = [["1","0","1","0","0"],
						 ["1","0","1","1","1"],
						 ["1","1","1","1","1"],
						 ["1","0","0","1","0"]]
		Output: 6
		Explanation: The maximal rectangle is shown in the above picture.
		
	Example 2:
		Input: matrix = []
		Output: 0
		
	Example 3:
		Input: matrix = [["0"]]
		Output: 0
		
	Example 4:
		Input: matrix = [["1"]]
		Output: 1
		
	Example 5:
		Input: matrix = [["0","0"]]
		Output: 0
 
	Constraints:
		rows == matrix.length
		cols == matrix[i].length
		0 <= row, cols <= 200
		matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {
	
	/*
	 * Time Complexity  : O(m*n)  m:no of rows, n: no of cols
	 * Space Complexity : O(n)
	 */
	public int maximalRectangle(char[][] matrix) {
		int rowSize = matrix.length;
		if(rowSize==0) {
			return 0;
		}
		int colSize = matrix[0].length;
		int[] heights = new int[colSize];
		int maxArea = 0;
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				heights[j] = matrix[i][j]=='1' ? heights[j]+1 : 0;
			}
			maxArea = Math.max(maxArea, maxRectInHistogram(heights));
		}
		return maxArea;
	}
	
	private int maxRectInHistogram(int[] heights) {
		int len = heights.length;
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		stack.add(-1);
		for(int i=0;i<len;i++) {
			int currHeight = heights[i];
			while(stack.peek()!=-1 && currHeight<heights[stack.peek()]) {
				int popHeight = heights[stack.pop()];
				maxArea = Math.max(maxArea, popHeight*(i-stack.peek()-1));
			}
			stack.push(i);
		}
		while(stack.peek()!=-1) {
			int popHeight = heights[stack.pop()];
			maxArea = Math.max(maxArea, popHeight*(len-stack.peek()-1));
		}
		return maxArea;
	}

	public static void main(String[] args) {
		MaximalRectangle maxRect = new MaximalRectangle();
		char[][] matrix1 = {{'1','0','1','0','0'},
							{'1','0','1','1','1'},
							{'1','1','1','1','1'},
							{'1','0','0','1','0'}
	  		   		   	   };
		System.out.println("Max area of Matrix1 : "+maxRect.maximalRectangle(matrix1));
		char[][] matrix2 = {};
		System.out.println("Max area of Matrix2 : "+maxRect.maximalRectangle(matrix2));
		char[][] matrix3 = {{'0'}};
		System.out.println("Max area of Matrix3 : "+maxRect.maximalRectangle(matrix3));
		char[][] matrix4 = {{'1'}};
		System.out.println("Max area of Matrix4 : "+maxRect.maximalRectangle(matrix4));
		char[][] matrix5 = {{'0','0'}};
		System.out.println("Max area of Matrix5 : "+maxRect.maximalRectangle(matrix5));
	}

}
