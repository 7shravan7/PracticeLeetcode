package interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* **Medium**  	17. Letter Combinations of a Phone Number
 * 
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could 
 * represent. Return the answer in any order.

   A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any 
   letters.
   
   Example 1:
		Input: digits = "23"
		Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
		
   Example 2:
		Input: digits = ""
		Output: []
		
   Example 3:
		Input: digits = "2"
		Output: ["a","b","c"]

  Constraints:
		0 <= digits.length <= 4
		digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfPhoneNumber {
	
	/*
	 * Time : 5ms
	 */
	public List<String> letterCombinationsBfs(String digits) {
		List<String> resultList = new ArrayList<>();
		Queue<String> queue = new LinkedList<String>();
		if(digits.isEmpty()) return resultList;
		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		queue.add("");
		for(int i =0; i<digits.length();i++){
			int numValue = digits.charAt(i)-'0';
			int queueSize = queue.size();
			while(queueSize>0){
				String prevStr = queue.remove();
				for(char str : mapping[numValue].toCharArray()) {
					queue.add(prevStr+str);
				}
				queueSize--;
			}
		}
		resultList.addAll(queue);
		return resultList;
	}
	
	public List<String> letterCombinations(String digits) {
		List<String> resultList = new ArrayList<>();
		if(digits.isEmpty()) {
			return resultList;
		}
		String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		StringBuilder sb = new StringBuilder();
		combinationsByBacktrack(0, digits, mapping, sb, resultList);
		return resultList;
	}
	
	/*
	 * Time : 0ms
	 */
	private void combinationsByBacktrack(int currIndex, String digits, String[] mapping, StringBuilder sb,
			List<String> resultList) {
		if(currIndex==digits.length()) {
			resultList.add(sb.toString());
			return;
		}
		int digitNumValue = digits.charAt(currIndex)-'0';
		String mappedString = mapping[digitNumValue];
		for(int i=0;i<mappedString.length();i++) {
			sb.append(mappedString.charAt(i));
			combinationsByBacktrack(currIndex+1, digits, mapping, sb, resultList);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	public static void printList(List<String> resultList) {
		System.out.println();
		for(int i=0;i<resultList.size();i++) {
			System.out.print(resultList.get(i));
			if(i!=resultList.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LetterCombinationsOfPhoneNumber combinations = new LetterCombinationsOfPhoneNumber();
		String digits1 = "23";
		List<String> resultList1 = combinations.letterCombinations(digits1);	
		printList(resultList1);
		String digits2 = "";
		List<String> resultList2 = combinations.letterCombinations(digits2);	
		printList(resultList2);
		String digits3 = "2";
		List<String> resultList3 = combinations.letterCombinations(digits3);	
		printList(resultList3);
		String digits4 = "7472826";
		List<String> resultList4 = combinations.letterCombinations(digits4);	
		printList(resultList4);
		String digits5 = "6844";
		List<String> resultList5 = combinations.letterCombinations(digits5);	
		printList(resultList5);
	}

}
