package interviewQuestions;

/* **Easy**   7. Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the 
 * signed 32-bit integer range [-2^(31), 2^(31 - 1)], then return 0.

   Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

	Example 1:
		Input: x = 123
		Output: 321
		
	Example 2:
		Input: x = -123
		Output: -321
		
	Example 3:
		Input: x = 120
		Output: 21
		
	Example 4:
		Input: x = 0
		Output: 0
 
	Constraints:
		-2^31 <= x <= 2^31 - 1
 */
public class ReverseInteger {

	public int reverse(int x) {
		int rev = 0;
		while(x!=0) {
			int rem = x%10;
			x = x/10;
			if(rev>Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && rem>7)) {
				return 0;
			}
			if(rev<Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && rem<-8)) {
				return 0;
			}
			rev = rev*10 + rem;
		}
		return rev;
	}

	public static void main(String[] args) {
		ReverseInteger reverseInteger = new ReverseInteger();
		int num1 = 123;
		System.out.println("Reverse of "+num1+" : "+reverseInteger.reverse(num1));
		int num2 = -123;
		System.out.println("Reverse of "+num2+" : "+reverseInteger.reverse(num2));
		int num3 = 120;
		System.out.println("Reverse of "+num3+" : "+reverseInteger.reverse(num3));
	}

}
