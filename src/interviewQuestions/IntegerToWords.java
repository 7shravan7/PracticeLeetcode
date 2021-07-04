package interviewQuestions;

/* **Hard**   273. Integer to English Words
 *  Convert a non-negative integer num to its English words representation.

	Example 1:
		Input: num = 123
		Output: "One Hundred Twenty Three"
		
	Example 2:
		Input: num = 12345
		Output: "Twelve Thousand Three Hundred Forty Five"
		
	Example 3:
		Input: num = 1234567
		Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
		
	Example 4:
		Input: num = 1234567891
		Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
		
	Constraints:
		0 <= num <= 231 - 1
 */
public class IntegerToWords {
	
	String[] lessThan20 = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven",
			"Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	
	String[] tensDigits = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	
	String[] thousands = {"","Thousand","Million","Billion"};
	
	public String numberToWords(int num) {
		if(num == 0) {
			return "Zero";
		}
		String words = "";
		int thousandsIndex = 0;
		while(num>0) {
			int val = num%1000;
			if(val!=0) {
				words = convertToWords(val)+ thousands[thousandsIndex]+ " "+ words;
			}
			num = num/1000;
			thousandsIndex++;
		}
		
		return words.trim();
	}
	
	private String convertToWords(int num) {
		if(num == 0) {
			return "";
		} else if(num<20) {
			return lessThan20[num]+ " ";
		} else if(num<100) {
			return tensDigits[num/10]+ " "+ convertToWords(num%10);
		} else {
			return lessThan20[num/100] + " Hundred "+ convertToWords(num%100); 
		}
	}

	public static void main(String[] args) {
		IntegerToWords int2Words = new IntegerToWords();
		int num1 = 123;
		System.out.println(num1+" : "+int2Words.numberToWords(num1));
		int num2 = 12345;
		System.out.println(num2+" : "+int2Words.numberToWords(num2));
		int num3 = 1234567;
		System.out.println(num3+" : "+int2Words.numberToWords(num3));
		int num4 = 1234567891;
		System.out.println(num4+" : "+int2Words.numberToWords(num4));
		int num5 = 1000000010;
		System.out.println(num5+" : "+int2Words.numberToWords(num5));
	}

}
