package interviewQuestions;

import java.util.Stack;

/* **Hard**   Damerau�Levenshtein distance
 * 
 *  Given two strings word1 and word2, return the minimum number of operations required to
 *  convert word1 to word2.

	You have the following three operations permitted on a word:
		Insert a character
		Delete a character
		Replace a character
		Swap 2 adjacent characters
		
	Example 1:
		Input: word1 = "apple", word2 = "appel"
		Output: 1
		Explanation: 
			apple -> appel (swap 'l' with 'e')
		
	Example 2:
		Input: word1 = "horse", word2 = "ros"
		Output: 3
		Explanation: 
			horse -> rorse (replace 'h' with 'r')
			rorse -> rose (remove 'r')
			rose -> ros (remove 'e')
			
	Example 3:
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
public class EditDistance2 {
	
	/*
	 * Time Complexity : O(mn)
	 * Space Complexity: O(mn)
	 */
	public int minDistance(String word1, String word2) {
		int word1Len = word1.length();
		int word2Len = word2.length();
		int[][] dp = new int[word1Len+1][word2Len+1];
		for(int i=0;i<=word1Len;i++) {
			dp[i][0] = i;
		}
		for(int j=0;j<=word2Len;j++) {
			dp[0][j] = j;
		}
		for(int i=1;i<=word1Len;i++) {
			for(int j=1;j<=word2Len;j++) {
				char ch1 = word1.charAt(i-1);
				char ch2 = word2.charAt(j-1);
				if(ch1 == ch2) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					if((i>=2 && j>=2) && (word1.charAt(i-2) == ch2 && ch1 == word2.charAt(j-2))) {
						dp[i][j] = dp[i-2][j-2] + 1;
					} else {
						dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
					}
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
		while(i>0 && j>0) {
			if(word1.charAt(i-1) != word2.charAt(j-1)) {
				char c1 = word1.charAt(i-1);
				char c2 = word2.charAt(j-1);
				if(i>=2 && j>=2 && c1 == word2.charAt(j-2) && word1.charAt(i-2) == c2) {
					stepsStack.push("Character '"+c1+"' swapped with '"+c2+"'");
					i=i-2;
					j=j-2;
				}else if(dp[i-1][j-1]+1 == dp[i][j]) {
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

	public static void main(String[] args) {
		EditDistance2 editDistance = new EditDistance2();
		String word1 = "apple";
		String word2= "appel";
		System.out.println("Damerau�Levenshtein to convert '"+word1+"' to '"+word2 +"' : "
												+editDistance.minDistance(word1, word2));
		String word3 = "horse";
		String word4= "hros";
		System.out.println("Damerau�Levenshtein to convert '"+word3+"' to '"+word4 +"' : "
												+editDistance.minDistance(word3, word4));
		String word5 = "intention";
		String word6= "execution";
		System.out.println("Damerau�Levenshtein to convert '"+word5+"' to '"+word6 +"' : "
												+editDistance.minDistance(word5, word6));
	}

}
