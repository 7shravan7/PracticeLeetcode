package interviewQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* **Hard**  212. Word Search II
 * 
 * Given an m x n board of characters and a list of strings words, return all words on the board.

   Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. 
   The same letter cell may not be used more than once in a word.
   
   Example 1:
		Input: board = [["o","a","a","n"],
						["e","t","a","e"],
						["i","h","k","r"],
						["i","f","l","v"]], words = ["oath","pea","eat","rain"]
		Output: ["eat","oath"]
	
   Example 2:
		Input: board = [["a","b"],
						["c","d"]], words = ["abcb"]
		Output: []
		
   Constraints:
		m == board.length
		n == board[i].length
		1 <= m, n <= 12
		board[i][j] is a lower case English letter.
		1 <= words.length <= 3 * 104
		1 <= words[i].length <= 10
		words[i] consists of lower case English letters.
		All the strings of words are unique.
 */
public class WordSearchII {
	
	int[] rows = {-1,0,1,0};
	int[] cols = {0,-1,0,1};
	
	private boolean isValidCell(int i,int j, int rowSize, int colSize, boolean[][] visited) {
		return !(i<0 || i>=rowSize || j<0 || j>=colSize || visited[i][j]);
	}
	
	/*
	 * Optimzation possible by 
	 * 1.keeping word is trie so that prefix is not needed
	 * 2.marking the end to be false after it is processed(also we can remove the matched words from trie)
	 * 
	 */
	private void dfs(int i, int j, String prefix, char[][] board, TrieNode node, boolean[][] visited, Set<String> wordsList) {
		int rowSize = board.length;
		int colSize = board[0].length;
		char ch = board[i][j];
		TrieNode chNode = node.characters[ch-'a'];
		if(chNode == null) {
			return;
		}
		String newPrefix = prefix+ch;
		if(chNode.isEnd) {
			wordsList.add(newPrefix);
			if(chNode.isEmpty()) {
				return;
			}
		}
		visited[i][j] = true;
		for(int ii=0;ii<4;ii++) {
			int newRow = i+rows[ii];
			int newCol = j+cols[ii];
			if(isValidCell(newRow, newCol, rowSize, colSize, visited)) {
				dfs(newRow, newCol, newPrefix, board, chNode, visited, wordsList);
			}
		}
		visited[i][j] = false;
	}
	
	/*
	 * Time complexity : O(M
	 */
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> wordsList = new HashSet<>();
		TrieNode trie = buildTrie(words);
		int row = board.length;
		int col = board[0].length;
		boolean[][] visited = new boolean[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				dfs(i, j, "", board, trie, visited, wordsList);
			}
		}
		return new ArrayList<>(wordsList);
	}
	
	private TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for(String word : words) {
			TrieNode node = root;
			for(Character ch: word.toCharArray()) {
				node = node.insert(ch);
			}
			node.isEnd=true;
		}
		return root;
	}
	
	class TrieNode {
		
		TrieNode[] characters;
		boolean isEnd;
		
		public TrieNode() {
			characters = new TrieNode[26];
			isEnd = false;
		}
		
		public TrieNode insert(Character ch) {
			TrieNode node = characters[ch-'a'];
			if(node==null) {
				TrieNode newNode = new TrieNode();
				characters[ch-'a'] = newNode;
				return newNode;
			} else {
				return node;
			}
			
		}
		
		public boolean containsWord(String word) {
			TrieNode node = this;
			for(Character ch:word.toCharArray()) {
				node = node.characters[ch-'a'];
				if(node==null) {
					return false;
				}
			}
			return node.isEnd; // node!=null && 
		}
		
		public boolean isEmpty() {
			for(int i=0;i<26;i++) {
				if(characters[i]!=null) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static void printList(List<String> wordList) {
		for(int i=0;i<wordList.size();i++) {
			System.out.print(wordList.get(i));
			if(i!=wordList.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		WordSearchII wordSearch = new WordSearchII();
		char[][] board1 = {{'o','a','a','n'},
		    			   {'e','t','a','e'},
		    			   {'i','h','k','r'},
		    			   {'i','f','l','v'}};
		String[] words1 = {"oath","pea","eat","rain"};
		List<String> wordsList1 = wordSearch.findWords(board1, words1);
		printList(wordsList1);
		
		char[][] board2 = {{'a','b'},
 			   			   {'c','d'}};
		String[] words2 = {"abcb"};
		List<String> wordsList2 = wordSearch.findWords(board2, words2);
		printList(wordsList2);
		
		char[][] board3 = { {'o','a','b','n'},
							{'o','t','a','e'},
							{'a','h','k','r'},
							{'a','f','l','v'} };
		String[] words3 = {"oa","oaa"};
		List<String> wordsList3 = wordSearch.findWords(board3, words3);
		printList(wordsList3);
	}

}
