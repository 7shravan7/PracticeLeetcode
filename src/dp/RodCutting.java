package dp;

public class RodCutting {
	
	
	private static int rodCutting(int[] profit, int val) {
		int[] dp = new int[val+1];
		int[] sol = new int[val+1];
		dp[0] = 0;
		sol[0] = 0;
		for(int i=1;i<=val;i++) {
			int maxVal = -1;
			for(int j=1;j<=i;j++) {
				if(maxVal < profit[j]+dp[i-j]) {
					maxVal = profit[j]+dp[i-j];
					sol[i] = j;
				}
			}
			dp[i] = maxVal;
		}
		for(int i=0;i<=val;i++) {
			System.out.print(sol[i]+",");
		}
		System.out.println("--");
		System.out.println(sol[val]);
		return dp[val];
	}

	public static void main(String[] args) {
		//int[] len = {0,1,2,3,4,5,6,7,8,9,10};
		int[] profit = {0,1,5,8,9,10,17,17,20,24,30};
		System.out.println(rodCutting(profit, 10));
	}

}
