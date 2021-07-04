package may_2021Challenges.week_1_1to7;

import java.util.HashSet;
import java.util.Set;

/*
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

  Implement the WordFilter class:

	WordFilter(string[] words) 
		Initializes the object with the words in the dictionary.
	f(string prefix, string suffix) 
		Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. 
		If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, 
		return -1.
 
	Example 1:
		Input
			["WordFilter", "f"]
			[[["apple"]], ["a", "e"]]
		Output
			[null, 0]

		Explanation
			WordFilter wordFilter = new WordFilter(["apple"]);
			wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".

	Constraints:
		1 <= words.length <= 15000
		1 <= words[i].length <= 10
		1 <= prefix.length, suffix.length <= 10
		words[i], prefix and suffix consist of lower-case English letters only.
		At most 15000 calls will be made to the function f.
 */
public class PrefixAandSuffixSearch {
	// PrefixAandSuffixSearch =~ 
	
	TrieNode trie1, trie2;
	public PrefixAandSuffixSearch(String[] words) {
		trie1 = new TrieNode();
		trie2 = new TrieNode();
		int wt = 0;
		for (String word: words) {
			char[] ca = word.toCharArray();

			TrieNode cur = trie1;
			cur.weight.add(wt);
			for (char letter: ca) {
				if (cur.children[letter - 'a'] == null)
					cur.children[letter - 'a'] = new TrieNode();
				cur = cur.children[letter - 'a'];
				cur.weight.add(wt);
			}

			cur = trie2;
			cur.weight.add(wt);
			for (int j = ca.length - 1; j >= 0; --j) {
				char letter = ca[j];
				if (cur.children[letter - 'a'] == null)
					cur.children[letter - 'a'] = new TrieNode();
				cur = cur.children[letter - 'a'];
				cur.weight.add(wt);
			}
			wt++;
		}
	}

	public int f(String prefix, String suffix) {
		TrieNode cur1 = trie1, cur2 = trie2;
		for (char letter: prefix.toCharArray()) {
			if (cur1.children[letter - 'a'] == null) return -1;
			cur1 = cur1.children[letter - 'a'];
		}
		char[] ca = suffix.toCharArray();
		for (int j = ca.length - 1; j >= 0; --j) {
			char letter = ca[j];
			if (cur2.children[letter - 'a'] == null) return -1;
			cur2 = cur2.children[letter - 'a'];
		}

		int ans = -1;
		for (int w1: cur1.weight)
			if (w1 > ans && cur2.weight.contains(w1))
				ans = w1;

		return ans;
	}

	public static void main(String[] args) {

	}

}

class TrieNode {
    TrieNode[] children;
    Set<Integer> weight;
    public TrieNode() {
        children = new TrieNode[26];
        weight = new HashSet();
    }
}
