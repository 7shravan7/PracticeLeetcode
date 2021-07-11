 package dp.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* **Medium**    322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount
 *  representing a total amount of money.

	Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be 
	made up by any combination of the coins, return -1.

	You may assume that you have an infinite number of each kind of coin.
	
	Example 1:
		Input: coins = [1,2,5], amount = 11
		Output: 3
		Explanation: 11 = 5 + 5 + 1
		
	Example 2:
		Input: coins = [2], amount = 3
		Output: -1
		
	Example 3:
		Input: coins = [1], amount = 0
		Output: 0
 * 
 */
public class CoinChange {
	
	/*
	 * if coins={1,2,5}
	 * coinChange(amount) = 1 + Math.min(coinChange(amount-1),coinChange(amount-2),coinChange(amount-5))
	 * 
	 * Time Complexity : O(amount^no.of.coins)
	 * Space Complexity: O(amount)
	 */
	public int coinChangeBruteForce(int[] coins, int amount) {
		if(amount<0) {
			return -1;
		}
		if(amount == 0) {
			return 0;
		}
		int coinChange = Integer.MAX_VALUE;
		for(int coin:coins) {
			int coinCount = coinChangeBruteForce(coins, amount-coin);
			if(coinCount!=-1) {
				coinChange = Math.min(coinChange, coinCount+1);
			}
		}
		return (coinChange == Integer.MAX_VALUE) ? -1 : coinChange;
	}
	
	/*
	 * Time Complexity : O(amount*no.of.coins)
	 * Space Complexity: O(amount)
	 */
	public int coinChangeDpTopDown(int[] coins, int amount) {
		int[] dp =new int[amount+1];
		Arrays.fill(dp, -1);
		dp[0]=0;
		int minCoin = coinChangeDpTopDown(coins, amount, dp);
		return minCoin;
	}
	
	private int coinChangeDpTopDown(int[] coins, int amount, int[] dp) {
		if(dp[amount]!=-1) {
			return dp[amount];
		}
		int coinChange = Integer.MAX_VALUE;
		for(int coin:coins) {
			if(amount-coin>=0) {
				int coinCount = coinChangeDpTopDown(coins, amount-coin, dp);
				if(coinCount!=-1) {
					coinChange = Math.min(coinChange, coinCount+1);
				}
			}
		}
		dp[amount]  = (coinChange == Integer.MAX_VALUE) ? -1 : coinChange;
		return dp[amount];
	}
	
	/*
	 * Time Complexity : O(amount*no.of.coins)
	 * Space Complexity: O(amount)
	 */
	public int coinChangeBottomTop(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		Arrays.fill(dp, amount+1); // max no of change would be amount+1
		dp[0] = 0;
		for(int i=1;i<amount+1;i++) {
			for(int coin:coins) {
				if(i-coin>=0) {
					dp[i] = Math.min(dp[i], dp[i-coin]+1);
				}
			}
		}
		return dp[amount] == amount+1 ? -1: dp[amount];
	}

	public static void main(String[] args) {
		CoinChange coinChange = new CoinChange();
		int[] coins = {2,5,10,1};
		int amount = 27;
		System.out.println("minimum no of coins Brute Force "+coinChange.coinChangeBruteForce(coins, amount));
		System.out.println("minimum no of coins Dp Top Down "+coinChange.coinChangeDpTopDown(coins, amount));
		System.out.println("minimum no of coins Dp Bottom Top  "+coinChange.coinChangeBottomTop(coins, amount));
	}

}
