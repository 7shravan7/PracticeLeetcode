package dp.cci_book;

import java.util.HashMap;
import java.util.Map;

public class Coins {
	
	public int noOfWaysofMakingChange(int amount,int[] denomination, int index) {
		if(index>=denomination.length-1) { // last element in denomination is 1 so one way to achieve amount
			return 1;
		}
		int indexAmount = denomination[index];
		int noOfWays=0;
		for(int i=0;i*indexAmount<=amount;i++) {
			int remainingAmount = amount - (i*indexAmount);
//			System.out.println("denomination used : "+indexAmount+", remaining amount : "+remainingAmount+", i:"+i+", index:"+index);
			noOfWays += noOfWaysofMakingChange(remainingAmount, denomination, index+1);
		}
		return noOfWays;
	}
	
	public int noOfWaysofMakingChangeWithMemoization(int amount,int[] denomination, int index, 
			Map<AmountPojo,Integer> memMap) {
		AmountPojo amountObj = new AmountPojo(amount, index);
		if(memMap.containsKey(amountObj)) {
			return memMap.get(amountObj);
		}
		if(index>=denomination.length-1) { // last element in denomination is 1 so one way to achieve amount
			return 1;
		}
		int indexAmount = denomination[index];
		int noOfWays=0;
		for(int i=0;i*indexAmount<=amount;i++) {
			int remainingAmount = amount - (i*indexAmount);
//			System.out.println("denomination used : "+indexAmount+", remaining amount : "+remainingAmount+", i:"+i+", index:"+index);
			noOfWays += noOfWaysofMakingChange(remainingAmount, denomination, index+1);
		}
		memMap.put(amountObj, noOfWays);
		return noOfWays;
	}

	public static void main(String[] args) {
		Coins coins = new Coins();
		int[] denomination = {25,10,5,1};
		int amount = 50;
		System.out.println(coins.noOfWaysofMakingChange(amount, denomination, 0));
		System.out.println("--------------");
		// with memoization
		Map<AmountPojo,Integer> memMap = new HashMap<>();
		System.out.println(coins.noOfWaysofMakingChangeWithMemoization(amount, denomination, 0, memMap));
	}

}

class AmountPojo {
	int amount;
	int index;
	AmountPojo(int amount, int index){
		this.amount = amount;
		this.index = index;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + index;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmountPojo other = (AmountPojo) obj;
		if (amount != other.amount)
			return false;
		if (index != other.index)
			return false;
		return true;
	}
	
}
