package restart;

public class MinCoinChange {

    public static int findMinimumCoins(int amount){
        // Write your code here.
        int[] coins = {1000,500,100,50,20,10,5,2,1};
        int minCoins = 0;
        int i=0;
        while(i<9){
            while(i<9 && amount<coins[i]){
                i++;
            }
            minCoins += amount/coins[i];
            System.out.println("Take "+amount/coins[i]+" times of "+coins[i]);
            amount = amount%coins[i];
            if(amount == 0){
                break;
            }
            i++;
        }
        return minCoins;
    }

    public static void main(String[] args) {
        int amount1= 70;
        System.out.println("Min coins for "+amount1+":"+findMinimumCoins(amount1));
        int amount2= 1768;
        System.out.println("Min coins for "+amount2+":"+findMinimumCoins(amount2));
    }
}
