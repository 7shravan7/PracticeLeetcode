package interviewQuestions;

/* **Easy**   953. Verifying an Alien Dictionary
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. 
 * The order of the alphabet is some permutation of lowercase letters.

   Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
    the given words are sorted lexicographicaly in this alien language.

	Example 1:
		Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
		Output: true
		Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
		
	Example 2:
		Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
		Output: false
		Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
		
	Example 3:
		Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
		Output: false
		Explanation: The first three characters "app" match, and the second string is shorter (in size.)
		 According to lexicographical rules "apple" > "app", because 'l' > '', where '' is defined as the blank character 
		 which is less than any other character (More info).
		 
	Constraints:
		1 <= words.length <= 100
		1 <= words[i].length <= 20
		order.length == 26
		All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAlienDictionary {

	/*
	 * Time complexity : O(M) (# of chars in words)
	 * Space complexity: O(1) (26 in all cases)
	 */
	public boolean isAlienSorted(String[] words, String order) {
		int[] orderMap = new int[26];
		for(int i=0;i<order.length();i++) {
			orderMap[order.charAt(i)-'a'] = i;
		}
		for(int i=0;i<words.length-1;i++) {
			if(!compare(words[i], words[i+1], orderMap)) {
				return false;
			}
		}
		return true;
	}

	private boolean compare(String word1, String word2, int[] orderMap) {
		for(int i=0;i<word1.length();i++) {
			if(i>=word2.length()) { // if both have same same chars and length of next word is small (apple and app)
				return false;
			}
			if(word1.charAt(i)!=word2.charAt(i)) {  // check only if both chars are diff
				return orderMap[word1.charAt(i) - 'a'] < orderMap[word2.charAt(i) - 'a'];
			}
		}
		return true;
	}

	public static void main(String[] args) {
		VerifyingAlienDictionary alienDictionary = new VerifyingAlienDictionary();
		String[] words = {"hello","leetcode"};
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		System.out.println("words is sorted : "+alienDictionary.isAlienSorted(words, order));
		String[] words1 = {"word","world","row"};
		String order1 = "worldabcefghijkmnpqstuvxyz";
		System.out.println("words1 is sorted : "+alienDictionary.isAlienSorted(words1, order1));
		String[] words2 = {"apple","app"};
		String order2 = "abcdefghijklmnopqrstuvwxyz";
		System.out.println("words2 is sorted : "+alienDictionary.isAlienSorted(words2, order2));
	}

}
