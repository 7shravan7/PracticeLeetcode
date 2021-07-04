package dp;

public class KnightTour {
	
	private static boolean isSafe(int[][] arr, boolean[][] visitedArr, int row, int col,int i,int j) {
		return (i>=0 && i<row)&&(j>=0 && j<col)&&visitedArr[i][j]==false;
	}
	
	private static void doDfs(int[][] arr, boolean[][] visitedArr, int row, int col,int i,int j,int dis) {
		int[] moveRowArr = {-2,-2,-1,-1,1,1,2,2};
		int[] moveColArr = {-1,1,-2,2,-2,2,-1,1};
		visitedArr[i][j] = true;
		arr[i][j] = Math.min(arr[i][j], dis);
		if(arr[i][j] != dis) {
			return;
		}
		for(int moveIndex=0;moveIndex<8;moveIndex++) {
			int newRow = i+moveRowArr[moveIndex];
			int newCol = j+moveColArr[moveIndex];
			if(isSafe(arr, visitedArr, row, col, newRow, newCol)) {
				doDfs(arr, visitedArr, row, col, newRow, newCol,dis+1);
			}
		}
		visitedArr[i][j] = false;
	}

	private static void knightTour(int[][] arr, boolean[][] visitedArr, int row, int col) {
		doDfs(arr, visitedArr, row, col, 0, 0, 0);
	}

	
	public static void main(String[] args) {
		int[][] arr = new int[8][8];
		boolean[][] visitedArr = new boolean[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				arr[i][j] = Integer.MAX_VALUE;
			}
		}
		knightTour(arr,visitedArr,8,8);
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(arr[i][j]>9) {
					System.out.print(arr[i][j]+" ");
				} else {
					System.out.print(" "+arr[i][j]+" ");
				}
			}
			System.out.println();
		}
	}
}
