package interviewQuestions;

/* **Hard**   829. Consecutive Numbers Sum
 * 
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
	Example 1:
		Input: 5
		Output: 2
		Explanation: 5 = 5 = 2 + 3
		
	Example 2:
		Input: 9
		Output: 3
		Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
		
	Example 3:
		Input: 15
		Output: 4
		Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
		
	Note: 1 <= N <= 10 ^ 9.
 */
public class ConsecutiveNumbersSum {

	/* Brute Force Method
	 * Time limit exceeeded for N=63660706
	 */
	public int consecutiveNumbersSum1(int N) {
		int noOfWays = 1;
		int half = N/2;
		int sum=0;
		int start= 0;
		for(int i=half+1;i>0;i--) {
			sum=i;
			start = i;
			for(int j=i-1;j>0;j--) {
				sum += j;
				if(sum == N) {
					noOfWays++;
					System.out.println("start:"+start+" end:"+j);
					break;
				}
				if(sum>N || sum+(j-1)>N) {
					break;
				}
			}
		}
		return noOfWays;
	}
	
	/*
	 * x+(x+1)+(x+2) ...(x+(i-1)) = N     (1+2+3+4 = 10 => 1+(1+1)+(1+2)+(1+3) = 10)
	 * x(i) + (1+2+3+...+(i-1)) = N     (1*4 + (1+2+3) = 10)
	 * x(i) + ((i-1)*(i))/2 = N       (1*4 + (3*4)/2 = 10)
	 * x(i) = N - ((i-1)*(i))/2       (1*4 = 10 - (3*4)/2)
	 * so, LHS is positive in all cases since x>0 and i>0 , In RHS,
	 *  N > ((i-1)*(i))/2 so that it is not zero
	 *  ==> 2N > (i-1)*(i) // for loop condition
	 *  (N - ((i-1)*(i))/2) % i == 0 (LHS is multiple of (i), RHS will be multiple iff it satisfies above condition)
	 */
	public int consecutiveNumbersSum(int N) {
		int noOfWays = 0;
		for(int i=1;(i-1)*i<2*N;i++) {
			System.out.println(N-(((i-1)*i)/2));
			if((N - ((i-1)*i)/2) % i == 0) {
				System.out.println(N - ((i-1)*i)/2+":i="+i);
				noOfWays++;
			}
		}
		return noOfWays;
	}

	public static void main(String[] args) {
		ConsecutiveNumbersSum consecutiveNumbersSum = new ConsecutiveNumbersSum();
		int N1 = 5;
		System.out.println("ConsecutiveNumbersSum ways of "+ N1+" : "+consecutiveNumbersSum.consecutiveNumbersSum(N1));
		int N2 = 9;
		System.out.println("ConsecutiveNumbersSum ways of "+ N2+" : "+consecutiveNumbersSum.consecutiveNumbersSum(N2));
		int N3 = 15;
		System.out.println("ConsecutiveNumbersSum ways of "+ N3+" : "+consecutiveNumbersSum.consecutiveNumbersSum(N3));
	}

}
