package restart;

import java.util.ArrayList;
import java.util.List;

public class GraphColoring {

    //Function to determine if graph can be coloured with at most M colours such
    //that no two adjacent vertices of graph are coloured with same colour.
    public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int m)
    {
        int len = G.length;
        return solve(i, G, color, m, len);
    }

    private static boolean solve(int node, List<Integer>[] G, int[] color, int m, int n){
        if(node == n){
            return true;
        }
        for(int i=1;i<=m;i++){
            if(isValidColor(node, G, color, i)){
                color[node] = i;
                boolean next = solve(node+1, G, color, m, n);
                if(next){
                    return true;
                }
                color[node] = 0;
            }
        }
        return false;
    }

    private static boolean isValidColor(int node, List<Integer>[] G, int[] color, int c){
        for(int node1:G[node]){
            if(color[node1]==c){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 4, M = 3;
        List < Integer > [] G = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            G[i] = new ArrayList <> ();
        }
        G[0].add(1);
        G[1].add(0);
        G[1].add(2);
        G[2].add(1);
        G[2].add(3);
        G[3].add(2);
        G[3].add(0);
        G[0].add(3);
        G[0].add(2);
        G[2].add(0);
        int[] color = new int[N];
        boolean ans = graphColoring(G, color, 0, M);
        if (ans == true)
            System.out.println("1");
        else
            System.out.println("0");
    }
}
