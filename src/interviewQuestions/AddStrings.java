package interviewQuestions;

/* **Easy**   415. Add Strings
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

   You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
   You must also not convert the inputs to integers directly.

   Example 1:
		Input: num1 = "11", num2 = "123"
		Output: "134"
		
  Example 2:
		Input: num1 = "456", num2 = "77"
		Output: "533"
		
  Example 3:
		Input: num1 = "0", num2 = "0"
		Output: "0"

  Constraints:
	1 <= num1.length, num2.length <= 104
	num1 and num2 consist of only digits.
	num1 and num2 don't have any leading zeros except for the zero itself.
 */
public class AddStrings {

	public String addStrings(String num1, String num2) {
		int num1Len = num1.length()-1;
		int num2Len = num2.length()-1;
		if(num1Len<num2Len)	{
			return addStrings(num2, num1);
		}
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		while(num1Len>=0 && num2Len>=0) {
			Integer num1Int = num1.charAt(num1Len) - '0';
			Integer num2Int = num2.charAt(num2Len) - '0';
			int sum = num1Int + num2Int + carry;
			carry = sum>9 ? 1 : 0;
			sb.append(sum%10);
			num1Len--;
			num2Len--;
		}
		while(num1Len>=0) {
			int sum = num1.charAt(num1Len) - '0' + carry;
			carry = sum>9 ? 1 : 0;
			sb.append(sum%10);
			num1Len--;
		}
		if(carry>0) {
			sb.append(carry);
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		AddStrings addStrings = new AddStrings();
		System.out.println(addStrings.addStrings("11", "123"));
		System.out.println(addStrings.addStrings("456", "77"));
	}

}
