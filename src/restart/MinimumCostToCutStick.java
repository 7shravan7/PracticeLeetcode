package restart;

import java.util.Arrays;

public class MinimumCostToCutStick {

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);// sort array
        int[] newCuts = new int[cuts.length+2];
        newCuts[0] = 0;
        newCuts[newCuts.length-1] = n;
        for(int i=1;i<cuts.length+1;i++){
            newCuts[i] = cuts[i-1];
        }
        int[][] dp = new int[newCuts.length-1][newCuts.length-1];
        int c = minCost(newCuts, 1, newCuts.length-2,dp);
        for(int i=0;i<newCuts.length-1;i++){
            for(int j=0;j<newCuts.length-1;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return c;
    }

    private int minCost(int[] cuts, int start, int end, int[][] dp){
        System.out.println("minCost("+(start)+","+(end)+")");
        if(start > end){
            return 0;
        }
        if(dp[start][end]>0){
            return dp[start][end];
        }
        int min = Integer.MAX_VALUE;
        for(int k=start;k<=end;k++){
            int minCost = minCost(cuts, start, k-1, dp)+ minCost(cuts, k+1, end, dp);
            min = Math.min(min, minCost);
        }
        int cost = min + cuts[end+1]-cuts[start-1];
        dp[start][end] = cost;
        return cost;
    }

    public static void main(String[] args) {
        MinimumCostToCutStick minimumCostToCutStick = new MinimumCostToCutStick();
        int[] cuts = {1,3,4,5};
        int n=7;
        System.out.println(minimumCostToCutStick.minCost(n, cuts));
    }

}
