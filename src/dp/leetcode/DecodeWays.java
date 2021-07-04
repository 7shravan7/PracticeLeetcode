package dp.leetcode;

/*
 * For memoization approach(numDecodingsWithMemoization) and numDecodings1
 * Time:O(n)
 * Space:O(n)
 * 
 * For numDecodings2
 * Time:O(n)
 * Space:O(1)
 */
import java.util.HashMap;
import java.util.Map;

public class DecodeWays {

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

	public int numDecodings(String str) {
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
		//String str="123123";
		//String str="1201234";
		String str="111111111111111111111111111111111111111111111";
		System.out.println("No of ways to decode "+str+" is "+decodeWays.numDecodings(str));
		System.out.println("No of ways to decode1 "+str+" is "+decodeWays.numDecodings1(str));
		System.out.println("No of ways to decode2 "+str+" is "+decodeWays.numDecodings2(str));
	}

}
