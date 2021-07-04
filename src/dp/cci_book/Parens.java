package dp.cci_book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parens {
	
	public List<String> getParenthesisCombinationsList(int n) {
		List<String> parentheisList = new ArrayList<>();
		parentheisList.addAll(getParensCombinations(n));
		return parentheisList;
	}
	/*
	 * if n=2, (()),()()
	 * if n=3, (()) -> (()()) // insert () after 1st left paren
	 *              -> ((())) // insert () after 2nd left paren
	 *              -> ()(()) // insert () before string
	 *         ()() -> (())() // insert () after 1st left paren
	 *              -> ()(()) // insert () after 2nd left paren
	 *              -> ()()() // insert () before string
	 * ()(()) is repeated twice...so there will be duplicates...so if added in a set, it will remove duplicates
	 */
	private Set<String> getParensCombinations(int n){
		Set<String> set = new HashSet<>();
		if(n==0) {
			set.add("");
			return set;
		}
		Set<String> prevSet = getParensCombinations(n-1);
		for(String str:prevSet) {
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i)=='(') {
					set.add(insertAfterLeftOpenParen(str, i));
				}
			}
			set.add("()"+str);
		}
		return set;
	}
	
	private String insertAfterLeftOpenParen(String str, int leftParenIndex) {
		String before = str.substring(0, leftParenIndex+1);
		String after = str.substring(leftParenIndex+1);
		return before+"()"+after;
	}
	
	/*
	 * first add all left paren and then add right paren and recur the function
	 * 1.add left paren unless it is not greater than n
	 * 2.add right paren unless it is invalid..ie., not greater than left paren
	 */
	public List<String> getParenthesisCombinationsListApporoach1(int n){
		List<String> parentheisList = new ArrayList<>();
		char[] charArray = new char[2*n];
		addParen(parentheisList, n, n, charArray, 0);
		return parentheisList;
	}
	
	private void addParen(List<String> parentheisList, int leftRem, int rightRem, char[] charArray, int index) {
		System.out.println("leftRem:"+leftRem+";rightRem:"+rightRem+";index:"+index+";str:"+new String(charArray));
		if(leftRem<0 || rightRem<leftRem) {
			return;
		}
		if(leftRem==0 && rightRem==0) {
			parentheisList.add(new String(charArray));
		} else {
			if(leftRem>0) {
				charArray[index]='(';
				addParen(parentheisList, leftRem-1, rightRem, charArray, index+1);
			}
			if(rightRem>leftRem) {
				charArray[index]=')';
				addParen(parentheisList, leftRem, rightRem-1, charArray, index+1);
			}
		}
	}
	
	public void printList(List<String> list) {
		System.out.println("The paranthesis list size : "+ list.size());
		list.forEach(System.out::println);
	}

	public static void main(String[] args) {
		Parens parens = new Parens();
		int n=3;
		//parens.printList(parens.getParenthesisCombinationsList(n));
		System.out.println("-- 2nd approach --");
		parens.printList(parens.getParenthesisCombinationsListApporoach1(n));
	}

}
