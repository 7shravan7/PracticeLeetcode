package codes.LeetCode.may2020Challenges.week_4_22to28;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
	
	public static int[] getIntersection(int[] A, int[] B) {
		if(A[1]<B[0] || A[0]>B[1]) {
			return null;
		}
		int[] res = new int[2];
		res[0] = Math.max(A[0], B[0]);
		res[1] = Math.min(A[1], B[1]);
		return res;
	}

	public static int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> list = new ArrayList<>();
		int i=0;
		int j=0;
		while(i<A.length && j<B.length) {
			int[] intersection = getIntersection(A[i],B[j]);
			if(intersection!=null) {
				list.add(intersection);
			}
			if(A[i][1]<B[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		int[][] res = new int[list.size()][2];
		for(int ii=0;ii<list.size();ii++){
			res[ii]=list.get(ii);
		}
		return res;
	}
	
	public static void printArray(int[][] res) {
		int len = res.length;
		for(int i=0;i<len;i++) {
			System.out.println("["+res[i][0]+","+res[i][1]+"]");
		}
	}

	public static void main(String[] args) {
		int[][] A = {{0,2},{5,10},{13,23},{24,25}};
		int[][] B = {{1,5},{8,12},{15,24},{25,26}};
		int[][] res = IntervalListIntersections.intervalIntersection(A, B);
		printArray(res);
	}

}
