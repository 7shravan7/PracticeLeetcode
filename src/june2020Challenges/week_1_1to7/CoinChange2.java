package june2020Challenges.week_1_1to7;

import java.util.HashSet;
import java.util.Set;

public class CoinChange2 {

	public int change(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				dp[i] += dp[i-coin];
			}
		}
		return dp[amount]; 
	}

	public static void main(String[] args) {
		CoinChange2 cc = new  CoinChange2();
		int amount= 5;
		int[] coins = {1,2,5};
		System.out.println(cc.change(amount, coins));
	}

}
