package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**   208. Implement Trie (Prefix Tree)
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and 
 * retrieve keys in a dataset of strings. 
 * There are various applications of this data structure, such as autocomplete and spellchecker.

	Implement the Trie class:
		Trie() Initializes the trie object.
		void insert(String word) Inserts the string word into the trie.
		boolean search(String word) Returns true if the string word is in the trie 
									(i.e., was inserted before), and false otherwise.
		boolean startsWith(String prefix) Returns true if there is a previously inserted string word that 
										  has the prefix prefix, and false otherwise.
 
	Example 1:
		Input
			["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
			[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
		Output
			[null, null, true, false, true, null, true]
		Explanation
			Trie trie = new Trie();
			trie.insert("apple");
			trie.search("apple");   // return True
			trie.search("app");     // return False
			trie.startsWith("app"); // return True
			trie.insert("app");
			trie.search("app");     // return True

	Constraints:
		1 <= word.length, prefix.length <= 2000
		word and prefix consist only of lowercase English letters.
		At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class Trie {
	
	TrieNode root;
	
	 /** Initialize your data structure here. */
    public Trie() {
    	root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    /*
     * Time complexity  : O(m)
	 * Space complexity : O(m)
     */
    public void insert(String word) {
       TrieNode node = root;
       for(int i=0;i<word.length();i++) {
    	   char ch = word.charAt(i);
    	   if(node.containsKey(ch)) {
    		   node = node.get(ch);
    	   } else {
    		   node = node.insert(ch);
    	   }
       }
       node.markEnd();
    }
    
    /** Returns if the word is in the trie. */
    /*
     * Time complexity  : O(m)
	 * Space complexity : O(1)
     */
    public boolean search(String word) {
    	TrieNode temp = root;
    	for(int i=0;i<word.length();i++) {
    		char ch = word.charAt(i);
    		if(!temp.containsKey(ch)) {
    			return false;
    		}
    		temp = temp.get(ch);
    	}
    	return temp!=null && temp.isWord();  
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    /*
     * Time complexity  : O(m)
	 * Space complexity : O(1)
     */
    public boolean startsWith(String prefix) {
    	TrieNode temp = getStartsWithNode(prefix);
    	if(temp == null) {
    		return false;
    	} else if(temp.isWord()){
    		return true;
    	} else {
    		for(int i=0;i<26;i++) {
    			if(temp.children[i]!=null) {
    				return true;
    			}
    		}
    		return  false;
    	}
    }
    
    /** Returns suggestion words with the given prefix. */
    public List<String> getSuggessionList(String prefix) {
    	List<String> suggestionList = new ArrayList<>();
    	TrieNode node = getStartsWithNode(prefix);
    	getSuggession(node, prefix, suggestionList);
    	return suggestionList;
    }
    
    /*
     * Time complexity  : O(m)
	 * Space complexity : O(1)
     */
    public void delete(String word) {
    	delete(word, root, 0);
    }
    
    private TrieNode getStartsWithNode(String prefix) {
    	TrieNode temp = root;
    	for(int i=0;i<prefix.length();i++) {
    		char ch = prefix.charAt(i);
    		if(!temp.containsKey(ch)) {
    			return null;
    		}
    		temp = temp.get(ch);
    	}
    	return temp;
    }
    
    private TrieNode delete(String word, TrieNode node, int index) {
    	if(node == null) {
    		return null;
    	}
    	if(index == word.length()) {
    		if(node.isWord()) {
    			node.isEnd = false;
    		}
    		if(node.isEmpty()) {
    			return null;
    		}
    		return node;
    	}
    	int childIndex = word.charAt(index) - 'a';
    	node.children[childIndex] = delete(word, node.children[childIndex], index+1);
    	if(node.isEmpty() && !node.isWord()) {
    		return null;
    	}
    	return node;
    }
    
    
    private void getSuggession(TrieNode node, String str, List<String> suggestionList) {
    	if(node == null) {
    		return;
    	}
    	if(node.isWord()) {
    		suggestionList.add(str);
    	}
    	for(int i=0;i<26;i++) {
    		if(node.children[i]!=null) {
    			getSuggession(node.children[i], str+(char)('a'+i), suggestionList);
    		}
    	}
    }
    
    public void printSuggession(List<String> suggessionList) {
    	System.out.println("Suggested words");
    	suggessionList.forEach(System.out::println);
    }
    
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("hello");
		trie.insert("hell");
		trie.insert("help");
		trie.insert("hen");
		trie.insert("hend");
		trie.insert("helped");
		System.out.println("search for 'help' : "+trie.search("help"));
		System.out.println("search for 'hen' : "+trie.search("hen"));
		System.out.println("search for 'hel' : "+trie.search("hel"));
		System.out.println("search for 'hell' : "+trie.search("hell"));
		System.out.println("search for 'hellow' : "+trie.search("hellow"));
		System.out.println("search for starts with 'h' : "+trie.startsWith("h"));
		System.out.println("search for starts with 'hell' : "+trie.startsWith("hell"));
		System.out.println("search for starts with 'hellop' : "+trie.startsWith("hellop"));
		trie.printSuggession(trie.getSuggessionList("hel"));
		trie.delete("help");
		trie.delete("abc");
		trie.printSuggession(trie.getSuggessionList("hel"));
	}

}

class TrieNode {
	TrieNode[] children;
	boolean isEnd;
	
	public TrieNode() {
		children = new TrieNode[26];
		isEnd = false;
	}
	
	public TrieNode insert(char ch) {
		TrieNode node = new TrieNode();
		children[ch-'a'] = node;
		return node;
	}
	
	public boolean containsKey(char ch) {
		return children[ch-'a']!=null;
	}
	
	public TrieNode get(char ch) {
		return children[ch-'a'];
	}
	
	public boolean isWord() {
		return isEnd;
	}
	
	public void markEnd() {
		isEnd = true;
	}
	
	public boolean isEmpty() {
    	for(int i=0;i<26;i++) {
			if(children[i]!=null) {
				return false;
			}
		}
		return true;
    }
	
}
