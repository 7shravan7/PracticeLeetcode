package dp.leetcode;

/*
 * Time Complexity - O(n^2)
 * Space Complexity - O(1)
 */
public class LongestPalindromicSubstring {
	
	public String getLongestPalindromicSubstring(String str) {
		int maxLength = 0;
		int startIndex=0;
		int strLen = str.length();
		for(int i=0;i<strLen;i++) {
			// handling with mid-x,mid,mid+x (ababc)
			for(int x=0;i-x>=0&&i+x<strLen;x++) {
				if(str.charAt(i-x)!=str.charAt(i+x)) {
					break;
				}
				if(maxLength<(2*x+1)) {
					maxLength = 2*x+1;//nothing but length with str[i] as middle and str[i-x]..str[i]..str[i+x]
					startIndex = i-x;
				}
			}
			// handling for mid and mid+1....and then mid-1 & mid+2. (pnnp,abbbba)
			for(int y=1;((i-y+1)>=0) && ((i+y)<strLen);y++) {
				if(str.charAt(i-y+1)!=str.charAt(i+y)) {
					break;
				}
				if(maxLength<(2*y)) {
					maxLength = 2*y; // getting the len of same value str[x],str[x+1] first ..if equal then with str[x-1] and str[x+2]
					startIndex = i-y+1;
				}
			}
		}
		return str.substring(startIndex,startIndex+maxLength);
	}

	public static void main(String[] args) {
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		String str= "pnnp";
		String longestPalindromicSubstring = lps.getLongestPalindromicSubstring(str);
		System.out.println("LPS :"+longestPalindromicSubstring);
	}

}
