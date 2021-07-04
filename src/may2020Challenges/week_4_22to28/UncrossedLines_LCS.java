package codes.LeetCode.may2020Challenges.week_4_22to28;

public class UncrossedLines_LCS {

	public static int maxUncrossedLines(int[] A, int[] B) {
		int lenA = A.length;
		int lenB = B.length;
		int[][] dp = new int[lenA+1][lenB+1];
		for(int i=0;i<=lenA;i++) {
			dp[i][0] = 0;
		}
		for(int j=1;j<=lenB;j++) {
			dp[0][j] = 0;
		}
		for(int i=1;i<=lenA;i++) {
			for(int j=1;j<=lenB;j++) {
				if(A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
				}
			}
		}
		return dp[lenA][lenB];
	}

	public static void main(String[] args) {
		int[] A = {1,4,2};
		int[] B = {1,2,4};
		System.out.println(UncrossedLines_LCS.maxUncrossedLines(A, B));
		int[] A1 = {2,5,1,2,5};
		int[] B1 = {10,5,2,1,5,2};
		System.out.println(UncrossedLines_LCS.maxUncrossedLines(A1, B1));
		int[] A2= {1,3,7,1,7,5};
		int[] B2 = {1,3,7,1,7,5};
		System.out.println(UncrossedLines_LCS.maxUncrossedLines(A2, B2));
	}

}
