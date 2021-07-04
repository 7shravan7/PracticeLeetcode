package interviewQuestions;

import java.util.Stack;

/* **Medium**     394. Decode String
 * 
 * Given an encoded string, return its decoded string.

   The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being 
   repeated exactly k times. Note that k is guaranteed to be a positive integer.

   You may assume that the input string is always valid; No extra white spaces, square brackets are
    well-formed, etc.

   Furthermore, you may assume that the original data does not contain any digits and that digits are only 
   for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

	Example 1:
		Input: s = "3[a]2[bc]"
		Output: "aaabcbc"
		
	Example 2:
		Input: s = "3[a2[c]]"
		Output: "accaccacc"
		
	Example 3:
		Input: s = "2[abc]3[cd]ef"
		Output: "abcabccdcdcdef"
		
	Example 4:
		Input: s = "abc3[cd]xyz"
		Output: "abccdcdcdxyz"

	Constraints:
		1 <= s.length <= 30
		s consists of lowercase English letters, digits, and square brackets '[]'.
		s is guaranteed to be a valid input.
		All the integers in s are in the range [1, 300].
 */
public class DecodeString {

	/*
	 * Time Complexity : O(Max(Count)*n) n -length of string(worstcase 10[abc])
	 * Space Complexity: O(m+n)  m-strStack n-#countStack
	 */
	public String decodeString(String s) {
		if(s.length()==1) {
			return s;
		}
		Stack<Integer> countStack = new Stack<>();
		Stack<StringBuilder> strStack = new Stack<>();
		StringBuilder currentSb = new StringBuilder();
		int count=0;
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if(Character.isDigit(ch)) {
				count = count*10 + ch-'0';
			} else if (ch == '[') {
				countStack.push(count);
				count=0;
				strStack.push(currentSb);
				currentSb = new StringBuilder();
			} else if ( ch == ']') {
				StringBuilder prevSb = strStack.pop();
				int countVal = countStack.pop();
				while(countVal>0) {
					prevSb.append(currentSb);
					countVal--;
				}
				currentSb = prevSb;
			} else {
				currentSb.append(ch);
			}
		}
		return currentSb.toString();
	}

	public static void main(String[] args) {
		DecodeString ds = new DecodeString();
		String s1= "3[a]2[bc]";
		System.out.println(s1+" : "+ds.decodeString(s1));
		String s2= "3[a2[c]]";
		System.out.println(s2+" : "+ds.decodeString(s2));
		String s3= "2[abc]3[cd]ef";
		System.out.println(s3+" : "+ds.decodeString(s3));
		String s4= "abc3[cd]xyz";
		System.out.println(s4+" : "+ds.decodeString(s4));
	}

}
