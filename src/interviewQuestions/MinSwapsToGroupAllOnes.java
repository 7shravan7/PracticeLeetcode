package interviewQuestions;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/* **Medium**      1151. Minimum Swaps to Group All 1's Together
 * 
 * Given a binary array data, return the minimum number of swaps required to group all
 * 1s present in the array together in any place in the array.

	Example 1:
		Input: data = [1,0,1,0,1]
		Output: 1
		Explanation: 
			There are 3 ways to group all 1's together:
			[1,1,1,0,0] using 1 swap.
			[0,1,1,1,0] using 2 swaps.
			[0,0,1,1,1] using 1 swap.
			The minimum is 1.
			
	Example 2:
		Input: data = [0,0,0,1,0]
		Output: 0
		Explanation: 
			Since there is only one 1 in the array, no swaps needed.
			
	Example 3:
		Input: data = [1,0,1,0,1,0,0,1,1,0,1]
		Output: 3
		Explanation: 
			One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
			
	Example 4:
		Input: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
		Output: 8

	Constraints:
		1 <= data.length <= 105
		data[i] is 0 or 1.
 */
public class MinSwapsToGroupAllOnes {

	/*
	 * Time Complexity :O(n)
	 * Space Complexity:O(n)
	 */
	public int minSwaps(int[] data) {
		int n = data.length;
		int numOfOnes = 0;
		for(int i=0;i<n;i++) {
			if(data[i]==1) {
				numOfOnes++;
			}
		}
		if(numOfOnes<=1) {
			return 0;
		}
		int windowSize = numOfOnes;
		int minSwaps = n - numOfOnes;
		Deque<Integer> deque = new LinkedList<>();
		int zerosCount = 0;
		int count=0;
		for(int i=0;i<n;i++) {  // or i<=n-k
			if(count==windowSize) {
				int num = deque.poll();
				if(num==0) {
					zerosCount--;
				}
				count--;
			}
			deque.offerLast(data[i]);
			if(data[i]==0) {
				zerosCount++;
			}
			count++;
			if(i>=windowSize-1) {
				minSwaps = Math.min(minSwaps, zerosCount);
			}
		}
		return minSwaps;
	}
	
	/*
	 * Time Complexity :O(n)
	 * Space Complexity:O(1)
	 */
	public int minSwapsWithSpaceOptimized(int[] data) {
		int ones = Arrays.stream(data).sum();
        int cnt_one = 0, max_one = 0;
        int left = 0, right = 0;

        while (right < data.length) {
            // updating the number of 1's by adding the new element
            cnt_one += data[right++];
            // maintain the length of the window to ones
            if (right - left > ones) {
                // updating the number of 1's by removing the oldest element
                cnt_one -= data[left++];
            }
            // record the maximum number of 1's in the window
            max_one = Math.max(max_one, cnt_one);
        }
        return ones - max_one;
	}

	public static void main(String[] args) {
		MinSwapsToGroupAllOnes minSwaps = new MinSwapsToGroupAllOnes();
		int[] data1 = {1,0,1,0,1};
		System.out.println("Min Swaps for data1 : "+minSwaps.minSwaps(data1));
		System.out.println("Min Swaps for data1 : "+minSwaps.minSwapsWithSpaceOptimized(data1));
		int[] data2 = {0,0,0,1,0};
		System.out.println("Min Swaps for data2 : "+minSwaps.minSwaps(data2));
		int[] data3 = {1,0,1,0,1,0,0,1,1,0,1};
		System.out.println("Min Swaps for data3 : "+minSwaps.minSwaps(data3));
		int[] data4 = {1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1};
		System.out.println("Min Swaps for data4 : "+minSwaps.minSwaps(data4));
	}

}
