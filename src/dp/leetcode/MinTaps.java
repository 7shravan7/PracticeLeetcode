package dp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* **Hard**    1326. Minimum Number of Taps to Open to Water a Garden
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n.
 *  (i.e The length of the garden is n).

	There are n + 1 taps located at points [0, 1, ..., n] in the garden.

	Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the 
	i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

	Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be 
	watered return -1.
 * 
 */
public class MinTaps {

	public int minTaps(int n, int[] ranges) {
		List<Tap> tapList = new ArrayList<>();
//		Tap[] tapArr = new Tap[ranges.length];
		for(int i=0;i<ranges.length;i++) {
//			tapArr[i] = new Tap(i-ranges[i], i+ranges[i], i);
			tapList.add(new Tap(i-ranges[i], i+ranges[i], i));
		}
//		tapList.sort((a,b)->a.startRange-b.startRange);
		tapList.sort((a,b)->a.endRange-b.endRange);
		System.out.println("("+tapList.get(ranges.length-1).startRange+","+tapList.get(ranges.length-1).endRange+")");
		int[] dp = new int[ranges.length];
		dp[ranges.length-1] =1;
		for(int i=ranges.length-2;i>=0;i--) {
			dp[i] = Integer.MAX_VALUE;
			Tap tap = tapList.get(i);
			System.out.println("("+tap.startRange+","+tap.endRange+")");
			if(tap.endRange>=ranges.length) {
				dp[i] = 1;
			} else if(i!=tap.endRange){
				dp[i] = Math.min(dp[i], dp[tap.endRange]+1);
			}
		}
		if(dp[0] == Integer.MAX_VALUE) {
			return -1;
		}
 		return dp[0];
	}
	
	private int videoStitching(int T, int[][] clips) {
        int res = 0;
        Arrays.sort(clips, (a,b) ->  a[0] - b[0]);
        for (int i = 0, st = 0, end = 0; st < T; st = end, ++res) {
            for (; i < clips.length && clips[i][0] <= st; ++i)
                end = Math.max(end, clips[i][1]);
            if (st == end) return -1;
        }
        return res;
    }
	
	public int videoStitching(int T, int[] ranges) {
		int[][] tapArr = new int[ranges.length][2];
		for(int i=0;i<ranges.length;i++) {
			tapArr[i][0] = i-ranges[i];
			tapArr[i][1] = i+ranges[i];
		}
		return videoStitching(T, tapArr);
	}

	public static void main(String[] args) {
		MinTaps minTaps = new MinTaps();
		int n=7;
		int[] ranges = {1,2,1,0,2,1,0,1};
		System.out.println("Min Taps to open : "+minTaps.videoStitching(n, ranges));
	}
	
	class Tap {
		int startRange;
		int endRange;
		int index;
		Tap(int startRange, int endRange, int index){
			this.startRange = startRange;
			this.endRange = endRange;
			this.index = index;
		}
	}

}
