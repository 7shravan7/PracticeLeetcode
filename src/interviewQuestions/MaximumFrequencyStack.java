package interviewQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* **Hard**   895. Maximum Frequency Stack
 * 
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from 
 * the stack.

	Implement the FreqStack class:
		FreqStack() 
			constructs an empty frequency stack.
		void push(int val) 
			pushes an integer val onto the top of the stack.
		int pop() 
			removes and returns the most frequent element in the stack.
			If there is a tie for the most frequent element, the element closest to the stack's top 
			is removed and returned.
 
	Example 1:
		Input
			["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
			[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
		Output
			[null, null, null, null, null, null, null, 5, 7, 5, 4]
		Explanation
			FreqStack freqStack = new FreqStack();
			freqStack.push(5); // The stack is [5]
			freqStack.push(7); // The stack is [5,7]
			freqStack.push(5); // The stack is [5,7,5]
			freqStack.push(7); // The stack is [5,7,5,7]
			freqStack.push(4); // The stack is [5,7,5,7,4]
			freqStack.push(5); // The stack is [5,7,5,7,4,5]
			freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
			freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. 
									The stack becomes [5,7,5,4].
			freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
			freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. 
									The stack becomes [5,7].
	
	Constraints:
		0 <= val <= 109
		At most 2 * 104 calls will be made to push and pop.
		It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public class MaximumFrequencyStack {
	
	Map<Integer, Integer> frequencyMap;
	
	Map<Integer, Stack<Integer>> groupMap;
	
	int maxFrequency;

	public MaximumFrequencyStack() {
		frequencyMap = new HashMap<>();
		groupMap = new HashMap<>();
		maxFrequency = 0;
	}

	public void push(int val) {
		if(!frequencyMap.containsKey(val)) {
			frequencyMap.put(val, 0);
		}
		int newCount = frequencyMap.get(val) + 1;
		frequencyMap.put(val, newCount);
		if(!groupMap.containsKey(newCount)) {
			groupMap.put(newCount, new Stack<>());
		}
		groupMap.get(newCount).add(val);
		if(newCount>maxFrequency) {
			maxFrequency = newCount;
		}
	}

	public int pop() {
		int val = groupMap.get(maxFrequency).pop();
		frequencyMap.put(val, frequencyMap.get(val)-1);
		if(groupMap.get(maxFrequency).size()==0) {
			maxFrequency--;
		}
		return val;
	}

	public static void main(String[] args) {
		MaximumFrequencyStack maxFreqStack = new MaximumFrequencyStack();
		maxFreqStack.push(5);
		maxFreqStack.push(7);
		maxFreqStack.push(5);
		maxFreqStack.push(7);
		maxFreqStack.push(4);
		maxFreqStack.push(5);
		System.out.println(maxFreqStack.pop());
		System.out.println(maxFreqStack.pop());
		System.out.println(maxFreqStack.pop());
		System.out.println(maxFreqStack.pop());
	}

}
