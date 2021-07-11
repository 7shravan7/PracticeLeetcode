package interviewQuestions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* **MEDIUM**  139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a 
 * space-separated sequence of one or more dictionary words.
   Note that the same word in the dictionary may be reused multiple times in the segmentation.
   
   Example 1:
   	Input: s = "leetcode", wordDict = ["leet","code"]
	Output: true
	Explanation: Return true because "leetcode" can be segmented as "leet code".
	
   Example 2:
    Input: s = "applepenapple", wordDict = ["apple","pen"]
	Output: true
	Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
	Note that you are allowed to reuse a dictionary word.
	
   Example 3:
   	Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
	Output: false
 */
public class WordBreak {
	
	/*
	 * BFS
	 * Time Complexity : O(n^3)
	 * Space Complexity: O(n)
	 */
	public boolean wordBreakBFS(String str, List<String> wordDict) {
		if(str.isEmpty()) {
			return false;
		}
		boolean[] visitedArray = new boolean[str.length()];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		while(!queue.isEmpty()) {
			int start = queue.poll();
			if(visitedArray[start]) {
				continue;
			}
			for(int end=start+1;end<=str.length();end++) {
				System.out.println(str.substring(start, end));
				if(wordDict.contains(str.substring(start, end))) {
					queue.add(end);
					if(end == str.length()) {
						return true;
					}
				}
			}
			visitedArray[start] = true;
		}
		return false;
	}
	
	/*
	 * Dp approach
	 * Time Complexity : O(n^3)
	 * Space Complexity: O(n)
	 */
	public boolean wordBreak(String str, List<String> wordDict) {
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
            	System.out.println(str.substring(j, i));
                if (dp[j] && wordDict.contains(str.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }

	public static void main(String[] args) {
		WordBreak wordBreak = new WordBreak();
		String str = "catsanddog";
		List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
		System.out.println("Word can be segmented BFS approach : "+wordBreak.wordBreakBFS(str, wordDict));
		System.out.println("Word can be segmented : "+wordBreak.wordBreak(str, wordDict));
	}

}
