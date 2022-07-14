package restart;

import java.util.ArrayList;
import java.util.Stack;

public class KosaRajuSCC {

    public void stronglyConnectedGraphs(ArrayList<ArrayList<Integer>> adj, int n) {
        Stack<Integer> topoStack = topoSort(adj, n);
        ArrayList<ArrayList<Integer>> transpAdj = new ArrayList<>();
        for(int i=0;i<n;i++){
            transpAdj.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            for(int ver : adj.get(i)){
                transpAdj.get(ver).add(i);
            }
        }
        boolean[] visited = new boolean[n];
        while (!topoStack.isEmpty()){
            int src = topoStack.pop();
            if(!visited[src]){
                Stack<Integer> stk = new Stack<>();
                dfsRev(src, transpAdj, stk, visited);
                while (!stk.isEmpty()){
                    System.out.print(stk.pop()+",");
                }
                System.out.println();
            }
        }
    }

    private Stack<Integer> topoSort(ArrayList<ArrayList<Integer>> adj, int n) {
        Stack<Integer> topoStack = new Stack<>();
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(i, adj, topoStack, visited);
            }
        }
        return topoStack;
    }

    private void dfs(int src, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack, boolean[] visited){
        visited[src] = true;
        adj.get(src).forEach(ver->{
            if(!visited[ver]){
                dfs(ver, adj, stack, visited);
            }
        });
        stack.push(src);
    }

    private void dfsRev(int src, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack, boolean[] visited){
        visited[src] = true;
        stack.push(src);
        adj.get(src).forEach(ver->{
            if(!visited[ver]){
                dfsRev(ver, adj, stack, visited);
            }
        });
    }

    public static void main(String args[])
    {
        int n = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer> >();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());

        /*adj.get(0).add(2);
        adj.get(1).add(0);
        adj.get(2).add(1);
        adj.get(2).add(4);
        adj.get(3).add(5);
        adj.get(4).add(3);
        adj.get(5).add(4);*/

        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(3).add(2);
        adj.get(3).add(5);
        adj.get(4).add(6);
        adj.get(5).add(4);
        adj.get(6).add(5);


        KosaRajuSCC obj = new KosaRajuSCC();
        obj.stronglyConnectedGraphs(adj, n);

    }

}
