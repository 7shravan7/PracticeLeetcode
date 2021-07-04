package interviewQuestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* **Hard**   269. Alien Dictionary
 * 
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

   You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted 
   lexicographically by the rules of this new language.

   Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the 
   new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

   A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s 
   comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, 
   then s is smaller if and only if s.length < t.length.

  Example 1:
		Input: words = ["wrt","wrf","er","ett","rftt"]
		Output: "wertf"
	
  Example 2:
		Input: words = ["z","x"]
		Output: "zx"
		
  Example 3:
		Input: words = ["z","x","z"]
		Output: ""
		Explanation: The order is invalid, so return "".
		
  Constraints:
		1 <= words.length <= 100
		1 <= words[i].length <= 100
		words[i] consists of only lowercase English letters. 
 */
public class AlienDictionary {

	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> adjacencyMap = new HashMap<>();
		for(String word: words) {
			for(int i=0;i<word.length();i++) {
				if(!adjacencyMap.containsKey(word.charAt(i))) {
					adjacencyMap.put(word.charAt(i), new HashSet<>());
				}
			}
		}
		// building adjacenyList
		for(int i=0;i<words.length-1;i++) {
			String word1 = words[i];
			String word2 = words[i+1];
			if(word1.length()>word2.length() && word1.startsWith(word2)) { // {welcome, we}
				return "";
			}
			int minLength = Math.min(word1.length(), word2.length());
			for(int j=0;j<minLength;j++) {
				if(word1.charAt(j) != word2.charAt(j)) {
					adjacencyMap.get(word1.charAt(j)).add(word2.charAt(j));
					break;
				}
			}
		}
		// topological sort using dfs
		Map<Character, Boolean> seenRecurseMap = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for(Character ch : adjacencyMap.keySet()) {
			boolean isAcyclic = dfs(adjacencyMap, seenRecurseMap, ch, sb);
			if(!isAcyclic) {
				return "";
			}
		}
		if(sb.length()<adjacencyMap.size()) {
			return "";
		}
		System.out.println("DFS Topological sort :"+ sb.reverse().toString());
		
		// topological sort using kahn's algo (bfs like)
		String toplogicalSortString = kahnsAlgo(adjacencyMap);
		System.out.println("Kahn's Topological sort :"+ toplogicalSortString);
		
		return sb.toString();
	}
	
	private boolean dfs(Map<Character, Set<Character>> adjacencyMap, Map<Character, Boolean> seenRecurseMap,
			Character ch, StringBuilder sb) {
		if(seenRecurseMap.containsKey(ch)) {
			return seenRecurseMap.get(ch); // if it returns false then there is cycle
		}
		seenRecurseMap.put(ch, false); // flag to update started and not yet finished (to keep track of recurse stack ch)
		Set<Character> dependencySet = adjacencyMap.get(ch);
		for(char dependChar : dependencySet) {
			boolean dependResult = dfs(adjacencyMap, seenRecurseMap, dependChar, sb);
			if(!dependResult) { // in case of cycle, it will return false so return only if false directly
				return dependResult; // false
			}
		}
		seenRecurseMap.put(ch, true); // finished processing so updating it to true
		sb.append(ch);
		return true;
	}
	
	private String kahnsAlgo(Map<Character, Set<Character>> adjacencyMap) {
		Map<Character, Integer> inDegreeCountMap = new HashMap<>();
		adjacencyMap.entrySet().forEach(entry->{
			if(!inDegreeCountMap.containsKey(entry.getKey())){
				inDegreeCountMap.put(entry.getKey(), 0);
			}
			Set<Character> chSet = entry.getValue();
			for(char ch: chSet) {
				if(inDegreeCountMap.containsKey(ch)) {
					inDegreeCountMap.put(ch, inDegreeCountMap.get(ch)+1);
				} else {
					inDegreeCountMap.put(ch,1);
				}
			}
		});
		Queue<Character> queue = new LinkedList<>();
		inDegreeCountMap.entrySet().forEach(entry->{
			if(entry.getValue()==0) {
				queue.add(entry.getKey());
			}
		});
		char[] topologicalArr = new char[adjacencyMap.size()];
		int index=0;
		while(!queue.isEmpty()) {
			char ch = queue.poll();
			Set<Character> outDegreeSet = adjacencyMap.get(ch);
			for(char outDegCh : outDegreeSet) {
				int count = inDegreeCountMap.get(outDegCh);
				count = count -1;
				if(count==0) {
					queue.add(outDegCh);
				}
				inDegreeCountMap.put(outDegCh, count);
			}
			topologicalArr[index++] = ch;
		}
		if(index!=adjacencyMap.size()) {
			return "";
		}
		return new String(topologicalArr);
	}

	public static void main(String[] args) {
		AlienDictionary alienDict = new AlienDictionary();
		String[] words1 = {"wrt","wrf","er","ett","rftt"};
		System.out.println("Word1 Alien Order : "+alienDict.alienOrder(words1));
		String[] words2 = {"z","x"};
		System.out.println("Words2 Alien Order : "+alienDict.alienOrder(words2));
		String[] words3 = {"z","x","z"};
		System.out.println("Word3 Alien Order : "+alienDict.alienOrder(words3));
		String[] words4 = {"wxqkj","whqg","cckgh","cdxg","cdxdt","cdht","ktgxt","ktdw","ktdc","jqw","jmc","jmg"};
		System.out.println("Word4 Alien Order : "+alienDict.alienOrder(words4));
	}

}
