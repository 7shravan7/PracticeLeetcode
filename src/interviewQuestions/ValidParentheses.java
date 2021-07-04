package interviewQuestions;

import java.util.Stack;

/* **Easy**    20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 *  valid.
	An input string is valid if:
		Open brackets must be closed by the same type of brackets.
		Open brackets must be closed in the correct order.
		
	Example 1:
		Input: s = "()"
		Output: true
		
	Example 2:
		Input: s = "()[]{}"
		Output: true
		
	Example 3:
		Input: s = "(]"
		Output: false
		
	Example 4:
		Input: s = "([)]"
		Output: false
		
	Example 5:
		Input: s = "{[]}"
		Output: true

	Constraints:
		1 <= s.length <= 104
		s consists of parentheses only '()[]{}'
 */
public class ValidParentheses {

	public boolean isValid1(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<s.length();i++) {
			Character ch = s.charAt(i);
			if(ch=='(' || ch=='[' || ch=='{') {
				stack.push(ch);
			} else {
				if(stack.isEmpty()) {
					return false;
				} else {
					Character topCh = stack.pop();
					if((ch == ')' && topCh != '(') || (ch == ']' && topCh != '[') ||
							(ch == '}' && topCh != '{')) {
						return false;
					}
				}
			}
		}
		return stack.isEmpty();
	}
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<s.length();i++) {
			Character ch = s.charAt(i);
			if(ch=='(') {
				stack.push(')');
			} else if(ch=='[') {
				stack.push(']');
			} else if(ch=='{') {
				stack.push('}');
			} else if (stack.isEmpty() || stack.pop()!=ch) {
				return false;
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		ValidParentheses validParanthesis = new ValidParentheses();
		String str1="()";
		System.out.println(str1 +" is valid : "+validParanthesis.isValid(str1));
		String str2="()[]{}";
		System.out.println(str2 +" is valid : "+validParanthesis.isValid(str2));
		String str3="(]";
		System.out.println(str3 +" is valid : "+validParanthesis.isValid(str3));
		String str4="([)]";
		System.out.println(str4 +" is valid : "+validParanthesis.isValid(str4));
		String str5="{[]}";
		System.out.println(str5 +" is valid : "+validParanthesis.isValid(str5));
	}

}
