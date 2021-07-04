package codes.LeetCode.may2020Challenges.week_1_1to7;

import java.util.HashSet;
import java.util.Set;

/*
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.
 */
public class JewelsAndStones {
	
	public static int numJewelsInStones(String J, String S) {
		int numJewelsInStones = 0;
		Set<Character> jSet =new HashSet<>();
		int jLen = J.length();
		for(int i=0; i<jLen; i++) {
			jSet.add(J.charAt(i));
		}
		int sLen = S.length();
		for(int i=0; i<sLen; i++) {
			if(jSet.contains(S.charAt(i))) {
				numJewelsInStones++;
			}
		}
        return numJewelsInStones;
    }

	public static void main(String[] args) {
		System.out.println(JewelsAndStones.numJewelsInStones("z", "aAAbbbb"));
	}

}
