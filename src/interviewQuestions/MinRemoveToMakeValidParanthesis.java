package interviewQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* **Medium**   1249. Minimum Remove to Make Valid Parentheses
 * 
 * Given a string s of '(' , ')' and lowercase English characters. 
   Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting 
   parentheses string is valid and return any valid string.

   Formally, a parentheses string is valid if and only if:

   It is the empty string, contains only lowercase characters, or
   It can be written as AB (A concatenated with B), where A and B are valid strings, or
   It can be written as (A), where A is a valid string.

	Example 1:
		Input: s = "lee(t(c)o)de)"
		Output: "lee(t(c)o)de"
		Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
		
	Example 2:
		Input: s = "a)b(c)d"
		Output: "ab(c)d"
		
	Example 3:
		Input: s = "))(("
		Output: ""
		Explanation: An empty string is also valid.
		
	Example 4:
		Input: s = "(a(b(c)d)"
		Output: "a(b(c)d)"
		
	Constraints:
		1 <= s.length <= 10^5
		s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinRemoveToMakeValidParanthesis {

	public String minRemoveToMakeValid(String s) {
		Stack<Integer> stack = new Stack<>();
		List<Integer> removeIndexList = new ArrayList<>();
		for(int i=0;i<s.length();i++) {
			Character ch = s.charAt(i);
			if(ch == '(') {
				stack.push(i);
			} else if (ch == ')')  {
				if(stack.isEmpty()) {
					removeIndexList.add(i);
				} else {
					stack.pop();
				}
			}
		}
		while(!stack.isEmpty()) {
			removeIndexList.add(stack.pop());
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(!removeIndexList.contains(i)) {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MinRemoveToMakeValidParanthesis validParanthesis = new MinRemoveToMakeValidParanthesis();
		String s1 = "lee(t(c)o)de)";
		System.out.println(s1+" : "+validParanthesis.minRemoveToMakeValid(s1));
	}

}
