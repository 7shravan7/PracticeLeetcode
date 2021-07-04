package dp.cci_book;

public class RecursiveMultiply {
	
	public int getRecursiveMultiply(int num1, int num2) {
		int temp = 0;
		if(num1>num2) {
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		System.out.println("getRecursiveMultiply("+num1+","+num2+")");
		return getMultipliedValue(num1, num2);
	}
	
	private int getMultipliedValue(int smaller, int larger) {
		if(smaller==0 || larger ==0) {
			return 0;
		} else if (smaller==1) {
			return larger;
		} else {
			int halfSmaller = smaller >> 1; //here dividing it by 2
			int getHalfMulVal = getRecursiveMultiply(halfSmaller, larger);
			if(smaller%2==0) {
				return getHalfMulVal+getHalfMulVal;
			} else {
				return getHalfMulVal+getHalfMulVal+larger;
			}
		}
	}

	public static void main(String[] args) {
		RecursiveMultiply rM = new RecursiveMultiply();
		int num1 = 17;
		int num2 = 23;
		int recursiveMulVal = rM.getMultipliedValue(num1, num2);
		System.out.println("("+num1+"*"+num2+")= "+recursiveMulVal);
		if(recursiveMulVal == num1*num2) {
			System.out.println("Correct answer");
		} else {
			System.out.println("Incorrect answer");
		}
	}

}
