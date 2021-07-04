package interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* **Medium**    49. Group Anagrams
 * 
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.

   An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all 
   the original letters exactly once.

   Example 1:
	Input: strs = ["eat","tea","tan","ate","nat","bat"]
	Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
	
   Example 2:
	Input: strs = [""]
	Output: [[""]]
	
   Example 3:
	Input: strs = ["a"]
	Output: [["a"]]

  Constraints:
	1 <= strs.length <= 104
	0 <= strs[i].length <= 100
	strs[i] consists of lower-case English letters.
 */
public class GroupAnagrams {
	
	/*
	 * Time : 9ms
	 * Time : O(NK) N is the length of strs, and K is the maximum length of a string in strs
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> resultList = new ArrayList<>();
		Map<String, List<String>> charMap = new HashMap<>();
		for(String str: strs) {
			String strCountString = getCharCountString(str);
			List<String> strList = charMap.getOrDefault(strCountString, new ArrayList<>());
			strList.add(str);
			charMap.put(strCountString, strList);
		}
		charMap.entrySet().forEach(entry->{
			resultList.add(entry.getValue());
		});
		return resultList;
	}
	
	private String getCharCountString(String str) {
		int[] charCount = new int[26];
		for(int i=0;i<str.length();i++) {
			Character ch = str.charAt(i);
			charCount[ch-'a'] += 1;
		}
		StringBuilder sb = new StringBuilder(); 
		for(int i=0;i<26;i++) {
			if(charCount[i]>0) {
				sb.append((char)(i+'a')).append(charCount[i]);
			}
		}
		return sb.toString();
	}
	
	/*
	 * Time : 3ms
	 * intersting way to multiply prime numbers(first 26 prime numbers) to find unique bucket/key instead of 
	 * string count encoded string to avoid duplicates
	 * Prime number hash code way
	 */
	public List<List<String>> groupAnagramsPrimeFactors(String[] strs) {
		int[] primes = {2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
				83, 89, 97, 101};
		List<List<String>> resultList = new ArrayList<List<String>>();
		Map<Long, List<String>> hashCodeMap = new HashMap<>();
		for(String str : strs){
			long hashCode = 1;
			for(char x: str.toCharArray()){
				hashCode *= primes[x-'a'];
			}
			if(hashCodeMap.containsKey(hashCode)){
				hashCodeMap.get(hashCode).add(str);
			}else{
				List<String> k = new ArrayList<>();
				k.add(str);
				hashCodeMap.put(hashCode, k);
			}
		}
		for( List<String> k : hashCodeMap.values()){
			resultList.add(k);
		}
		return resultList;
	}
	
	public static void printResult(List<List<String>> resultList) {
		System.out.println();
		for(List<String> list: resultList) {
			for(String str: list) {
				System.out.print(str+",");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		GroupAnagrams groupAnagrams = new GroupAnagrams();
		String[] str1 = {"eat","tea","tan","ate","nat","bat"};
		printResult(groupAnagrams.groupAnagrams(str1));
		printResult(groupAnagrams.groupAnagramsPrimeFactors(str1));
		String[] str2 = {""};
		printResult(groupAnagrams.groupAnagrams(str2));
		printResult(groupAnagrams.groupAnagramsPrimeFactors(str2));
		String[] str3 = {"a"};
		printResult(groupAnagrams.groupAnagrams(str3));
		printResult(groupAnagrams.groupAnagramsPrimeFactors(str3));

	}

}
