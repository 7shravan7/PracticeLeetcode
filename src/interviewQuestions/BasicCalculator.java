package interviewQuestions;

import java.util.Stack;

/* **Hard**   224. Basic Calculator
 * Given a string s representing an expression, implement a basic calculator to evaluate it.

	Example 1:
		Input: s = "1 + 1"
		Output: 2
		
	Example 2:
		Input: s = " 2-1 + 2 "
		Output: 3
		
	Example 3:
		Input: s = "(1+(4+5+2)-3)+(6+8)"
		Output: 23
		
	Constraints:
		1 <= s.length <= 3 * 105
		s consists of digits, '+', '-', '(', ')', and ' '.
		s represents a valid expression.
 */
public class BasicCalculator {

	public int calculate(String s) {
		if(s == null) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		int sign = 1; // 1 or -1
		int num=0;
		for(int i=0;i<s.length();i++) {
			Character ch = s.charAt(i);
			if(ch == ' ') {
				continue;
			} else if (ch >= '0' && ch<='9') {
				num = num*10 + ch-'0';
			} else if (ch == '+') {
				result += num * sign;
				num = 0;
				sign = 1;
			} else if (ch == '-') {
				result += num * sign;
				num = 0;
				sign = -1;
			} else if (ch == '(') {
				stack.push(result);
				stack.push(sign);
				result = 0;
				sign = 1;
			} else {
				result += sign * num;  
				num = 0;
				result *= stack.pop();
				result += stack.pop();
			}
		}
		if(num!=0) {
			result += num * sign;
		}
		return result;
	}

	public static void main(String[] args) {
		BasicCalculator calculator = new BasicCalculator();
		String expr1 = "1 + 1";
		System.out.println(expr1+" : "+calculator.calculate(expr1));
		String expr2 = " 2-1 + 2 ";
		System.out.println(expr2+" : "+calculator.calculate(expr2));
		String expr3 = "(1+(4+5+2)-3)+(6+8)";
		System.out.println(expr3+" : "+calculator.calculate(expr3));
		String expr4 = "-(4+5+2)";
		System.out.println(expr4+" : "+calculator.calculate(expr4));
	}

}
