package dp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* **Hard**   140. Word Break II
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where
 *  each word is a valid dictionary word. Return all such possible sentences in any order.

	Note that the same word in the dictionary may be reused multiple times in the segmentation
	
	Example 1
	   	Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
		Output: ["cats and dog","cat sand dog"]
		
	Example 2
		Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
		Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
		Explanation: Note that you are allowed to reuse a dictionary word.
		
	Example 3
	    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
		Output: []
 */
public class WordBreak2 {
	
	public void wordBreakDfs(String str, int startIndex, int endIndex, List<String> wordDict, 
			List<String> resultList, List<String> wordBreakList) {
		if(endIndex > str.length()) {
			return;
		}
		String subString = str.substring(startIndex, endIndex);
		System.out.println(subString+ " "+startIndex+":"+endIndex);
		if(wordDict.contains(subString)) {
			resultList.add(subString);
			if(endIndex == str.length()) {
				StringBuilder workBreakSb= new StringBuilder();
				for(int ii=0;ii<resultList.size();ii++) {
					workBreakSb.append(resultList.get(ii));
					if(ii<resultList.size()-1) {
						workBreakSb.append(" ");
					}
				}
				wordBreakList.add(workBreakSb.toString());
			} else {
				wordBreakDfs(str, endIndex, endIndex+1, wordDict, resultList, wordBreakList);
			}
			resultList.remove(resultList.size()-1);
		}
		wordBreakDfs(str, startIndex, endIndex+1,wordDict, resultList, wordBreakList);
	}
	
	// dfs approach
	public List<String> wordBreak(String str, List<String> wordDict) {
		List<String> wordBreakList = new ArrayList<>();
		List<String> resultList = new ArrayList<>();
		if(str.isEmpty()) {
			return wordBreakList;
		}
		wordBreakDfs(str, 0, 1, wordDict, resultList, wordBreakList);
//		wordBreakBfs(str, wordDict, resultList, wordBreakList);
		System.out.println("----------------");
		return wordBreakList;
	}
	
	public void printList(List<String> resultList) {
		for(String result: resultList) {
			System.out.println(result);
		}
	}
	
	private void wordBreakBfs(String str, List<String> wordDict, List<String> resultList,
			List<String> wordBreakList){
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		while(!queue.isEmpty()) {
			int startIndex = queue.poll();
			for(int end=startIndex+1;end<=str.length();end++) {
				String subString = str.substring(startIndex, end);
				if(wordDict.contains(subString)) {
					resultList.add(subString);
					if(end==str.length()) {
						StringBuilder workBreakSb= new StringBuilder();
						for(int ii=0;ii<resultList.size();ii++) {
							workBreakSb.append(resultList.get(ii));
							if(ii<resultList.size()-1) {
								workBreakSb.append(" ");
							}
						}
						wordBreakList.add(workBreakSb.toString());
					} else {
						queue.add(end);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		WordBreak2 wb2 = new WordBreak2();
		String str= "catsanddog";
		List<String> wordDict= Arrays.asList("cat","cats","and","sand","dog","a","aa","aaa","nd");
		List<String> wordBreakList = wb2.wordBreak(str, wordDict);
		wb2.printList(wordBreakList);
		
	}

}
