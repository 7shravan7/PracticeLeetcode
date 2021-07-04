package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**    443. String Compression
 * 
 * Given an array of characters chars, compress it using the following algorithm:

   Begin with an empty string s. For each group of consecutive repeating characters in chars:

   If the group's length is 1, append the character to s.
   Otherwise, append the character followed by the group's length.
   The compressed string s should not be returned separately, but instead be stored in the input character array chars.
   Note that group lengths that are 10 or longer will be split into multiple characters in chars.

   After you are done modifying the input array, return the new length of the array.

   You must write an algorithm that uses only constant extra space.

	Example 1:
		Input: chars = ["a","a","b","b","c","c","c"]
		Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
		Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
		
	Example 2:
		Input: chars = ["a"]
		Output: Return 1, and the first character of the input array should be: ["a"]
		Explanation: The only group is "a", which remains uncompressed since it's a single character.
		
	Example 3:
		Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
		Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
		Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
		
	Example 4:
		Input: chars = ["a","a","a","b","b","a","a"]
		Output: Return 6, and the first 6 characters of the input array should be: ["a","3","b","2","a","2"].
		Explanation: The groups are "aaa", "bb", and "aa". This compresses to "a3b2a2". 
					 Note that each group is independent even if two groups have the same character.
					 
	Constraints:
		1 <= chars.length <= 2000
		chars[i] is a lower-case English letter, upper-case English letter, digit, or symbol.
 */
public class StringCompression {

	public int compress(char[] chars) {
		char ch = chars[0];
		int chCount=1;
		int j=0;
		for(int i=1;i<chars.length;i++) {
			if(ch == chars[i]) {
				chCount++;
			} else {
				chars[j++] = ch;
				if(chCount == 1) {
					ch = chars[i];
					continue;
				}
				j = insertCount(chars, j, chCount);
				ch = chars[i];
				chCount =1;
			}
		}
		chars[j++] = ch;
		if(chCount>1) {
			j = insertCount(chars, j, chCount);
		}
		return j;
	}
	
	/*
	 * With this implementation time reduced to 1ms
	 */
	private int insertCount(char[] chars, int index, int chCount) {
		char[] countArr = Integer.toString(chCount).toCharArray();
		for(char ch:countArr) {
			chars[index++] = ch;
		}
		return index;
	}
	
	 /*
	  * manual method to insert count
	 	with this method Time: 5ms
	 */
	 private int insertCountManual(char[] chars, int index, int chCount) {
		List<Character> temp = new ArrayList<>();
		while(chCount>0) {
			String digit = ""+chCount%10;
			temp.add(digit.charAt(0));
			chCount /= 10;
		}
		for(int i=temp.size()-1;i>=0;i--) {
			chars[index++] = temp.get(i);
		}
		return index;
	}
	
	public static void printChars(char[] chars, int len) {
		System.out.print("Chars {");
		for(int i=0;i<len;i++) {
			System.out.print(chars[i]);
			if(i==len-1) {
				System.out.print("}");
			} else {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		StringCompression strCompression = new StringCompression();
		char[] chars1 = {'a','a','b','b','c','c','c'};
		int compression1Length = strCompression.compress(chars1);
		System.out.println("compression1 Length : "+compression1Length);
		printChars(chars1, compression1Length);
		char[] chars2 = {'a'};
		int compression2Length = strCompression.compress(chars2);
		System.out.println("compression2 Length : "+compression2Length);
		printChars(chars2, compression2Length);
		char[] chars3 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
		int compression3Length = strCompression.compress(chars3);
		System.out.println("compression3 Length : "+compression3Length);
		printChars(chars3, compression3Length);
		char[] chars4 = {'a','a','a','b','b','a','a'};
		int compression4Length = strCompression.compress(chars4);
		System.out.println("compression4 Length : "+compression4Length);
		printChars(chars4, compression4Length);
		
	}

}
