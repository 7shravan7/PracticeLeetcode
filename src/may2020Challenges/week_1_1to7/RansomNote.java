package codes.LeetCode.may2020Challenges.week_1_1to7;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
   Each letter in the magazine string can only be used once in your ransom note.
   Note: You may assume that both strings contain only lowercase letters.
   canConstruct("a", "b") -> false
   canConstruct("aa", "ab") -> false
   canConstruct("aa", "aab") -> true
 */
public class RansomNote {

	public static boolean canConstruct(String ransomNote, String magazine) {
		Map<Character,Integer> ransomNoteMap = new HashMap<>();
		for(Character c: ransomNote.toCharArray()) {
			int count = 1;
			if(ransomNoteMap.containsKey(c)) {
				count = ransomNoteMap.get(c)+1;
			}
			ransomNoteMap.put(c, count);
		}
		for(Character m: magazine.toCharArray()) {
			if(ransomNoteMap.containsKey(m)) {
				int count = ransomNoteMap.get(m);
				if(count>1) {
					count--;
					ransomNoteMap.put(m, count);
				} else {
					ransomNoteMap.remove(m);
				}
			}
		}
		return ransomNoteMap.isEmpty();
	}

	public static void main(String[] args) {
		System.out.println(RansomNote.canConstruct("aa", "ab"));
	}

}
