package dp.cci_book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermuationWithDup {
	
	/*
	 * approach is similar to Permutation with unique string but here we are considering count/frequency to remove
	 * duplicate permuatation in the list
	 * if str='aba', valid permuation is only 3 (ie.,) aba,aab,baa & rest all are duplicate
	 * 
	 * Approach is to get frequency of each char..here a=2,b=1
	 * Perm(a2b1) => a+Perm(a1b1),b+Perm(a2b0)
	 * Perm(a1b1) => a+Perm(a0b1),b+perm(a1b0) => ab,ba
	 * Perm(a2b0) => a+Perm(a1b0) => aa
	 * so Perm(a2b1) => a+Perm(a1b1),b+Perm(a2b0) => a+{ab,ba},b+{aa} => aab,aba,baa
	 */
	public List<String> getPermutaionWithDup(String str){
		List<String> permutationList = new ArrayList<>();
		Map<Character,Integer> frequencyMap = new HashMap<>();
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			int currCount = frequencyMap.getOrDefault(c, 0);
			frequencyMap.put(c, currCount+1);
		}
		computePermutation("", str.length(), frequencyMap,permutationList);
		return permutationList;
	}
	
	private void computePermutation(String prefix, int remainingLength, Map<Character, Integer> frequencyMap,
			List<String> permutationList) {
		if(remainingLength==0) {
			permutationList.add(prefix);
			return;
		}
		frequencyMap.keySet().forEach(ch->{
			int frequency = frequencyMap.get(ch);
			if(frequency>0) {
				frequencyMap.put(ch, frequency-1);
				computePermutation(prefix+ch, remainingLength-1, frequencyMap, permutationList);
				frequencyMap.put(ch, frequency);
			}
		});
	}

	public static void main(String[] args) {
		PermuationWithDup permutationDup = new PermuationWithDup();
		List<String> permutedList = permutationDup.getPermutaionWithDup("acdb");
		System.out.println("Permutation List length: "+ permutedList.size());
		for(String str:permutedList) {
			System.out.println(str);
		}
	}

}
