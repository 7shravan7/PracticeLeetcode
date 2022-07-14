package grabInterview2022;

import java.util.HashSet;
import java.util.Set;

public class Task2 {

    // you can also use imports, for example:

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

        int longestPathNodesCount;

        public int solution(Tree T) {
            // write your code in Java SE 8
            longestPathNodesCount = 0;
            Set<Integer> visitedSet = new HashSet<>();
            preOrderTraversal(T, visitedSet);
            return longestPathNodesCount;
        }

        private void preOrderTraversal(Tree node, Set<Integer> visitedSet){
            if(node == null || visitedSet.contains(node.x)){
                return;
            }
            visitedSet.add(node.x);
            preOrderTraversal(node.l, visitedSet);
            preOrderTraversal(node.r, visitedSet);
            if(visitedSet.size()>longestPathNodesCount){
                longestPathNodesCount = visitedSet.size();
            }
            visitedSet.remove(node.x);
        }

}

class Tree {
    int x;
    Tree l;
    Tree r;
}
