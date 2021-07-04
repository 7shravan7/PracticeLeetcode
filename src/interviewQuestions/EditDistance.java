package interviewQuestions;

import java.util.Stack;

/* **Hard**      72. Edit Distance(Levenshtein distance)
 * 
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

	You have the following three operations permitted on a word:
		Insert a character
		Delete a character
		Replace a character
		
	Example 1:
		Input: word1 = "horse", word2 = "ros"
		Output: 3
		Explanation: 
			horse -> rorse (replace 'h' with 'r')
			rorse -> rose (remove 'r')
			rose -> ros (remove 'e')
			
	Example 2:
		Input: word1 = "intention", word2 = "execution"
		Output: 5
		Explanation: 
			intention -> inention (remove 't')
			inention -> enention (replace 'i' with 'e')
			enention -> exention (replace 'n' with 'x')
			exention -> exection (replace 'n' with 'c')
			exection -> execution (insert 'u')

	Constraints:
		0 <= word1.length, word2.length <= 500
		word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {
	
	private int recur(String word1, String word2) {
		System.out.println("EditDist("+word1+","+word2+")");
		int word1Len = word1.length();
		int word2Len = word2.length();
		if(word1Len==0 && word2Len==0) {
			return 0;
		}
		if(word1Len==0 || word2Len==0) {
			return word1Len==0 ? word2Len : word1Len;
		}
		if(word1.charAt(word1Len-1) == word2.charAt(word2Len-1)) {
			return recur(word1.substring(0,word1Len-1), word2.substring(0,word2Len-1));
		} else {
			System.out.println("Replace");
			int replaceOperation = 1 + recur(word1.substring(0,word1Len-1), word2.substring(0,word2Len-1));
			System.out.println("Insert");
			int insertOperation = 1 + recur(word1.substring(0,word1Len), word2.substring(0,word2Len-1));
			System.out.println("Delete");
			int deleteOperation = 1 + recur(word1.substring(0,word1Len-1), word2.substring(0,word2Len));
			System.out.println("EditDist("+word1+","+word2+")::r="+replaceOperation+",i="+insertOperation+",d="+deleteOperation);
			return Math.min(replaceOperation, Math.min(insertOperation, deleteOperation));
		}
	}
	
	private int recurWithMemoization(String word1, String word2, int[][] dp) {
		System.out.println("EditDist("+word1+","+word2+")");
		int word1Len = word1.length();
		int word2Len = word2.length();
		if(word1Len==0 && word2Len==0) {
			return 0;
		}
		if(word1Len==0 || word2Len==0) {
			return word1Len==0 ? word2Len : word1Len;
		}
		if(dp[word1Len-1][word2Len-1]!=0) {
			System.out.println("**");
			return dp[word1Len-1][word2Len-1];
		}
		if(word1.charAt(word1Len-1) == word2.charAt(word2Len-1)) {
			return recurWithMemoization(word1.substring(0,word1Len-1), word2.substring(0,word2Len-1),dp);
		} else {
			System.out.println("Replace");
			int replaceOperation = 1 + recurWithMemoization(word1.substring(0,word1Len-1), word2.substring(0,word2Len-1), dp);
			System.out.println("Insert");
			int insertOperation = 1 + recurWithMemoization(word1.substring(0,word1Len), word2.substring(0,word2Len-1),dp);
			System.out.println("Delete");
			int deleteOperation = 1 + recurWithMemoization(word1.substring(0,word1Len-1), word2.substring(0,word2Len), dp);
			System.out.println("EditDist("+word1+","+word2+")::r="+replaceOperation+",i="+insertOperation+",d="+deleteOperation);
			int minDist = Math.min(replaceOperation, Math.min(insertOperation, deleteOperation));
			dp[word1Len-1][word2Len-1] = minDist;
			return minDist;
		}
	}
	
	/*
	 * Time complexity  : O(mn)   m and n are length of word1 and word2
	 * Space complexity : O(mn)
	 */
	private int minDistanceDp(String word1, String word2) {
		int word1Len = word1.length();
		int word2Len = word2.length();
		int[][] dp = new int[word1Len+1][word2Len+1];
		// initialize first row and first col to curr index i and j
		for(int i=0;i<=word1Len;i++) {
			dp[i][0] = i;
		}
		for(int j=0;j<=word2Len;j++) {
			dp[0][j] = j;
		}
		// start with i=1;j=1 to i=m;j=n
		// if word1[i]==word2[i] then retain same value as of dp[i-1][j-1]
		// else perform allowed operations such as replace,insert,delete and find min among them and update
		for(int i=1;i<=word1Len;i++) {
			for(int j=1;j<=word2Len;j++) {
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					// 1 + min(replace[i-1,j-1],insert[i,j-1],delete[i-1,j])
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
				}
			}
		}
		printSteps(word1, word2, dp);
		return dp[word1Len][word2Len];
	}
	
	private void printSteps(String word1, String word2, int[][] dp) {
		int i=word1.length();
		int j=word2.length();
		Stack<String> stepsStack = new Stack<>();
		while(i!=0 && j!=0) {
			if(word1.charAt(i-1) != word2.charAt(j-1)) {
				char c1 = word1.charAt(i-1);
				char c2 = word2.charAt(j-1);
				if(dp[i-1][j-1]+1 == dp[i][j]) {
					stepsStack.push("Character '"+c1+"' replaced by '"+c2+"'");
					i=i-1;
					j=j-1;
				} else if (dp[i][j-1]+1 == dp[i][j]) {
					stepsStack.push("Character '"+c2+"' inserted");
					j=j-1;
				} else {
					stepsStack.push("Character '"+c1+"' deleted");
					i=i-1;
				} 
			} else {
				i=i-1;
				j=j-1;
			}
		}
		int index=1;
		while(!stepsStack.isEmpty()) {
			System.out.println("Step "+(index++)+":"+stepsStack.pop());
		}
	}

	public int minDistance(String word1, String word2) {
//		int recursionResult =  recur(word1, word2);
//		int[][] dp = new int[word1.length()][word2.length()];
//		int recurMemoization = recurWithMemoization(word1, word2, dp);
		int minDistanceDp = minDistanceDp(word1, word2);
		return minDistanceDp;
	}

	public static void main(String[] args) {
		EditDistance editDistance = new EditDistance();
		String word1 = "horse";
		String word2= "ros";
		System.out.println("Mindistance to convert '"+word1+"' to '"+word2 +"' : "
												+editDistance.minDistance(word1, word2));
		String word3 = "intention";
		String word4= "execution";
		System.out.println("Mindistance to convert '"+word3+"' to '"+word4 +"' : "
												+editDistance.minDistance(word3, word4));
		String word5 = "apple";
		String word6= "appel";
		System.out.println("Mindistance to convert '"+word5+"' to '"+word6 +"' : "
												+editDistance.minDistance(word5, word6));
	}

}
