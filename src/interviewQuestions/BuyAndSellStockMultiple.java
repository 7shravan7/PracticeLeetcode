package interviewQuestions;

/* **Medium**   Stock Buy Sell to Maximize Profit : GeeksForGeeks
    The cost of a stock on each day is given in an array, find the max profit that you can make by buying and
    selling in those days.
    For example, if the given array is {100, 180, 260, 310, 40, 535, 695},
    the maximum profit can earned by buying on day 0, selling on day 3. Again buy on day 4 and sell on day 6.
    If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.
 */
public class BuyAndSellStockMultiple {

    /*   Find local minimum and local maximum and sum it to get maxProfit
         Time Complexity : O(n)
         Space Complexity: O(1)
     */
    public int maxProfit(int[] stockArr) {
        int n = stockArr.length;
        int maxProfit = 0;
        int i=0;
        int buyIndex = 0;
        while(i<n-1){
            while(i<n-1 && stockArr[i]>=stockArr[i+1]){  // find local minimum (should be less than next element)
                i++;
            }
            buyIndex = i;
            i++;
            while(i<n && stockArr[i]>=stockArr[i-1]){ // find local maximum (should be greater than previous element)
                i++;
            }
            if(buyIndex == i-1){
                System.out.println("No solution");
                break;
            } else {
                maxProfit += stockArr[i-1] - stockArr[buyIndex];
                System.out.println("Buy "+ stockArr[buyIndex]+" : Sell "+ stockArr[i-1]);
            }
        }
        return maxProfit;
    }

    /*
    public int maxProfit(int[] stockArr) {
        int n = stockArr.length;
        int maxProfit = 0;
        int i=0;
        while(i<n-1){
            while(i<n-1 && stockArr[i]>=stockArr[i+1]){
                i++;
            }
            System.out.println("buy "+ i+" : "+stockArr[i]);
            i++;
            while(i<n && stockArr[i]>=stockArr[i-1]){
                i++;
            }
            System.out.println("sell "+(i-1)+" : "+stockArr[i-1]);
        }
        return maxProfit;
    }
     */

    public static void main(String[] args) {
        BuyAndSellStockMultiple stock = new BuyAndSellStockMultiple();
        int[] stockArr1 = {100,180,260,310,40,535,695};
        System.out.println("Max profit of stockArr1 : "+ stock.maxProfit(stockArr1));
        int[] stockArr2 = {100,20};
        System.out.println("Max profit of stockArr2 : "+ stock.maxProfit(stockArr2));
    }
}
