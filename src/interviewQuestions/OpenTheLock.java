package interviewQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* **Medium**   752. Open the Lock
 * 
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: 
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 *  The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 *  Each move consists of turning one wheel one slot.

    The lock initially starts at '0000', a string representing the state of the 4 wheels.

    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, 
 	wheels of the lock will stop turning and you will be unable to open it.

	Given a target representing the value of the wheels that will unlock the lock, return the minimum total 
	number of turns required to open the lock, or -1 if it is impossible.

	Example 1:
		Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
		Output: 6
		Explanation:
		A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
		Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
		because the wheels of the lock become stuck after the display becomes the dead end "0102".
		
	Example 2:
		Input: deadends = ["8888"], target = "0009"
		Output: 1
		Explanation:
			We can turn the last wheel in reverse to move from "0000" -> "0009".
			
	Example 3:
		Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
		Output: -1
		Explanation:
			We can't reach the target without getting stuck.
			
	Example 4:
		Input: deadends = ["0000"], target = "8888"
		Output: -1
		
	Constraints:
		1 <= deadends.length <= 500
		deadends[i].length == 4
		target.length == 4
		target will not be in the list deadends.
		target and deadends[i] consist of digits only.
 */
public class OpenTheLock {
	
	/*
	 * Time : 85ms
	 */
	public int openLock(String[] deadends, String target) {
		String start = "0000";
		if(start.equals(target)) {
			return 0;
		}
		Set<String> deadEndSet = new HashSet<>();
		for(String deadEnd:deadends) {
			if(start.equals(deadEnd)) {
				return -1; // if src is there in deadends[]
			}
			deadEndSet.add(deadEnd);
		}
		Set<String> visitedSet = new HashSet<>();
		Queue<String> startQueue = new LinkedList<>();
		startQueue.add(start);
		visitedSet.add(start);
		int noOfTurns = 0;
		while(!startQueue.isEmpty()) {
			int startQueueSize = startQueue.size();
			noOfTurns++;
			while(startQueueSize-->0) {
				String str = startQueue.poll();
				char[] charArr=str.toCharArray();
//				System.out.println(new String(charArr));
				for(int i=0;i<4;i++) {
					int num = charArr[i] -'0';
					int increNum =(num+1+10)%10;
					charArr[i] = (char)(increNum+'0');
					String newIncStr = new String(charArr);
					if(newIncStr.equals(target)) {
						return noOfTurns;
					}
					if(!deadEndSet.contains(newIncStr) && !visitedSet.contains(newIncStr)) {
						startQueue.add(newIncStr);
						visitedSet.add(newIncStr);
					}
					int decreNum =(num-1+10)%10;
					charArr[i] = (char)(decreNum+'0');
					String newDecStr = new String(charArr);
					if(newDecStr.equals(target)) {
						return noOfTurns;
					}
					if(!deadEndSet.contains(newDecStr) && !visitedSet.contains(newDecStr)) {
						startQueue.add(newDecStr);
						visitedSet.add(newDecStr);
					}
					charArr[i] =(char)(num+'0');
				}
			}
		}
		return -1;
	}
	
	/*
	 * Time : 42ms
	 */
	public int openLock2EndBfs(String[] deadends, String target) {
		String start = "0000";
		if(start.equals(target)) {
			return 0;
		}
		Set<String> deadEndSet = new HashSet<>();
		for(String deadEnd:deadends) {
			if(start.equals(deadEnd)) {
				return -1; // if src is there in deadends[]
			}
			deadEndSet.add(deadEnd);
		}
		Queue<String> startQueue = new LinkedList<>();
		Set<String> visitedStartSet = new HashSet<>();
		startQueue.add(start);
		visitedStartSet.add(start);
		Queue<String> targetQueue = new LinkedList<>();
		Set<String> visitedTargetSet = new HashSet<>();
		targetQueue.add(target);
		visitedTargetSet.add(target);
		int noOfTurns = 0;
		while(!startQueue.isEmpty() || !targetQueue.isEmpty()) {
			if(!startQueue.isEmpty()) {
				noOfTurns++;
				boolean fromStartFound = addToQueue(startQueue, visitedStartSet, deadEndSet, visitedTargetSet);
				if(fromStartFound) {
					return noOfTurns;
				}
			}
			if(!targetQueue.isEmpty()) {
				noOfTurns++;
				boolean fromTargetFound = addToQueue(targetQueue, visitedTargetSet, deadEndSet, visitedStartSet);
				if(fromTargetFound) {
					return noOfTurns;
				}
			}
		}
		return -1;
	}
	
