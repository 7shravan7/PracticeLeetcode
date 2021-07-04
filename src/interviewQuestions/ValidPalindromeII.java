package interviewQuestions;

/* **Easy***   680. Valid Palindrome II
 * 
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.

	Example 1:
		Input: s = "aba"
		Output: true
		
	Example 2:
		Input: s = "abca"
		Output: true
		Explanation: You could delete the character 'c'.
		
	Example 3:
		Input: s = "abc"
		Output: false
 
	Constraints:
		1 <= s.length <= 105
		s consists of lowercase English letters.
 */
public class ValidPalindromeII {

	public boolean validPalindrome(String s) {
		if(s.length()==0) {
			return false;
		}
		int start = 0;
		int end = s.length()-1;
		while(start<end) {
			if(s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} else {
				return isPalindrome(s, start, end-1) || isPalindrome(s, start+1, end);
			}
		}
		return true;
	}
	
	private boolean isPalindrome(String s, int start, int end) {
		while(start<end) {
			if(s.charAt(start)!=s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		ValidPalindromeII validPalidrome = new ValidPalindromeII();
		String s1 = "aba";
		System.out.println(s1+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s1));
		String s2 = "abca";
		System.out.println(s2+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s2));
		String s3 = "abc";
		System.out.println(s3+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s3));
		String s4 = "acdc";
		System.out.println(s4+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s4));
		String s5 = "acac";
		System.out.println(s5+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s5));
		String s6 = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
		System.out.println(s6+" is valid palindrome if removed atmost one char :" +validPalidrome.validPalindrome(s6));
	}

}
