package june2020Challenges.week_1_1to7;

public class ReverseString {

	public void reverseString(char[] s) {
		int len = s.length;
		int i=0;
		int j=len-1;
		while(i<j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		ReverseString rs =new ReverseString();
		char[] input = {'h','e','l','l','o'};
		System.out.println(input);
		rs.reverseString(input);
		System.out.println(input);
	}

}
