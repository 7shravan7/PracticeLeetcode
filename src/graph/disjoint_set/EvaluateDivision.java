package graph.disjoint_set;

import com.sun.tools.javac.util.Pair;

import java.util.*;

/* **MEDIUM**     399. Evaluate Division

You are given an array of variable pairs equations and an array of real numbers values, where
equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find
the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by
zero and that there is no contradiction.

Example 1:
    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],
                                                                             ["a","a"],["x","x"]]
    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    Explanation:
        Given: a / b = 2.0, b / c = 3.0
        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
        return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
                            queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
    Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
    Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
    1 <= equations.length <= 20
    equations[i].length == 2
    1 <= Ai.length, Bi.length <= 5
    values.length == equations.length
    0.0 < values[i] <= 20.0
    1 <= queries.length <= 20
    queries[i].length == 2
    1 <= Cj.length, Dj.length <= 5
    Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {

    class Value {
        String str;
        double val;
        public Value(String str,double val){
            this.str = str;
            this.val = val;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        HashMap<String, Value> gidWeight = new HashMap<>();

        // Step 1). build the union groups
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            union(gidWeight, dividend, divisor, quotient);
        }

        // Step 2). run the evaluation, with "lazy" updates in find() function
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
                // case 1). at least one variable did not appear before
                results[i] = -1.0;
            else {
                Value dividendEntry = find(gidWeight, dividend);
                Value divisorEntry = find(gidWeight, divisor);

                String dividendGid = dividendEntry.str;
                String divisorGid = divisorEntry.str;
                Double dividendWeight = dividendEntry.val;
                Double divisorWeight = divisorEntry.val;

                if (!dividendGid.equals(divisorGid))
                    // case 2). the variables do not belong to the same chain/group
                    results[i] = -1.0;
                else
                    // case 3). there is a chain/path between the variables
                    results[i] = dividendWeight / divisorWeight;
            }
        }

        return results;
    }

    private Value find(HashMap<String, Value> gidWeight, String nodeId) {
        if (!gidWeight.containsKey(nodeId))
            gidWeight.put(nodeId, new Value(nodeId, 1.0));

        Value entry = gidWeight.get(nodeId);
        // found inconsistency, trigger chain update
        if (!entry.str.equals(nodeId)) {
            Value newEntry = find(gidWeight, entry.str);
            gidWeight.put(nodeId, new Value(
                    newEntry.str, entry.val * newEntry.val));
        }

        return gidWeight.get(nodeId);
    }

    private void union(HashMap<String, Value> gidWeight, String dividend, String divisor, Double value) {
        Value dividendEntry = find(gidWeight, dividend);
        Value divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.str;
        String divisorGid = divisorEntry.str;
        Double dividendWeight = dividendEntry.val;
        Double divisorWeight = divisorEntry.val;

        // merge the two groups together,
        // by attaching the dividend group to the one of divisor
        if (!dividendGid.equals(divisorGid)) {
            gidWeight.put(dividendGid, new Value(divisorGid,
                    divisorWeight * value / dividendWeight));
        }
    }

    public static void printResult(double[] resultArr) {
        System.out.println("-----");
        for(double res : resultArr){
            System.out.print(res+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EvaluateDivision evalDivision = new EvaluateDivision();
        List<List<String>> equations1 = new ArrayList<>();
        equations1.add(Arrays.asList("a","b"));
        equations1.add(Arrays.asList("b","c"));
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList("c","a"));
        queries1.add(Arrays.asList("b","a"));
        queries1.add(Arrays.asList("a","e"));
        queries1.add(Arrays.asList("a","a"));
        queries1.add(Arrays.asList("x","x"));
        printResult(evalDivision.calcEquation(equations1, values1, queries1));
    }
}
