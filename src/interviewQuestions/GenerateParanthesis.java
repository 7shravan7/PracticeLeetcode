package interviewQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* **Medium**   22. Generate Parentheses
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 
	Example 1:
		Input: n = 3
		Output: ["((()))","(()())","(())()","()(())","()()()"]
		
	Example 2:
		Input: n = 1
		Output: ["()"]
			
	Constraints:
		1 <= n <= 8
 */
public class GenerateParanthesis {
	
	// Time : 22ms
	// Time & Space Complexity : O(4^n/sqrt(n))
	public List<String> generateParenthesisBFS(int n) {
		List<String> resultList = new ArrayList<>();
		Set<String> resultSet = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add("()");
		if(n==1) {
			resultList.addAll(queue);
			return resultList;
		}
		for(int i=2;i<=n;i++) {
			int queueSize = queue.size();
			while(queueSize>0) {
				String currStr = queue.poll();
				queue.add("()"+currStr);
				for(int j=0;j<currStr.length();j++) {
					if(currStr.charAt(j)=='(') {
						queue.add(currStr.substring(0, j+1)+"()"+currStr.substring(j+1));
					}
				}
				queueSize--;
			}
		}
		resultSet.addAll(queue);
		return  new ArrayList<>(resultSet);
	}
	
	/*
	 * Time : 0ms
	 * Time & Space Complexity : O(4^n/sqrt(n))
	 * 
	 * Why it is more efficient from prev method is that it wont have duplicates of combinations since we are 
	 * adding left and right parenthesis at each index of string and we never repeat index so each string is
	 * guaranteed to be unique 
	 */
	public List<String> generateParenthesisBacktrack(int n) {
		List<String> parentheisisList = new ArrayList<>();
		char[] charArray = new char[2*n]; // since pairs of brackets needs to be present
		generateParanthesis(parentheisisList, n, n, charArray, 0);
		return parentheisisList;
	}
	
	private void generateParanthesis(List<String> parentheisisList, int leftParenCount, int rightParenCount,
			char[] charArray, int index) {
		if(leftParenCount<0 || leftParenCount>rightParenCount) {
			return;
		}
		if(leftParenCount==0 && rightParenCount==0) {
			parentheisisList.add(new String(charArray));
			return;
		}
		if(leftParenCount>0) {
			charArray[index] = '(';
			generateParanthesis(parentheisisList, leftParenCount-1, rightParenCount, charArray, index+1);
		}
		if(rightParenCount>leftParenCount) {
			charArray[index] = ')';
			generateParanthesis(parentheisisList, leftParenCount, rightParenCount-1, charArray, index+1);
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
		GenerateParanthesis gp = new GenerateParanthesis();
		int n1 = 3;
		List<String> resultList1 = gp.generateParenthesisBacktrack(n1);
		printList(resultList1);
		int n2 = 1;
		List<String> resultList2 = gp.generateParenthesisBacktrack(n2);
		printList(resultList2);
		int n3 = 8;
		List<String> resultList3 = gp.generateParenthesisBacktrack(n3);
		printList(resultList3);
	}

}
