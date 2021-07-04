package codes.LeetCode.may2020Challenges.week_3_15to21;

public class CountSquareSubmatriceswithAllOnes {
	
	public static int get1Counts(int[][] matrix, int row, int col) {
		int count = 0;
		for(int i=0; i<row;i++) {
			for(int j=0; j<col; j++) {
				if(matrix[i][j]==1) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static int[][] getProcMatrix(int[][] matrix, int row, int col){
		int[][] newMat = new int[row-1][col-1];
		for(int i=0;i<row-1;i++) {
			for(int j=0;j<col-1;j++) {
				if(matrix[i][j]==1 && matrix[i][j+1]==1 && matrix[i+1][j]==1 && matrix[i+1][j+1]==1) {
					newMat[i][j] = 1;
				} else {
					newMat[i][j] = 0;
				}
			}
		}
		return newMat;
	}
	
	public static int countSquares(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int count = get1Counts(matrix, row, col);
		int[][] tempMat = matrix;
		while(row>1 && col>1) {
			int[][] newMat = getProcMatrix(tempMat, row, col);
			tempMat = newMat;
			row = tempMat.length;
			col = tempMat[0].length;
			count += get1Counts(tempMat, row, col);
		}
        return count;
    }

	public static void main(String[] args) {
		int[][] inputMatrix = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
		System.out.println(CountSquareSubmatriceswithAllOnes.countSquares(inputMatrix));
		int[][] inputMatrix1 = {{1,0,1},{1,1,0},{1,1,0}};
		System.out.println(CountSquareSubmatriceswithAllOnes.countSquares(inputMatrix1));
		int[][] inputMatrix2 = {{0},{1}};
		System.out.println(CountSquareSubmatriceswithAllOnes.countSquares(inputMatrix2));
	}

}
