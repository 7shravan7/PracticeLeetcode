package dp.cci_book;
/*
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * **** Here we are generalizing the solution given n and step array as parameters ***(Daily coding questions)
 */
public class ClimbingStairs {

	/*
	 * this problem resembles fibonacci series when we write recursive function
	 * f(n) = f(n-1) + f(n-2) + ...(based on the step array values)
	 * if stepArray is {1,3,5} and n is 5 ,then equation is 
	 * f(n)=f(n-1)+f(n-3)+f(n-5)
	 * f(5)=f(5-1)+f(5-3)+f(5-5) ----> f(5)=f(4)+f(2)+f(0)
	 * f(4)=f(4-1)+f(4-3)+f(4-5) ----> f(4)=f(3)+f(1)+f(-1) ----> f(4)=f(3)+f(1) (ignoring non negative func)
	 * f(3)=f(3-1)+f(3-3)+f(3-5) ----> f(3)=f(2)+f(0)
	 * f(2)=f(2-1)+f(2-3)+f(2-5) ----> f(2)=f(1)
	 * f(1)=f(1-1)+f(1-3)+f(1-5) ----> f(1)=f(0)
	 * f(0) = 1 (since if they are in level 0 and n=0 there is only one way which is base case here)
	 */
	public int climbStairs(int n, int[] stepArray) {
		if(n==0 || n==1) {
			return 1;
		}
		int[] resultArr = new int[n+1];
		resultArr[0] = 1;
		for(int i=1;i<=n;i++) {
			int ans = 0;
			for(int j=0; j<stepArray.length;j++) {
				// here ignoring non negative steps
				if(i-stepArray[j]>=0) {
					ans += resultArr[i-stepArray[j]];
				}
			}
			resultArr[i] = ans;
		}
		return resultArr[n];
	}

	public static void main(String[] args) {
		ClimbingStairs cs = new ClimbingStairs();
		int[] stepArray = {1,3,5};
		int n = 5;
		int noOfWays = cs.climbStairs(n, stepArray);
		System.out.println("number of ways is "+noOfWays);
	}

}
