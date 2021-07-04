package interviewQuestions;

/* **Medium**   48. Rotate Image
 * 
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

	You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
	DO NOT allocate another 2D matrix and do the rotation.
	
	Example 1:
		Input: matrix = [[1,2,3],
						 [4,5,6],
						 [7,8,9]]
						 
		Output: 		[[7,4,1],
						 [8,5,2],
						 [9,6,3]]
	
	Example 2:
		Input: matrix = [[5, 1, 9, 11],
						 [2, 4, 8, 10],
						 [13,3, 6, 7],
						 [15,14,12,16]]
						 
		Output: [[15,13 ,2, 5],
				 [14, 3, 4, 1],
				 [12, 6, 8, 9],
				 [16, 7,10,11]]
		
	Example 3:
		Input: matrix = [[1]]
		Output: [[1]]
		
	Example 4:
		Input: matrix = [[1,2],
						 [3,4]]
		Output: [[3,1],
				 [4,2]]

	Constraints:
		matrix.length == n
		matrix[i].length == n
		1 <= n <= 20
		-1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
	
	/*
	 * Time : 0ms
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length; // since square matrix n*n
		int left=0;
		int right=n-1;
		while(left<right) {
			int top = left;
			int bottom = right; // because it is square matrix top==left and bottom==right
			for(int i=0;i<(right-left);i++) {
				int topLeft = matrix[top][left+i];
				matrix[top][left+i] = matrix[bottom-i][left];
				matrix[bottom-i][left] = matrix[bottom][right-i];
				matrix[bottom][right-i] = matrix[top+i][right];
				matrix[top+i][right] = topLeft;
			}
			left++;
			right--;
		}
	}
	
	/*
	 * Time : 0ms
	 * Solution works only for square matrix when there are equal no of rows and columns
	 */
	public void rotateByTranspose(int[][] matrix) {
		int n = matrix.length; // since square matrix n*n
		// Step 1: Transpose the matrix
		for(int i=0;i<n;i++) {
			for(int j=i;j<n;j++) {
				if(i!=j) {
					int temp = matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i] = temp;
				}
			}
		}
		// Step 2: Reverse each row Elements
		for(int i=0; i<n;i++) {
			int start=0;
			int end=n-1;
			while(start<end) {
				int temp = matrix[i][start];
				matrix[i][start] = matrix[i][end];
				matrix[i][end] = temp;
				start++;
				end--;
			}
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int row=0;row<matrix.length;row++) {
			for(int col=0;col<matrix[0].length;col++) {
				System.out.print(matrix[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		RotateImage rotateImage = new RotateImage();
		int[][] matrix1 = {{1,2,3},
						   {4,5,6},
						   {7,8,9}};
		rotateImage.rotateByTranspose(matrix1);
		printMatrix(matrix1);
		int[][] matrix2 = {{5, 1, 9, 11},
				   		   {2, 4, 8, 10},
				   		   {13,3, 6, 7},
				   		   {15,14,12,16}};
		rotateImage.rotateByTranspose(matrix2);
		printMatrix(matrix2);
		int[][] matrix3 = {{1}};
		rotateImage.rotateByTranspose(matrix3);
		printMatrix(matrix3);
		int[][] matrix4 = {{1,2},
						   {3,4}};
		rotateImage.rotateByTranspose(matrix4);
		printMatrix(matrix4);
	}

}
