package dp.leetcode;

/* **Hard**   10. Regular Expression Matching
 * 
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' 
 * and '*' where
	'.' Matches any single character​​​
	'*' Matches zero or more of the preceding element
	The matching should cover the entire input string (not partial)
	
	Example 1:
	Input: s = "aa", p = "a"
	Output: false
	Explanation: "a" does not match the entire string "aa".
	
	Example 2:
	Input: s = "aa", p = "a*"
	Output: true
	Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, 
		it becomes "aa".

	Example 3:
	Input: s = "ab", p = ".*"
	Output: true
	Explanation: ".*" means "zero or more (*) of any character (.)".
 */
public class RegularExpressionMatching {
	
	/*
	 * Time Complexity : O(T+P)2^(T+ P/2).
	 * Space Complexity: O(T+P)2^(T+ P/2).
	 */
	public boolean isMatch(String str, String pattern) {
		if(pattern.isEmpty()) {
			return str.isEmpty();
		}
//		System.out.println("isMatch("+str+","+pattern+")");
		boolean isFirstCharMatch = !str.isEmpty() && 
				((str.charAt(0) == pattern.charAt(0)) || (pattern.charAt(0) == '.'));
		if(pattern.length()>1 && pattern.charAt(1) == '*') {
			return isMatch(str, pattern.substring(2)) || 
					(isFirstCharMatch && isMatch(str.substring(1), pattern));
		} else {
			return isFirstCharMatch && isMatch(str.substring(1), pattern.substring(1));
		}
	}
	
	/*
	 * Time Complexity : O(TP)
	 * Space Complexity: O(TP).
	 */
	public boolean isMatchDp(String str, String pattern) {
		int[][] dp = new int[str.length()+1][pattern.length()+1];
		boolean result = isMatchDp(str, 0, pattern, 0, dp);
		return result;
	}

	private boolean isMatchDp(String str, int strStartIndex, String pattern, int patternStartIndex,
			int[][] dp) {
		int patternLength = pattern.length();
		int strLength = str.length();
		if(patternStartIndex==patternLength) {
			return strStartIndex==strLength;
		}
		System.out.println("strStartIndex : "+ strStartIndex+", patternStartIndex: "+patternStartIndex);
		if(dp[strStartIndex][patternStartIndex]!=0) {
			return dp[strStartIndex][patternStartIndex]==1;
		}
		int result = 0;
		boolean isFirstCharMatch = (strStartIndex<strLength) && 
				(str.charAt(strStartIndex) == pattern.charAt(patternStartIndex) || pattern.charAt(patternStartIndex) =='.');
		if(patternStartIndex+1<patternLength &&  pattern.charAt(patternStartIndex+1)=='*') {
			boolean nextPatternMatch = isMatchDp(str, strStartIndex, pattern, patternStartIndex+2, dp);
			boolean nextStringMatch = isFirstCharMatch &&
					isMatchDp(str, strStartIndex+1, pattern, patternStartIndex, dp);
			if(nextPatternMatch||nextStringMatch) {
				result = 1;
			} else {
				result = -1;
			}
		} else {
			result = isFirstCharMatch && isMatchDp(str, strStartIndex+1, pattern, patternStartIndex+1, dp) ? 1:-1;
		}
		dp[strStartIndex][patternStartIndex] = result;
		return result==1;
	}

	public static void main(String[] args) {
		RegularExpressionMatching rem = new RegularExpressionMatching();
		String str="aab";
		String pattern = "a.*";
		System.out.println("isMatch("+str+","+pattern+") : "+rem.isMatch(str, pattern));
		System.out.println("isMatch("+str+","+pattern+") : "+rem.isMatchDp(str, pattern));
	}

}
