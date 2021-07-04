package dp.cci_book;

import java.util.ArrayList;
import java.util.List;

public class PermutationUniqueString {
	
	/* prefix based approach and remaining part
	 * if str='abcd'
	 * 
	 * Perm(abcd) = a+Perm(bcd), b+Perm(acd), c+Perm(abd), d+Perm(abc)
	 * Perm(bcd) = b+Perm(cd), c+Perm(bd), d+Perm(bc)
	 * Perm(cd) = cd,dc ; Perm(bd) = bd,db ; Perm(bc) = bc,cb
	 * so Perm(bcd) => b+{cd,dc} ; c+{bd,db} ; d+{bc,cb} => bcd,bdc;cbd,cdb;dbc,dcb
	 * ...... so on
	 * 
	 * 1.prefix='',remaining='abcd' => p-prefix,r-remaining
	 * 2.p='a',r='bcd'
	 * 3.	p='ab',r='cd'
	 * 4.		p='abc',r='d'
	 * 5.			p='abcd',r='' so if r='', add prefix to list(a permuation found)
	 * 6.		p='abd',r='c'
	 * 7.			='abdc',r='' add to list
	 * 8.   p='ac',r='bd'
	 * 			........so on
	 *     
	 * 
	 */
	private void permutation(String prefix,String remainingString, List<String> permuationList) {
		if(remainingString.length()==0) {
			permuationList.add(prefix);
			return;
		}
		int len = remainingString.length();
		for(int i=0;i<len;i++) {
			String before = remainingString.substring(0, i);
			char c = remainingString.charAt(i);
			String after = remainingString.substring(i+1);
			permutation(prefix+c, before+after, permuationList);
		}
	}
	
	public List<String> getPermutation(String str) {
		List<String> permuationList = new ArrayList<>();
		String prefix="";
		permutation(prefix, str, permuationList);
		return permuationList;
	}

	public static void main(String[] args) {
		PermutationUniqueString permuation = new PermutationUniqueString();
		List<String> permutedList = permuation.getPermutation("abcd");
		System.out.println("Permutation List length: "+ permutedList.size());
		for(String str:permutedList) {
			System.out.println(str);
		}
	}

}
