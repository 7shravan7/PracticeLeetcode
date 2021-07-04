package interviewQuestions;

/* **Medium**    1143. Longest Common Subsequence
 * 
 * Given two strings text1 and text2, return the length of their longest common subsequence. 
 * If there is no common subsequence, return 0.

   A subsequence of a string is a new string generated from the original string with some characters 
   (can be none) deleted without changing the relative order of the remaining characters.

	For example, "ace" is a subsequence of "abcde".
	A common subsequence of two strings is a subsequence that is common to both strings.

	Example 1:
		Input: text1 = "abcde", text2 = "ace" 
		Output: 3  
		Explanation: The longest common subsequence is "ace" and its length is 3.
		
	Example 2:
		Input: text1 = "abc", text2 = "abc"
		Output: 3
		Explanation: The longest common subsequence is "abc" and its length is 3.
		
	Example 3:
		Input: text1 = "abc", text2 = "def"
		Output: 0
		Explanation: There is no such common subsequence, so the result is 0.
		
	Constraints:
		1 <= text1.length, text2.length <= 1000
		text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
	
	/*
	 * Time Complexity : O(mn)
	 * Space Complexity: O(mn)
	 */
	public int longestCommonSubsequence(String text1, String text2) {
		int text1Len = text1.length();
		int text2Len = text2.length();
		int[][] dp = new int[text1Len+1][text2Len+1];
		for(int i=0;i<=text1Len;i++) {
			for(int j=0;j<=text2Len;j++) {
				if(i==0 || j==0) {
					dp[i][j] = 0;
				} else {
					if(text1.charAt(i-1) == text2.charAt(j-1)) { // 0 indexed so i-1 and j-1
						dp[i][j] = 1 + dp[i-1][j-1];
					} else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
		}
		printLcs(text1, text2, dp);
		return dp[text1Len][text2Len];
	}
	
	/*
	 * Time Complexity : O(mn)
	 * Space Complexity: O(Min(m,n))
	 */
	public int longestCommonSubsequenceSpaceOptimized(String text1, String text2) {
		if(text1.length()>text2.length()) {
			String temp = text2;
			text2 = text1;
			text1 = temp;
		}
		int text1Len = text1.length();
		int text2Len = text2.length();
		int[] prevRow = new int[text1Len+1];
		int[] currRow = new int[text1Len+1];
		for(int i=0;i<=text2Len;i++) {
			for(int j=0;j<=text1Len;j++) {
				if(i==0 || j==0) {
					currRow[j] = 0;
				} else {
					if(text1.charAt(j-1) == text2.charAt(i-1)) { // 0 indexed so i-1 and j-1
						currRow[j] = 1 + prevRow[j-1];
					} else {
						currRow[j] = Math.max(prevRow[j], currRow[j-1]);
					}
				}
			}
			int[] temp = currRow;
			currRow = prevRow;
			prevRow = temp;
		}
		return prevRow[text1Len]; // because last iteration swap will change current to prev
	}
	
	private void printLcs(String text1, String text2, int[][] dp) {
		int i=text1.length();
		int j=text2.length();
		int len = dp[i][j];
		char[] lcs = new char[len];
		while(i>0 && j>0) {
			if(text1.charAt(i-1) == text2.charAt(j-1)) {
				lcs[len-1] = text1.charAt(i-1);
				i--;
				j--;
				len--;
			} else {
				if(dp[i-1][j]>dp[i][j-1]) { // find which is larger
					i--;
				} else {
					j--;
				}
			}
		}
		System.out.println(new String(lcs));
	}

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String text1 = "abcde";
		String text2 = "ace";
		System.out.println("LCS of "+text1+" and "+ text2+" : "+lcs.longestCommonSubsequence(text1, text2));
		System.out.println("LCS of "+text1+" and "+ text2+" : "+lcs.longestCommonSubsequenceSpaceOptimized(text1, text2));
		String text3 = "abc";
		String text4 = "abc";
		System.out.println("LCS of "+text3+" and "+ text4+" : "+lcs.longestCommonSubsequence(text3, text4));
		String text5 = "AGGTAB";
		String text6 = "GXTXAYB";
		System.out.println("LCS of "+text5+" and "+ text6+" : "+lcs.longestCommonSubsequence(text5, text6));
		String text7 = "actgattag";
		String text8 = "gtgtgatcg";
		System.out.println("LCS of "+text7+" and "+ text8+" : "+lcs.longestCommonSubsequence(text7, text8));
		System.out.println("LCS of "+text7+" and "+ text8+" : "+lcs.longestCommonSubsequenceSpaceOptimized(text7, text8));
	}

}
