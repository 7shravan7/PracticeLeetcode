package interviewQuestions;

import java.util.Arrays;

/* **Medium**  97. Interleaving String
 * 
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

   An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings 
   such that:
		s = s1 + s2 + ... + sn
		t = t1 + t2 + ... + tm
		|n - m| <= 1
		The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
		Note: a + b is the concatenation of strings a and b.
	Example 1:
		Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
		Output: true
	Example 2:
		Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
		Output: false
	Example 3:
		Input: s1 = "", s2 = "", s3 = ""
		Output: true
		
	Constraints:
		0 <= s1.length, s2.length <= 100
		0 <= s3.length <= 200
		s1, s2, and s3 consist of lowercase English letters.
 */
public class InterleavingString {
	
	/*
	 * Time limit exceeded
	 * Time Complexity  : O(2^(m+n))
	 * Space Complexity : O(m+n)
	 */
	private boolean isInterleave(String s1, int i, String s2, int j, String s3, String temp) {
//		System.out.println(temp);
		if(temp.equals(s3)) {
			return true;
		}
		boolean isInterleave = false;
		if(i<s1.length()) {
			isInterleave = isInterleave || isInterleave(s1, i+1, s2, j, s3, temp+s1.charAt(i));
		}
		if(j<s2.length()) {
			isInterleave = isInterleave || isInterleave(s1, i, s2, j+1, s3, temp+s2.charAt(j));
		}
		return isInterleave;
	}
	
	/*
	 * TopDown approach  - 2ms
	 * 
	 * Time Complexity : O(m*n) 
	 * Space Complexity: O(m*n)
	 * 
	 * Example
	 * 	  s1,     s2 	  s3
	 *    aabcc   dbbca   aadbbcbcac
	 *    i		  j		  k
	 *    ----------Here s1[i] == s3[k] so increment both ---------------
	 *    aabcc   dbbca   aadbbcbcac
	 *     i	  j		   k
	 *    ----------Here s1[i] == s3[k] so increment both --------------- 
	 *     aabcc   dbbca   aadbbcbcac
	 *      i	   j		 k
	 *    ----------Here s2[j] == s3[k] so increment both ---------------
	 *    aabcc   dbbca   aadbbcbcac
	 *      i	   j		 k
	 *    ----------Here s1[i] == s3[k] so increment both --------------- 
	 *     aabcc   dbbca   aadbbcbcac
	 *        i	    j		   k
	 *    and goes on until any of ptr reaches end and then, we need other string to be substring of s3.subString(k)
	 */
	private boolean isInterleaveDp(String s1, int i, String s2, int j, String s3, int k, int[][] dp) {
//		System.out.println(temp);
		if(i==s1.length()) {
			return s2.substring(j).equals(s3.substring(k));
		}
		if(j==s2.length()) {
			return s1.substring(i).equals(s3.substring(k));
		}
		if(dp[i][j]!=-1) {
			return dp[i][j]==1;
		}
		boolean isInterleave = false;
		if(s1.charAt(i) == s3.charAt(k)) { // if i and k are equal then increment both the pointer
			isInterleave = isInterleaveDp(s1, i+1, s2, j, s3, k+1, dp);
		}
		if(s2.charAt(j) == s3.charAt(k)) { // if j and k are equal then increment both the pointer
			isInterleave = isInterleave || isInterleaveDp(s1, i, s2, j+1, s3, k+1, dp);
		}
		dp[i][j] = isInterleave ? 1: 0;
		return isInterleave;
	}

	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1.length()+s2.length()!=s3.length()) {
			return false;
		}
		int[][] dp = new int[s1.length()][s2.length()];
		for(int i=0;i<s1.length();i++) {
			Arrays.fill(dp[i], -1);
		}
		boolean isInterLeave = isInterleaveDp(s1, 0, s2, 0, s3, 0, dp);
		return isInterLeave;
	}

	public static void main(String[] args) {
		InterleavingString interleavingString = new InterleavingString();
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		System.out.println("Is s3 interleaving string of s1 and s2 : "+interleavingString.isInterleave(s1, s2, s3));
		String s4 = "aabcc";
		String s5 = "dbbca";
		String s6 = "aadbbbaccc";
		System.out.println("Is s6 interleaving string of s4 and s5 : "+interleavingString.isInterleave(s4, s5, s6));
		String s7 = "";
		String s8 = "";
		String s9 = "";
		System.out.println("Is s9 interleaving string of s7 and s8 : "+interleavingString.isInterleave(s7, s8, s9));
		String s10 = "cabbcaaacacbac";
		String s11 = "acabaabacabcca";
		String s12 = "cacabaabacaabccbabcaaacacbac";
		System.out.println("Is s12 interleaving string of s10 and s11 : "+interleavingString.isInterleave(s10, s11, s12));
	}

}
