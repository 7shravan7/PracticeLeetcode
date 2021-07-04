package interviewQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* **Medium**      990. Satisfiability of Equality Equations
 * 
 * Given an array equations of strings that represent relationships between variables, each string 
 * equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  
 * Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

   Return true if and only if it is possible to assign integers to variable names so as to satisfy all the 
   given equations.

	Example 1:
		Input: ["a==b","b!=a"]
		Output: false
		Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, 
					 but not the second.  There is no way to assign the variables to satisfy both equations.
					 
	Example 2:
		Input: ["b==a","a==b"]
		Output: true
		Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
		
	Example 3:
		Input: ["a==b","b==c","a==c"]
		Output: true
		
	Example 4:
		Input: ["a==b","b!=c","c==a"]
		Output: false
		
	Example 5:
		Input: ["c==c","b==d","x!=z"]
		Output: true

	Note:
		1 <= equations.length <= 500
		equations[i].length == 4
		equations[i][0] and equations[i][3] are lowercase letters
		equations[i][1] is either '=' or '!'
		equations[i][2] is '='
 */
public class SatisfiabliltyOfEqualityEquations {
	
	class DisjointSet {
		
		int[] parents;
		
		public DisjointSet() {
			parents = new int[26];
			for(int i=0;i<26;i++) {
				parents[i] = i;
			}
		}
		
		public int find(int val) {
			if(parents[val] == val) {
				return val;
			}
			return find(parents[val]);
		}
		
		public void union(int val1, int val2) {
			int group1 = find(val1);
			int group2 = find(val2);
			if(group1!=group2) {
				parents[group1] = group2;
			}
		}
		
	}
	
	public boolean equationsPossibleWithUnionFind(String[] equations) {
		DisjointSet disjointSet = new DisjointSet();
		for(String equation:equations) {
			if('=' == equation.charAt(1)) {
				int val1 = equation.charAt(0)-'a';
				int val2 = equation.charAt(3)-'a';
				disjointSet.union(val1, val2);
			}
		}
		for(String equation:equations) {
			if('!' == equation.charAt(1)) {
				int val1 = equation.charAt(0)-'a';
				int val2 = equation.charAt(3)-'a';
				int group1 = disjointSet.find(val1);
				int group2 = disjointSet.find(val2);
				if(group1==group2) {
					return false;
				}
			}
		}
		return true;
	}
	

