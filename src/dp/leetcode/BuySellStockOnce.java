package dp.leetcode;

/*
 * Time Complexity - O(n)
 * Space Complexity - O(1)
 */
public class BuySellStockOnce {

	public int maxProfit(int[] prices) {
		if(prices.length==0) {
			return 0;
		}
		int maxProfit=0;
		int leftMin=prices[0];
		for(int i=1;i<prices.length;i++){
			if(leftMin>prices[i]){
				leftMin  =prices[i];
			}else if(maxProfit<prices[i]-leftMin){
				maxProfit = prices[i]-leftMin;
			}

		}
		return maxProfit;
	}

	public static void main(String[] args) {
		BuySellStockOnce stock = new BuySellStockOnce();
		int[] prices= {7,1,5,3,6,4};
		System.out.println("Max profit : "+stock.maxProfit(prices));
	}

}
