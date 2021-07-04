package interviewQuestions;

/* **Easy**  14. Longest Common Prefix
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.

   If there is no common prefix, return an empty string "".

	Example 1:
		Input: strs = ["flower","flow","flight"]
		Output: "fl"
		
	Example 2:
		Input: strs = ["dog","racecar","car"]
		Output: ""
		Explanation: There is no common prefix among the input strings.

	Constraints:
		1 <= strs.length <= 200
		0 <= strs[i].length <= 200
		strs[i] consists of only lower-case English letters.
 */
public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		String str1 = strs[0];
		if(strs.length == 1) {
			return str1;
		}
		for(int i=0;i<str1.length();i++) {
			char ch = str1.charAt(i);
			for(int j=1;j<strs.length;j++) {
				if(i>=strs[j].length() || ch != strs[j].charAt(i)) {
					return str1.substring(0, i);
				}
			}
		}
		return "";
	}

	public static void main(String[] args) {
		LongestCommonPrefix lcp = new LongestCommonPrefix();
		String[] strs1 = {"flower","flow","flight"};
		System.out.println("LCP of strs1 : "+lcp.longestCommonPrefix(strs1));
		String[] strs2 = {"dog","racecar","car"};
		System.out.println("LCP of strs2 : "+lcp.longestCommonPrefix(strs2));
		String[] strs3 = {"a"};
		System.out.println("LCP of strs3 : "+lcp.longestCommonPrefix(strs3));
		String[] strs4 = {"ab","a"};
		System.out.println("LCP of strs4 : "+lcp.longestCommonPrefix(strs4));
	}

}
