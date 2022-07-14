package restart;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        int steps = 0;
        int endMask = (1<<n)-1;
        boolean[][] seen = new boolean[n][endMask];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            int mask = 1<<i;
            queue.add(new int[] {i, mask});
            seen[i][mask] = true;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] nodeArr = queue.poll();
                int node = nodeArr[0];
                int nodeMask = nodeArr[1];
                System.out.println("(node,mask)::("+node+","+nodeMask+")");
                for(int neighbour:graph[node]){
                    int nextMask = nodeMask | (1<<neighbour);
                    if(endMask == nextMask){
                        System.out.println("answer-"+node+","+nodeMask+"::"+neighbour);
                        return steps + 1;
                    }
                    if(!seen[neighbour][nextMask]){
                        seen[neighbour][nextMask] = true;
                        queue.add(new int[]{neighbour, nextMask});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathVisitingAllNodes shortestPathVisitingAllNodes = new ShortestPathVisitingAllNodes();
        int[][] graph = {{1,2,3},{0},{0},{0}};
        System.out.println(shortestPathVisitingAllNodes.shortestPathLength(graph));
        int[][] graph1 = {{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(shortestPathVisitingAllNodes.shortestPathLength(graph1));
    }
}
