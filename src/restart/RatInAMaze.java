package restart;

import java.util.ArrayList;

/*  **Medium**  $$ GeeksforGeeks Striver SDE Sheet Day 10  $$
Consider a rat placed at (0, 0) in a square matrix of order N * N.
It has to reach the destination at (N - 1, N - 1).
Find all possible paths that the rat can take to reach from source to destination.
The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right).
Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a
cell in the matrix represents that rat can be travel through it.
Note: In a path, no cell can be visited more than one time.

Example 1:
Input:
N = 4
m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1},
         {1, 1, 0, 0},
         {0, 1, 1, 1}}
Output:
DDRDRR DRDDRR
Explanation:
The rat can reach the destination at
(3, 3) from (0, 0) by two paths - DRDDRR
and DDRDRR, when printed in sorted order
we get DDRDRR DRDDRR.

Example 2:
Input:
N = 2
m[][] = {{1, 0},
         {1, 0}}
Output:
-1
Explanation:
No path exists and destination cell is
blocked.
Your Task:
You don't need to read input or print anything. Complete the function printPath() which takes N and 2D array m[ ][ ] as input parameters and returns the list of paths in lexicographically increasing order.
Note: In case of no path, return an empty list. The driver will output "-1" automatically.

Expected Time Complexity: O((3N^2)).
Expected Auxiliary Space: O(L * X), L = length of the path, X = number of paths.

Constraints:
2 ≤ N ≤ 5
0 ≤ m[i][j] ≤ 1
 */
public class RatInAMaze {

    static int[] rows = {1,0,0,-1};
    static int[] cols = {0,-1,1,0};
    static char[] dir = {'D','L','R','U'};

    public static ArrayList<String> findPath(int[][] m, int n) {
        // Your code here
        ArrayList<String> resultList = new ArrayList<>();
        if(m[0][0]==0 || m[n-1][n-1]==0){
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        dfs(0,0,m,n,resultList, sb);
        return resultList;
    }

    private static void dfs(int row, int col, int[][] m, int n, ArrayList<String> resultList, StringBuilder sb) {
        if(row == (n-1) && col == (n-1)){
            resultList.add(new String(sb.toString()));
            return;
        }
        m[row][col] = 0;
        for(int i=0;i<4;i++){
            int newRow = row+rows[i];
            int newCol = col+cols[i];
            if(isAllowedCell(newRow, newCol, m, n)){
                m[newRow][newCol] = 0;
                sb.append(dir[i]);
                dfs(newRow, newCol, m, n, resultList, sb);
                m[newRow][newCol] = 1;
                sb.deleteCharAt(sb.length()-1);
            }
        }
        m[row][col] = 1;
    }

    private static boolean isAllowedCell(int row, int col, int[][] m, int n) {
        return row>=0 && row<n && col>=0 && col<n && m[row][col]==1;
    }

    public static void print(ArrayList<String> resultList){
        for(String str:resultList){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        int n1 =4;
        int[][] m1 = {{1,0,0,0},
                      {1,1,0,1},
                      {1,1,0,0},
                      {0,1,1,1}};
        print(findPath(m1,n1));
        System.out.println("-------------");
        int n2=4;
        int[][] m2= {{1, 0, 1, 1},
                     {1, 1, 1, 1},
                     {1, 1, 0, 1},
                     {0, 1, 1, 1}};
        print(findPath(m2,n2));
    }
}
