package interviewQuestions;

import java.util.Stack;

/* **Medium**   227. Basic Calculator II
 * 
 * Given a string s which represents an expression, evaluate this expression and return its value. 

   The integer division should truncate toward zero.

    Note: You are not allowed to use any built-in function which evaluates strings as 
           mathematical expressions, such as eval().

	Example 1:
		Input: s = "3+2*2"
		Output: 7
		
	Example 2:
		Input: s = " 3/2 "
		Output: 1
		
	Example 3:
		Input: s = " 3+5 / 2 "
		Output: 5

	Constraints:
		1 <= s.length <= 3 * 105
		s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
		s represents a valid expression.
		All the integers in the expression are non-negative integers in the range [0, 231 - 1].
		The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculatorII {
	
	/*  27ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	public int calculateWithStack(String s) {
		int num=0;
		int result=0;
		char operation ='+';
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) { // check if ch is in '0'-'9'
				num = num*10 + ch-'0';
			}
			if(i==s.length()-1 || !Character.isDigit(ch) && ch!=' ') { // if not digit or to include end of string
				if(operation == '+') {
					stack.push(num);
				} else if (operation == '-') {
					stack.push(-num);
				} else if(operation == '*') {
					stack.push(stack.pop()*num);
				} else if(operation == '/') {
					stack.push(stack.pop()/num);
				}
				num=0;
				operation=ch;
			}
		}
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		return result;
	}
	
	/* 6ms
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int calculate(String s) {
		int result=0;
		int currNum=0;
		int lastNum=0;
		int operation = '+'; //default operation
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if(Character.isDigit(ch)) {
				currNum = currNum*10 + ch-'0';
			}
			if(i==s.length()-1 || ch!=' '&& !Character.isDigit(ch)) {
				switch(operation) {
					case '+':
						result += lastNum;
						lastNum = currNum;
						break;
					case '-':
						result += lastNum;
						lastNum = -currNum;
						break;
					case '*':
						lastNum *= currNum;
						break;
					case '/':
						lastNum /= currNum;
						break;
				}
				operation = ch;
				currNum = 0;
			}
		}
		return result+lastNum;
	}

	public static void main(String[] args) {
		BasicCalculatorII basicCalculator = new BasicCalculatorII();
		String s1="3+2*2-5/2+1";
		System.out.println(s1+" : "+basicCalculator.calculate(s1));
		String s2=" 3/2 ";
		System.out.println(s2+" : "+basicCalculator.calculate(s2));
		String s3=" 3+5 / 2 ";
		System.out.println(s3+" : "+basicCalculator.calculate(s3));
		String s4="0-2147483647";
		System.out.println(s4+" : "+basicCalculator.calculate(s4));
		String s5="1-1+1";
		System.out.println(s5+" : "+basicCalculator.calculate(s5));
		String s6="2*3*4";
		System.out.println(s6+" : "+basicCalculator.calculate(s6));
	}

}
