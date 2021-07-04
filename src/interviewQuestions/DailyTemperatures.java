package interviewQuestions;

import java.util.ArrayDeque;
import java.util.Deque;

/* **Medium**   739.Daily Temperatures
 * 
 * Given a list of daily temperatures temperatures, return a list such that, for each day in the input, 
 * tells you how many days you would have to wait until a warmer temperature. 
 * If there is no future day for which this is possible, put 0 instead.

   For example, given the list of temperatures temperatures = [73, 74, 75, 71, 69, 72, 76, 73], 
   your output should be [1, 1, 4, 2, 1, 1, 0, 0].

    Note: 	The length of temperatures will be in the range [1, 30000]. 
    		Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
	
	/*
	 * Brute Force method
	 * Time Complexity : O(n^2)
	 */
	public int[] dailyTemperaturesBruteForce(int[] temperatures) {
		int[] result = new int[temperatures.length];
		for(int i=0;i<temperatures.length;i++) {
			for(int j=i+1;j<temperatures.length;j++) {
				if(temperatures[i] < temperatures[j]) {
					result[i] = j-i;
					break;
				}
			}
		}
		return result;
	}
	
	public int[] dailyTemperaturesStack(int[] temperatures) {
		int[] result = new int[temperatures.length];
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i=0;i<temperatures.length;i++) {
			while(!stack.isEmpty() && temperatures[stack.peek()]<temperatures[i]) {
				int index = stack.pop();
				result[index] = i-index;
			}
			stack.push(i);
		}
		return result;
	}
	
	public int[] dailyTemperaturesArray(int[] temperatures) {
	    int[] stack = new int[temperatures.length];
	    int top = -1;
	    int[] ret = new int[temperatures.length];
	    for(int i = 0; i < temperatures.length; i++) {
	        while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
	            int idx = stack[top--];
	            ret[idx] = i - idx;
	        }
	        stack[++top] = i;
	    }
	    return ret;
	}
	
	public static void printResult(int[] resultList) {
		System.out.println();
		for(int i=0;i<resultList.length;i++) {
			System.out.print(resultList[i]);
			if(i!=resultList.length-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		DailyTemperatures dailyTemp = new DailyTemperatures();
		int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
		printResult(dailyTemp.dailyTemperaturesBruteForce(temperatures1));
		printResult(dailyTemp.dailyTemperaturesStack(temperatures1));
		printResult(dailyTemp.dailyTemperaturesArray(temperatures1));
	}

}
