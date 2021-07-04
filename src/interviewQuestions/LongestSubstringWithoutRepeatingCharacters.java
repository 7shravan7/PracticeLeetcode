package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Medium**   3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.

	Example 1:
		Input: s = "abcabcbb"
		Output: 3
		Explanation: The answer is "abc", with the length of 3.
		
	Example 2:
		Input: s = "bbbbb"
		Output: 1
		Explanation: The answer is "b", with the length of 1.
		
	Example 3:
		Input: s = "pwwkew"
		Output: 3
		Explanation: The answer is "wke", with the length of 3.
		Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
		
	Example 4:
		Input: s = ""
		Output: 0
		
	Constraints:
		0 <= s.length <= 5 * 104
		s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	/*
	 * Time Complexity  : O(n)
	 * Space Complexity : O(# of unqiue characters of input string)
	 */
	public int lengthOfLongestSubstring(String str) {
		Map<Character, Integer> letterIndexMap = new HashMap<>();
		int maxLength = 0;
		int startIndex = 0;
		int endIndex=0;
		String longestString = "";
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(letterIndexMap.containsKey(ch)) {  // if ch is already there in map, increase start to prevIndex+1
				int prevOccurIndex = letterIndexMap.get(ch);
				if(startIndex<=prevOccurIndex) {
					startIndex = prevOccurIndex+1;
				}
			}
			endIndex = i;
			letterIndexMap.put(ch, i);
			if(maxLength<(endIndex-startIndex+1)) {
				maxLength = endIndex-startIndex+1;
				longestString = str.substring(startIndex,endIndex+1);
			}
		}
		System.out.println(longestString);
        return maxLength;
    }

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters longestSubString = new LongestSubstringWithoutRepeatingCharacters();
		String str1 = "tmmzuxt";
		System.out.println("LongestSubstringWithoutRepeatingCharacters ("+str1+") :"
					+longestSubString.lengthOfLongestSubstring(str1));
		String str2 = "bbbbb";
		System.out.println("LongestSubstringWithoutRepeatingCharacters ("+str2+") :"
					+longestSubString.lengthOfLongestSubstring(str2));
		String str3 = "pwwkew";
		System.out.println("LongestSubstringWithoutRepeatingCharacters ("+str3+") :"
					+longestSubString.lengthOfLongestSubstring(str3));
		String str4 = "";
		System.out.println("LongestSubstringWithoutRepeatingCharacters ("+str4+") :"
					+longestSubString.lengthOfLongestSubstring(str4));
	}

}
