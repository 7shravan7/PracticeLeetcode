package binary_search_approach;

/*  ** HARD **      668.Kth Smallest Number in Multiplication Table
 Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is
 an integer matrix mat where mat[i][j] == i * j (1-indexed).
 Given three integers m, n, and k, return the kth smallest element in the m x n multiplication
 table.

Example 1:

    1 2 3
    2 4 6
    3 6 9

  []-> 1,2,2,3,[3],4,6,6,9
 idx-> 1,2,3,4,[5],6,7,8,9

Input: m = 3, n = 3, k = 5
Output: 3
Explanation: The 5th smallest number is 3.
Example 2:


Input: m = 2, n = 3, k = 6
Output: 6
Explanation: The 6th smallest number is 6.


Constraints:

1 <= m, n <= 3 * 104
1 <= k <= m * n

 */
public class KthSmallestNumberInMultiplicationTable {

    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!enough(mi, m, n, k)) lo = mi + 1;
            else hi = mi;
        }
        return lo;
    }


    public static void main(String[] args) {
        KthSmallestNumberInMultiplicationTable kSmallNum = new KthSmallestNumberInMultiplicationTable();
        System.out.println(kSmallNum.findKthNumber(3,3,5));
    }
}
