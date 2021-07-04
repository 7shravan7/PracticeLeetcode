package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* **Easy**   937. Reorder Data in Log Files
 * 
 * You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.

   There are two types of logs:

   Letter-logs: All words (except the identifier) consist of lowercase English letters.
   Digit-logs: All words (except the identifier) consist of digits.
   
   Reorder these logs so that:
	The letter-logs come before all digit-logs.
	The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them 
		lexicographically by their identifiers.
	The digit-logs maintain their relative ordering.
	Return the final order of the logs.

	Example 1:
		Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
		Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
		Explanation:
			The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
			The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
			
	Example 2:
		Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
		Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

	Constraints:
		1 <= logs.length <= 100
		3 <= logs[i].length <= 100
		All the tokens of logs[i] are separated by a single space.
		logs[i] is guaranteed to have an identifier and at least one word after the identifier.
 */
public class ReorderDataInLogFiles {
	
	/*
	 * wirting custom comparator function
	 * Time : 5ms
	 * Time Complexity :  O(MNlogN) N - no of logs in list and M - max length of a log
	 */
	public String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, (a,b)->{
			String[] aSplit = a.split(" ",2);
			String[] bSplit = b.split(" ",2);
			boolean isALetterLog = aSplit[1].charAt(0)>='a'&& aSplit[1].charAt(0)<='z';
			boolean isBLetterLog = bSplit[1].charAt(0)>='a'&& bSplit[1].charAt(0)<='z';
			if(isALetterLog && isBLetterLog) {
				int comp = aSplit[1].compareTo(bSplit[1]);
				if(comp == 0) { // when contents are same we should compare by identifier
					return aSplit[0].compareTo(bSplit[0]);
				}
				return comp;
			} else if (isALetterLog && !isBLetterLog) {
				return -1; // if b is digit log, then b should come after a
			} else if (!isALetterLog && isBLetterLog) {
				return 1; // if a is digit log, then a should come after b
			} else {
				return 0; // both are digit logs
			}
		});
		return logs;
	}
	
	/*
	 * here spliting letter and digit logs and doing sort only for letter logs and then combining both
	 * Time : 4ms
	 */
	public String[] reorderLogFiles1(String[] logs) {
		List<String> letterLogs = new ArrayList<>();
		List<String> digitLogs = new ArrayList<>();
		for(String log:logs) {
			if(Character.isDigit(log.split(" ",2)[1].charAt(0))) {
				digitLogs.add(log);
			} else {
				letterLogs.add(log);
			}
		}
		Collections.sort(letterLogs, (a,b)->{
			String[] aSplit = a.split(" ",2);
			String[] bSplit = b.split(" ",2);
			int comp = aSplit[1].compareTo(bSplit[1]);
			if(comp == 0) { // when contents are same we should compare by identifier
				return aSplit[0].compareTo(bSplit[0]);
			}
			return comp;
		});
		int index=0;
		for(int i=0;i<letterLogs.size();i++) {
			logs[index++] = letterLogs.get(i);
		}
		for(int i=0;i<digitLogs.size();i++) {
			logs[index++] = digitLogs.get(i);
		}
		return logs;
	}
	
	public static void printResult(String[] logs) {
		System.out.println();
		for(int i=0;i<logs.length;i++) {
			System.out.print(logs[i]);
			if(i!=logs.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ReorderDataInLogFiles reOrder = new ReorderDataInLogFiles();
		String[] logs1 = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
		printResult(reOrder.reorderLogFiles1(logs1));
		String[] logs2 = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
		printResult(reOrder.reorderLogFiles1(logs2));
	}

}
