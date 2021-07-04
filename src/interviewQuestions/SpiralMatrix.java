package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**   54. Spiral Matrix
 * 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * 	Example 1:
 * 		Input: matrix = [
 * 							[1,2,3],
 * 							[4,5,6],
 * 							[7,8,9]
 * 						]
		Output: [1,2,3,6,9,8,7,4,5]
		
	Example 2:
		Input: matrix = [
							[1,2,3,4],
							[5,6,7,8],
							[9,10,11,12]
						]
		Output: [1,2,3,4,8,12,11,10,9,5,6,7]
		
	Constraints:
		m == matrix.length
		n == matrix[i].length
		1 <= m, n <= 10
		-100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiralOrderList = new ArrayList<>();
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int rowStart = 0;
		int colStart= 0;
		int rowEnd = rowSize-1;
		int colEnd = colSize-1;
		while(rowStart<=rowEnd && colStart<=colEnd) {
			for(int j=colStart;j<=colEnd;j++) {
				spiralOrderList.add(matrix[rowStart][j]);
			}
			rowStart++;
			for(int i=rowStart;i<=rowEnd;i++) {
				spiralOrderList.add(matrix[i][colEnd]);
			}
			colEnd--;
			if(rowStart<=rowEnd) {
				for(int j=colEnd;j>=colStart;j--) {
					spiralOrderList.add(matrix[rowEnd][j]);
				}
				rowEnd--;
			}
			if(colStart<=colEnd) {
				for(int i=rowEnd;i>=rowStart;i--) {
					spiralOrderList.add(matrix[i][colStart]);
				}
				colStart++;
			}
		}
		return spiralOrderList;
	}
	
	public static void printSpiralOrder(List<Integer> spiralOrderList) {
		for(int i=0;i<spiralOrderList.size();i++) {
			System.out.print(spiralOrderList.get(i));
			if(i!=spiralOrderList.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SpiralMatrix spiralMatrix = new SpiralMatrix();
		int[][] matrix1 = { {1,2,3},
							{4,5,6},
							{7,8,9}};
		printSpiralOrder(spiralMatrix.spiralOrder(matrix1));
		int[][] matrix2 = { {1,2,  3, 4},
							{5,6,  7, 8},
							{9,10,11,12}};
		printSpiralOrder(spiralMatrix.spiralOrder(matrix2));
		int[][] matrix3 = { {1,2,  3, 4},
							{5,6,  7, 8},
							{9,10,11,12},
							{13,14,15,16}};
		printSpiralOrder(spiralMatrix.spiralOrder(matrix3));
		int[][] matrix4 = {{1,2}};
		printSpiralOrder(spiralMatrix.spiralOrder(matrix4));
	}

}
