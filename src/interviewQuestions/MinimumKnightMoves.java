package interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* **MEDIUM**   1197. Minimum Knight Moves

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].
It is guaranteed the answer exists.

Example 1:
    Input: x = 2, y = 1
    Output: 1
    Explanation: [0, 0] → [2, 1]

Example 2:
    Input: x = 5, y = 5
    Output: 4
    Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
    -300 <= x, y <= 300
    0 <= |x| + |y| <= 300

 */
public class MinimumKnightMoves {

    /*
        00 01 02 03 04
        10 11 12 13 14
        20 21 22 23 24
        30 31 32 33 34
        40 41 42 43 44
     */

    int[] rowMoves = { -2, -1, 1, 2,  2,  1, -1, -2};
    int[] colMoves = {  1,  2, 2, 1, -1, -2, -2, -1};

    public int minKnightMoves(int x, int y) {
        int minKnightMoves = 0;
        Queue<String> startQueue = new LinkedList<>();
        startQueue.add("0;0");
        Queue<String> endQueue = new LinkedList<>();
        endQueue.add(""+x+";"+y);
        while(!startQueue.isEmpty()){
            int size = startQueue.size();
            for(int ii=0;ii<size;ii++) {
                String point = startQueue.poll();
                if(endQueue.contains(point)){
                    return minKnightMoves;
                }
                String[] arr = point.split(";");
                int pointX = Integer.valueOf(arr[0]);
                int pointY = Integer.valueOf(arr[1]);
                for(int i=0;i<=7;i++){
                    startQueue.add(""+(pointX+rowMoves[i])+";"+(pointY+colMoves[i]));
                }
            }
            Queue<String> temp = startQueue;
            startQueue = endQueue;
            endQueue=temp;
            minKnightMoves++;
        }
        return minKnightMoves;
    }

    public static void main(String[] args) {
        MinimumKnightMoves minKnightMoves = new MinimumKnightMoves();
        int x1=2;
        int y1=1;
        System.out.println("Min Knight moves for ("+x1+","+y1+")"+minKnightMoves.minKnightMoves(x1,y1));
        int x2=5;
        int y2=5;
        System.out.println("Min Knight moves for ("+x2+","+y2+")"+minKnightMoves.minKnightMoves(x2,y2));
    }

    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