	private boolean addToQueue(Queue<String> queue, Set<String> vistedSet, Set<String> deadEndSet,
			Set<String> otherVisitedSet) {
		int queueSize = queue.size();
		while(queueSize-->0) {
			String str = queue.poll();
			char[] charArr=str.toCharArray();
//			System.out.println(new String(charArr));
			for(int i=0;i<4;i++) {
				int num = charArr[i] -'0';
				int increNum =(num+1+10)%10;
				charArr[i] = (char)(increNum+'0');
				String newIncStr = new String(charArr);
				if(otherVisitedSet.contains(newIncStr)) {
					return true;
				}
				if(!deadEndSet.contains(newIncStr) && !vistedSet.contains(newIncStr)) {
					queue.add(newIncStr);
					vistedSet.add(newIncStr);
				}
				int decreNum =(num-1+10)%10;
				charArr[i] = (char)(decreNum+'0');
				String newDecStr = new String(charArr);
				if(otherVisitedSet.contains(newDecStr)) {
					return true;
				}
				if(!deadEndSet.contains(newDecStr) && !vistedSet.contains(newDecStr)) {
					queue.add(newDecStr);
					vistedSet.add(newDecStr);
				}
				charArr[i] =(char)(num+'0');
			}
		}
		return false;
	}
	
	/*
	 * Time : 32ms
	 */
	public int openLock2EndBfsWithOptimization(String[] deadends, String target) {
		String start = "0000";
		if(start.equals(target)) {
			return 0;
		}
		Set<String> deadEndSet = new HashSet<>();
		for(String deadEnd:deadends) {
			if(start.equals(deadEnd)) {
				return -1; // if src is there in deadends[]
			}
			deadEndSet.add(deadEnd);
		}
		Queue<String> startQueue = new LinkedList<>();
		Set<String> visitedStartSet = new HashSet<>();
		startQueue.add(start);
		visitedStartSet.add(start);
		Queue<String> targetQueue = new LinkedList<>();
		Set<String> visitedTargetSet = new HashSet<>();
		targetQueue.add(target);
		visitedTargetSet.add(target);
		int noOfTurns = 0;
		Queue<String> tempQueue = null;
		Set<String> tempVisitedSet = null;
		while(!startQueue.isEmpty() && !targetQueue.isEmpty()) {
			if(startQueue.size()>targetQueue.size()) {
				tempQueue = startQueue;
				startQueue = targetQueue;
				targetQueue = tempQueue;
				tempVisitedSet = visitedStartSet;
				visitedStartSet = visitedTargetSet;
				visitedTargetSet = tempVisitedSet;
			}
			noOfTurns++;
			boolean isFound = addToQueue(startQueue, visitedStartSet, deadEndSet, visitedTargetSet);
			if(isFound) {
				return noOfTurns;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		OpenTheLock openLock = new OpenTheLock();
		String[] deadends1 = {"0201","0101","0102","1212","2002"};
		String target1 = "0202";
		System.out.println("Min total no of turns to achieve from 0000 to "+target1 +" : "
						+openLock.openLock2EndBfsWithOptimization(deadends1, target1));
		String[] deadends2 = {"8888"};
		String target2 = "0009";
		System.out.println("Min total no of turns to achieve from 0000 to "+target2 +" : "
						+openLock.openLock2EndBfs(deadends2, target2));
		String[] deadends3 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
		String target3 = "8888";
		System.out.println("Min total no of turns to achieve from 0000 to "+target3 +" : "
						+openLock.openLock2EndBfs(deadends3, target3));
		String[] deadends4 = {"0000"};
		String target4 = "0202";
		System.out.println("Min total no of turns to achieve from 0000 to "+target4 +" : "
						+openLock.openLock2EndBfs(deadends4, target4));
		String[] deadends5 = {"0201","0101","0102","1212","2002"};
		String target5 = "0000";
		System.out.println("Min total no of turns to achieve from 0000 to "+target5 +" : "
						+openLock.openLock2EndBfs(deadends5, target5));
	}

}
