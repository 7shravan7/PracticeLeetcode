package interviewQuestions;

import java.util.Stack;

/* **Medium**  1209. Remove All Adjacent Duplicates in String II
 * 
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters 
 * from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

   We repeatedly make k duplicate removals on s until we no longer can.

   Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

	Example 1:
		Input: s = "abcd", k = 2
		Output: "abcd"
		Explanation: There's nothing to delete.
		
	Example 2:
		Input: s = "deeedbbcccbdaa", k = 3
		Output: "aa"
		Explanation: 
			First delete "eee" and "ccc", get "ddbbbdaa"
			Then delete "bbb", get "dddaa"
			Finally delete "ddd", get "aa"
			
	Example 3:
		Input: s = "pbbcggttciiippooaais", k = 2
		Output: "ps"
		
	Constraints:
		1 <= s.length <= 105
		2 <= k <= 104
		s only contains lower case English letters.
 */
public class RemoveAllDuplicatesStringII {
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(n) // charArr & stack to store count
	 */
	public String removeDuplicates(String s, int k) {
		int strLen = s.length();
		char[] charArr = s.toCharArray();
		Stack<Integer> countStack = new Stack<>();
		int index=0;
		for(int i=0;i<strLen;i++) {
			char ch = charArr[i];
			charArr[index] = ch;
			if(index==0 || ch != charArr[index-1]) {
				countStack.push(1);
			} else {
				int newCount = countStack.pop()+1;
				if(newCount==k) {
					index = index-k;
				} else {
					countStack.push(newCount);
				}
			}
			index++;
		}
		return new String(charArr, 0 ,index);
	}

	public static void main(String[] args) {
		RemoveAllDuplicatesStringII removeDuplicates = new RemoveAllDuplicatesStringII();
		String s1="abcd";
		int k1=2;
		System.out.println(s1+" after remove duplicates with k "+k1+" : "+removeDuplicates.removeDuplicates(s1, k1));
		String s2="deeedbbcccbdaa";
		int k2=3;
		System.out.println(s2+" after remove duplicates with k "+k2+" : "+removeDuplicates.removeDuplicates(s2, k2));
		String s3="pbbcggttciiippooaais";
		int k3=2;
		System.out.println(s3+" after remove duplicates with k "+k3+" : "+removeDuplicates.removeDuplicates(s3, k3));
	}

}
