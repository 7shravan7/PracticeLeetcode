package dp.cci_book;

import java.util.HashMap;
import java.util.Map;

public class BooleanEvaluation {
	
	private int getNumberOfEvalWays(String str, boolean result, Map<String,Integer> memMap) {
		if(str.length()<=0) {
			return 0;
		}
		if(str.length()==1) {
			return stringToBoolean(str) == result ? 1: 0;
		}
//		System.out.println("need");
		if(memMap.containsKey(result+str)) {
//			System.out.println("already found");
			return memMap.get(result+str);
		}
		int ways=0;
		for(int i=1;i<str.length();i+=2) {
			char operator = str.charAt(i);
			String left = str.substring(0,i);
			String right = str.substring(i+1);
			int leftTrue = getNumberOfEvalWays(left, true, memMap);
			int leftFalse = getNumberOfEvalWays(left, false, memMap);
			int rightTrue = getNumberOfEvalWays(right, true, memMap);
			int rightFalse = getNumberOfEvalWays(right, false, memMap);
			int totalVal = (leftTrue+leftFalse) * (rightTrue+rightFalse);
			int trueVal=0;
			if(operator == '|') {
				trueVal = leftTrue*rightTrue + leftFalse*rightTrue + leftTrue*rightFalse;
			} else if (operator == '&') {
				trueVal = leftTrue*rightTrue;
			} else { //which is ^
				trueVal = leftTrue*rightFalse + leftFalse*rightTrue;
			}
			ways += result ? trueVal : totalVal-trueVal;
		}
		memMap.put(result+str, ways);
		return ways;
	}
	
	private boolean stringToBoolean(String str) {
		return "1".equals(str) ? true : false;
	}


	public static void main(String[] args) {
		//String str = "1^0|0|1";
		//boolean result = false;
		String str = "0&0&0&1^1|0";
		boolean result = true;
		BooleanEvaluation be = new BooleanEvaluation();
		Map<String,Integer> memMap = new HashMap<>();
		int numberOfEvalWays = be.getNumberOfEvalWays(str, result, memMap);
		System.out.println("numberOfEvalWays("+str+","+result+"):"+numberOfEvalWays);
	}

}
