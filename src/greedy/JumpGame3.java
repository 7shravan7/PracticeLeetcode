package greedy;

/* **MEDIUM**   1306. Jump Game III
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 *  When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with 
 *  value 0.
 *  Notice that you can not jump outside the array at any time.
 *  
 *  Example 1:
		Input: arr = [4,2,3,0,3,1,2], start = 5
		Output: true
		Explanation: 
			All possible ways to reach at index 3 with value 0 are: 
			index 5 -> index 4 -> index 1 -> index 3 
			index 5 -> index 6 -> index 4 -> index 1 -> index 3
			
	Example 2:
		Input: arr = [4,2,3,0,3,1,2], start = 0
		Output: true 
		Explanation: 
			One possible way to reach at index 3 with value 0 is: 
			index 0 -> index 4 -> index 1 -> index 3
			
	Example 3:
		Input: arr = [3,0,2,1,2], start = 2
		Output: false
		Explanation: There is no way to reach at index 1 with value 0.

	Constraints:
		1 <= arr.length <= 5 * 104
		0 <= arr[i] < arr.length
		0 <= start < arr.length
 */
public class JumpGame3 {
	
	class Point {
		int val;
		int startRange;
		int endRange;
		public Point(int val, int startRange, int endRange) {
			this.val = val;
			this.startRange = startRange;
			this.endRange = endRange;
		}
	}

	public boolean canReach(int[] arr, int start) {
		Point[] rangeArr = new Point[arr.length];
		for(int i=0;i<arr.length;i++) {
			rangeArr[i] = new Point(arr[i], i-arr[i], i+arr[i]);
		}
		boolean[] visited = new boolean[arr.length]; // to avoid cycle
		int[] canReach = new int[arr.length]; // to avoid re-calculating for same
		return dfs(rangeArr, start, visited, canReach);
	}
	
	private boolean dfs(Point[] rangeArr, int currIndex, boolean[] visited, int[] canReach) {
		Point currPoint = rangeArr[currIndex];
		if(currPoint.val == 0) {
			return true;
		}
		if(visited[currIndex]) {
			return false;
		}
		if(canReach[currIndex]!=0) {
			return canReach[currIndex]==1;
		}
		visited[currIndex] = true;
		boolean canStartReach = currPoint.startRange>=0 ? dfs(rangeArr, currPoint.startRange, visited, canReach) : false;
		boolean canEndReach = canStartReach || (currPoint.endRange<rangeArr.length ? dfs(rangeArr, currPoint.endRange, visited, canReach) : false);
		visited[currIndex] = false;
		canReach[currIndex] = canStartReach || canEndReach ? 1 :-1;
		return canStartReach || canEndReach;
	}
	
	public boolean canReachLeetCodeSoln(int[] arr, int start) {
		System.out.println(start);
        if(start >= 0 && start < arr.length && arr[start] >= 0) {            
            if(arr[start] == 0)
                return true;

            arr[start] = -arr[start];
            return canReachLeetCodeSoln(arr, start + arr[start]) || canReachLeetCodeSoln(arr, start - arr[start]);
        }
        return false;
    }

	public static void main(String[] args) {
		JumpGame3 jumpGame3 = new JumpGame3();
		int[] arr = {4,2,3,0,3,1,2};
		int start = 5;
		System.out.println("Can reach 0 Value Index : "+jumpGame3.canReach(arr, start));
		System.out.println("Can reach 0 Value Index (Leetcode Soln): "+jumpGame3.canReachLeetCodeSoln(arr, start));
	}

}
