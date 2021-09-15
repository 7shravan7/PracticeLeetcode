package graph.disjoint_set;

/* **Medium**    547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with
city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:

    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    Output: 2
Example 2:

    Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
    Output: 3

Constraints:
    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] is 1 or 0.
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {

    class DisjointSet {

        int[] root;
        int[] rank;
        int count;

        public DisjointSet(int n) {
            root = new int[n];
            rank = new int[n];
            count = n;
            for(int i=0;i<n;i++){
                root[i] = i;
                rank[i] = i;
            }
        }

        public int find(int val){
            if(root[val] == val){
                return val;
            }
            return root[val] = find(root[val]);
        }

        public void union(int val1, int val2){
            int parent1 = find(val1);
            int parent2 = find(val2);
            if(parent1 != parent2){
                if(rank[parent1]>rank[parent2]){
                    root[parent2] = parent1;
                } else if (rank[parent1]<rank[parent2]){
                    root[parent1] = parent2;
                } else {
                    root[parent2] = parent1;
                    rank[parent1] += 1;
                }
                count--;
            }
        }

        public boolean isConnected(int val1, int val2) {
            return find(val1) == find(val2);
        }

        public int getCount() {
            return count;
        }

    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet disjointSet = new DisjointSet(n);
        for(int i=0;i<n;i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    disjointSet.union(i, j);
                }
            }
        }
        return disjointSet.getCount();
    }

    public static void main(String[] args) {
        NumberOfProvinces provinces = new NumberOfProvinces();
        int[][] connectedCities1 = {{1,1,0},
                                    {1,1,0},
                                    {0,0,1}};
        System.out.println("No of provinces in connectedCities1 : "+provinces.findCircleNum(connectedCities1));
        int[][] connectedCities2 = {{1,0,0},
                                    {0,1,0},
                                    {0,0,1}};
        System.out.println("No of provinces in connectedCities2 : "+provinces.findCircleNum(connectedCities2));
    }
}
