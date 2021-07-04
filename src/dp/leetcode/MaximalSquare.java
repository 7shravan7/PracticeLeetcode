package dp.leetcode;

public class MaximalSquare {
	
	/*
	 * Time Complexity - O(rowCount*colCount)
	 * Space Complexity - O(rowCount*colCount)
	 */
	public int maximalSquare(char[][] matrix) {
		int rowCount = matrix.length;
		if(rowCount==0) {
			return 0;
		}
		int colCount = matrix[0].length;
		if(colCount==0) {
			return 0;
		}
		int[][] dp = new int[rowCount][colCount];
		int maxSize=0;
		for(int i=0;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				if(matrix[i][j]=='1') {
					if(i==0 || j==0) {
						dp[i][j] = 1;
						maxSize = Math.max(maxSize, dp[i][j]);
					} else {
						dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
						maxSize = Math.max(maxSize, dp[i][j]);
					}
				}
			}
		}
        return maxSize*maxSize;
    }
	/*
	 * instead of using 2d array here we used 1d array to optimize space
	 * 1d array of dp[colvalues]
	 * prev    -> old value of dp[j-1] (which means previous row dp[j-1] i.e., dp[i-1][j-1])
	 * dp[j-1] -> curr value of dp[j-1](which means current row dp[j-1] i.e., dp[i][j-1])
	 * dp[j]   -> old value of dp[j] (which means previous row dp[j] i.e., dp[i-1][j])
	 */
	public int maximalSquare1(char[][] matrix) {
		int rowCount = matrix.length;
		int colCount = rowCount>0? matrix[0].length : 0;
		if(rowCount == 0 | colCount == 0) {
			return 0;
		}
		int[] dp = new int[colCount+1];
		int prev=0;
		int temp=0;
		int maxSize = 0;
		for(int i=1;i<rowCount+1;i++) {
			for(int j=1;j<colCount+1;j++) {
				temp = dp[j];
				if(matrix[i-1][j-1] =='1') {
					dp[j] = Math.min(Math.min(prev, dp[j-1]),dp[j])+1;
					maxSize = Math.max(maxSize, dp[j]);
				} else {
					dp[j] = 0;
				}
				prev=temp;
			}
		}
		return maxSize*maxSize;
	}

	public static void main(String[] args) {
		MaximalSquare ms = new MaximalSquare();
		char[][] arr = {{'1','0','1','0','0'},
						{'1','0','1','1','1'},
						{'1','1','1','1','1'},
						{'1','0','0','1','0'}
			  		   };
		System.out.println(ms.maximalSquare(arr));
		System.out.println(ms.maximalSquare1(arr));
	}

}