	public boolean equationsPossible(String[] equations) {
		Map<Character, Set<Character>> adjacencyMap = new HashMap<>();
		for(String equation : equations) {
			if('='== equation.charAt(1)) {
				char c1 = equation.charAt(0);
				char c2 = equation.charAt(3);
				if(adjacencyMap.containsKey(c1)) {
					adjacencyMap.get(c1).add(c2);
				} else {
					adjacencyMap.put(c1, new HashSet<>(Arrays.asList(c2)));
				}
				if(adjacencyMap.containsKey(c2)) {
					adjacencyMap.get(c2).add(c1);
				} else {
					adjacencyMap.put(c2, new HashSet<>(Arrays.asList(c1)));
				}
			}
		}
		for(String equation : equations) {
			if('!'== equation.charAt(1)) {
				char c1 = equation.charAt(0);
				char c2 = equation.charAt(3);
				if(c1 == c2) {
					return false;
				}
				boolean result = dfs(c1, c2, adjacencyMap);
				if(!result) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean dfs(Character c1, Character c2, Map<Character, Set<Character>> adjacencyMap) {
		Set<Character> visitedSet = new HashSet<>();
		Stack<Character> stack = new Stack<>();
		stack.push(c1);
		visitedSet.add(c1);
		while(!stack.isEmpty()) {
			char ch = stack.pop();
			Set<Character> equalSet = adjacencyMap.get(ch);
			if(equalSet!=null && !equalSet.isEmpty()) {
				for(char c: equalSet) {
					if(c == c2) {
						return false;
					} else if (!visitedSet.contains(c)) {
						stack.push(c);
						visitedSet.add(c);
					}
				}
			}
		}
		return true;
	}
	
	public boolean equationsPossibleGraphColoring(String[] equations) {
		Set<Integer>[] graph = new HashSet[26];
		for(int i=0;i<26;i++) {
			graph[i] = new HashSet<>();
		}
		for(String equation : equations) {
			if('='== equation.charAt(1)) {
				int index1 = equation.charAt(0)-'a';
				int index2 = equation.charAt(3) -'a';
				graph[index1].add(index2);
				graph[index2].add(index1);
			}
		}
		int[] color = new int[26];
		int colorVal = 0;
		for(int i=0;i<26;i++) {
			if(color[i]==0) {
				colorVal++;
				Stack<Integer> stack = new Stack<>();
				stack.push(i);
				while(!stack.isEmpty()) {
					int node = stack.pop();
					Set<Integer> valuesSet = graph[node];
					for(int val:valuesSet) {
						if(color[val]==0) {
							color[val] = colorVal;
							stack.push(val);
						}
					}
				}
			}
		}
		for(String equation : equations) {
			if('!'== equation.charAt(1)) {
				int c1Index = equation.charAt(0)-'a';
				int c2Index = equation.charAt(3)-'a';
				if(c1Index==c2Index || (color[c1Index]!=0 && color[c1Index]==color[c2Index])) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		SatisfiabliltyOfEqualityEquations se = new SatisfiabliltyOfEqualityEquations();
		String[] equations1 = {"a==b","b!=a"};
		System.out.println("Equations1 valid : "+se.equationsPossible(equations1));
		System.out.println("Equations1 valid GC : "+se.equationsPossibleGraphColoring(equations1));
		System.out.println("Equations1 valid Union Find : "+se.equationsPossibleWithUnionFind(equations1));
		String[] equations2 = {"b==a","a==b"};
		System.out.println("Equations2 valid : "+se.equationsPossible(equations2));
		System.out.println("Equations2 valid GC : "+se.equationsPossibleGraphColoring(equations2));
		System.out.println("Equations2 valid Union Find : "+se.equationsPossibleWithUnionFind(equations2));
		String[] equations3 = {"a==b","b==c","a==c"};
		System.out.println("Equations3 valid : "+se.equationsPossible(equations3));
		System.out.println("Equations3 valid GC : "+se.equationsPossibleGraphColoring(equations3));
		System.out.println("Equations3 valid Union Find : "+se.equationsPossibleWithUnionFind(equations3));
		String[] equations4 = {"a==b","b!=c","c==a"};
		System.out.println("Equations4 valid : "+se.equationsPossible(equations4));
		System.out.println("Equations4 valid GC : "+se.equationsPossibleGraphColoring(equations4));
		System.out.println("Equations4 valid Union Find : "+se.equationsPossibleWithUnionFind(equations4));
		String[] equations5 = {"c==c","b==d","x!=z"};
		System.out.println("Equations5 valid : "+se.equationsPossible(equations5));
		System.out.println("Equations5 valid GC : "+se.equationsPossibleGraphColoring(equations5));
		System.out.println("Equations5 valid Union Find : "+se.equationsPossibleWithUnionFind(equations5));
		String[] equations6 = {"a!=a"};
		System.out.println("Equations6 valid : "+se.equationsPossible(equations6));
		System.out.println("Equations6 valid GC : "+se.equationsPossibleGraphColoring(equations6));
		System.out.println("Equations6 valid Union Find : "+se.equationsPossibleWithUnionFind(equations6));
		String[] equations7 = {"c==c","f!=a","f==b","b==c"};
		System.out.println("Equations7 valid : "+se.equationsPossible(equations7));
		System.out.println("Equations7 valid GC : "+se.equationsPossibleGraphColoring(equations7));
		System.out.println("Equations7 valid Union Find : "+se.equationsPossibleWithUnionFind(equations7));
	}

}
