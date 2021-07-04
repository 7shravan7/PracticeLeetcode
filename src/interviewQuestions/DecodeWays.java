package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Medium**   91. Decode Ways
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:

	'A' -> "1"
	'B' -> "2"
	...
	'Z' -> "26"
	To decode an encoded message, all the digits must be grouped then mapped back into letters using the 
	reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
		"AAJF" with the grouping (1 1 10 6)
		"KJF" with the grouping (11 10 6)
	Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different
	from "06".
	Given a string s containing only digits, return the number of ways to decode it.
	The answer is guaranteed to fit in a 32-bit integer.

	Example 1:
		Input: s = "12"
		Output: 2
		Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
		
	Example 2:
		Input: s = "226"
		Output: 3
		Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
		
	Example 3:
		Input: s = "0"
		Output: 0
		Explanation: There is no character that is mapped to a number starting with 0.
				     The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
					 Hence, there are no valid ways to decode this since all digits need to be mapped.
					 
	Example 4:
		Input: s = "06"
		Output: 0
		Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

	Constraints:
		1 <= s.length <= 100
		s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {

	// bottom up approach like staircase problem
	// see numDecodings1 and numDecodings2 for better solution
	public int numDecodings(String str) {
		int strLength  = str.length();
		int[] dp = new int[strLength + 1];
		dp[0] = 1; // base case
		for(int i=1;i<strLength+1;i++) {
			int noOfWays = 0;
			char c = str.charAt(i-1);
			if(c!='0') {
				noOfWays += dp[i-1];
			}
			if(i>1) {
				String twoDigitStr = ""+str.charAt(i-2)+str.charAt(i-1);
				int twoDigitVal = Integer.parseInt(twoDigitStr);
				if(twoDigitVal>10 && twoDigitVal<27) {
					noOfWays += dp[i-2];
				}
			}
			dp[i] = noOfWays;
		}
		return dp[strLength];
	}

	// time limit exceeded
	private int numDecodings(String str, String prefix) {
		//System.out.println(prefix+" + decode("+str+")");
		if(!"".equals(prefix)) {
			int prefixIntVal = Integer.parseInt(prefix);
			if(prefixIntVal==0 || prefixIntVal>26) {
				return 0;
			}
		}
		if(str.isEmpty()) {
			return 1;
		}
		String firstChar = String.valueOf(str.charAt(0));
		if(firstChar.equals("0")){
			return 0;
		}
		if(str.length()==1) {
			return 1;
		}
		String newPrefixOne = str.substring(0,1);
		int newPrefixOneDecodings = numDecodings(str.substring(1), newPrefixOne);
		String newPrefixTwo = str.substring(0,2);
		int newPrefixTwoDecodings = numDecodings(str.substring(2), newPrefixTwo);
		return newPrefixOneDecodings+newPrefixTwoDecodings;
	}

	private int numDecodingsWithMemoization(String str, String prefix,Map<String,Integer> dpMap) {
		//System.out.println(prefix+" + decode("+str+")");
		if(!"".equals(prefix)) {
			int prefixIntVal = Integer.parseInt(prefix);
			if(prefixIntVal==0 || prefixIntVal>26) {
				return 0;
			}
		}
		if(dpMap.containsKey(str)) {
			return dpMap.get(str);
		}
		if(str.isEmpty()) {
			return 1;
		}
		String firstChar = String.valueOf(str.charAt(0));
		if(firstChar.equals("0")){
			return 0;
		}
		if(str.length()==1) {
			return 1;
		}
		String newPrefixOne = str.substring(0,1);
		int newPrefixOneDecodings = numDecodingsWithMemoization(str.substring(1), newPrefixOne, dpMap);
		String newPrefixTwo = str.substring(0,2);
		int newPrefixTwoDecodings = numDecodingsWithMemoization(str.substring(2), newPrefixTwo, dpMap);
		dpMap.put(str, newPrefixOneDecodings+newPrefixTwoDecodings);
		return newPrefixOneDecodings+newPrefixTwoDecodings;
	}

	public int numDecodingsTopDown(String str) {
		Map<String,Integer> dpMap = new HashMap<>();
		int val= numDecodingsWithMemoization(str, "", dpMap);
		return val;
	}

	/*
	 * similar to stair case problem
	 * decode(i) = decode(i-1)  // if char[i]!='0'
	 *             + decode(i-2) // if char[i-1],char[i] should be >9 and <27
	 */
	public int numDecodings1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0;
		for (int i = 2; i <= n; i++) {
			int first = Integer.valueOf(s.substring(i - 1, i));
			int second = Integer.valueOf(s.substring(i - 2, i));
			if (first >= 1 && first <= 9) {
				dp[i] += dp[i-1];  
			}
			if (second >= 10 && second <= 26) {
				dp[i] += dp[i-2];
			}
		}
		return dp[n];
	}

	/*
	 * similar to stair case problem
	 * decode(i) = decode(i-1)  // if char[i]!='0'
	 *             + decode(i-2) // if char[i-1],char[i] should be >9 and <27
	 * same as previous but instead of storing entire array..store it only 2 variables
	 */
	public int numDecodings2(String s) {
		if(s==null || s.isEmpty()) {
			return 0;
		}
		int firstVar = 1;
		int secondVar = s.charAt(0)=='0' ? 0:1;
		for(int i=1;i<s.length();i++) {
			int currVal = 0;
			int singleDigit = Integer.valueOf(s.substring(i,i+1));
			if(singleDigit>0 && singleDigit<9) {
				currVal += secondVar; // decode(i-1)
			}
			int twoDigits = Integer.valueOf(s.substring(i-1,i+1));
			if(twoDigits>9 && twoDigits<27) {
				currVal += firstVar;  // decode(i-2)
			}
			firstVar = secondVar;
			secondVar= currVal;
		}
		return secondVar;
	}


	public static void main(String[] args) {
		DecodeWays decodeWays = new DecodeWays();
		String s0 = "11106";
		System.out.println("No of decode ways ("+s0+") :"+decodeWays.numDecodings(s0));
		String s1 = "12";
		System.out.println("No of decode ways ("+s1+") :"+decodeWays.numDecodings(s1));
		String s2 = "226";
		System.out.println("No of decode ways ("+s2+") :"+decodeWays.numDecodings(s2));
		String s3 = "0";
		System.out.println("No of decode ways ("+s3+") :"+decodeWays.numDecodings(s3));
		String s4 = "06";
		System.out.println("No of decode ways ("+s4+") :"+decodeWays.numDecodings(s4));
	}

}
