package codes.LeetCode.may2020Challenges.week_4_22to28;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
	
	public static String frequencySort(String s) {
		Map<Character, Integer> characterFreqMap = new HashMap<>();
		int len = s.length();
		for(int i=0; i<len; i++) {
			characterFreqMap.put(s.charAt(i), characterFreqMap.getOrDefault(s.charAt(i), 0) +1);
		}
		PriorityQueue<Character> maxHeap = new PriorityQueue<>((key1,key2)->
			 characterFreqMap.get(key2) - characterFreqMap.get(key1)
		);
		//maxHeap.offer(e);
		maxHeap.addAll(characterFreqMap.keySet());
		StringBuilder sb = new StringBuilder();
		while(!maxHeap.isEmpty()) {
			Character ch = maxHeap.remove();
			int count = characterFreqMap.get(ch);
			for(int i=0;i<count;i++) {
				sb.append(ch);
			}
		}
		return sb.toString();
    }
	
	public static void main(String[] args) {
		String str = "tree";
		System.out.println(SortCharactersByFrequency.frequencySort(str));
		String str1 = "Aabb";
		System.out.println(SortCharactersByFrequency.frequencySort(str1));
	}

}
