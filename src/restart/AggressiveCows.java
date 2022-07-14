package restart;

import java.util.Arrays;

/*
    Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls.
    The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).

    His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put
    into a stall.
    To prevent the cows from hurting each other, FJ wants to assign the cows to the stalls, such that the
    minimum distance between any two of them is as large as possible.

    What is the largest minimum distance?

    Input
        t – the number of test cases, then t test cases follows.
        * Line 1: Two space-separated integers: N and C
        * Lines 2..N+1: Line i+1 contains an integer stall location, xi

    Output
        For each test case output one integer: the largest minimum distance.

Example

Input:

1
5 3
1
2
8
4
9
Output:

3
Output details:

FJ can put his 3 cows in the stalls at positions 1, 4 and 8,
resulting in a minimum distance of 3.
 */
public class AggressiveCows {

    // 1 2 4 8 9
    //  1 2 4 1
    public int getLargestMinimumDistance(int[] stalls, int noOfStalls, int noOfCows) {
        Arrays.sort(stalls);
        int low = 1;
        int high = stalls[noOfStalls-1] - stalls[0];
        int largestMinDist = Integer.MIN_VALUE;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isPossible(stalls, mid, noOfCows)){
                largestMinDist = Math.max(largestMinDist, mid);
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return largestMinDist;
    }

    private boolean isPossible(int[] stalls, int distance, int noOfCows){
        int cow = 1;
        int lastPlacedCow = stalls[0];
        for(int i=1;i<stalls.length;i++){
            if(stalls[i]-lastPlacedCow>=distance){
                cow++;
                lastPlacedCow = stalls[i];
                if(cow == noOfCows){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AggressiveCows aggressiveCows = new AggressiveCows();
        int[] stalls = {1,2,8,4,9};
        int noOfStalls = 5;
        int noOfCows = 3;
        System.out.println(aggressiveCows.getLargestMinimumDistance(stalls, noOfStalls, noOfCows));
    }
}
