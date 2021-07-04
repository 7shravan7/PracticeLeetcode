package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Hard**   68. Text Justification
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth
 *  characters and is fully (left and right) justified.
 *  
	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
	Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a 
    line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the
     slots on the right.

    For the last line of text, it should be left justified and no extra space is inserted between words.

    Note:
		A word is defined as a character sequence consisting of non-space characters only.
		Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
		The input array words contains at least one word.
 
	Example 1:
		Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
		Output:
				[
   				 "This    is    an",
   				 "example  of text",
   				 "justification.  "
				]
				
	Example 2:
		Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
		Output:
		  [
    		"What   must   be",
  			"acknowledgment  ",
  			"shall be        "
		  ]
		Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line
		 must be left-justified instead of fully-justified.
		Note that the second line is also left-justified becase it contains only one word.
		
	Example 3:
		Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a",
		                "computer.","Art","is","everything","else","we","do"], maxWidth = 20
		Output:
		  [
  			"Science  is  what we",
  			"understand      well",
  			"enough to explain to",
  			"a  computer.  Art is",
  			"everything  else  we",
  			"do                  "
		  ]
 
  Constraints:
	1 <= words.length <= 300
	1 <= words[i].length <= 20
	words[i] consists of only English letters and symbols.
	1 <= maxWidth <= 100
	words[i].length <= maxWidth
 */
public class TextJustification {
	
	private String appendSpaces(int spacesCount) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<spacesCount;i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	private String justifyString(List<String> lineList, int lettersCount, int maxWidth) {
		int wordsCount = lineList.size();
		int wordSpaces = wordsCount-1;
		int diff = maxWidth - lettersCount;
		StringBuilder sb = new StringBuilder(maxWidth);
		if(wordSpaces==0) {  // only one word
			sb.append(lineList.get(0));
			sb.append(appendSpaces(diff)); // add remaining spaces
		} else {
			int equalSpaces = diff/wordSpaces;
			int extraSpaces = diff%wordSpaces;
			for(int i=0;i<wordsCount;i++) {
				sb.append(lineList.get(i));
				if(i!=wordsCount-1) { // for last word in line we wont add spaces
					sb.append(appendSpaces(equalSpaces));
					if(extraSpaces-->0) { // add if any extra spaces needed
						sb.append(" ");
					}
				}
			}
		}
		return sb.toString();
	}
	
	private String justifyLastLine(List<String> lastLineList, int letterCount, int maxWidth) {
		int diff = maxWidth - letterCount;
		StringBuilder sb = new StringBuilder(maxWidth);
		for(int i=0;i<lastLineList.size();i++) {
			sb.append(lastLineList.get(i));
			if(i!=lastLineList.size()-1) {
				sb.append(" ");
				diff--;
			}
		}
		sb.append(appendSpaces(diff));
		return sb.toString();
	}
	
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> justifiedStringList = new ArrayList<>();
//		List<List<String>> linesList = new ArrayList<>();
		List<String> lineList = new ArrayList<>();
		int letterCount = 0;
		int spacesBtwnWords = 0;
		for(String word:words) {
			if(letterCount+word.length()+spacesBtwnWords>maxWidth) {
				List<String> newList = new ArrayList<>(lineList);
//				linesList.add(newList);
				justifiedStringList.add(justifyString(newList, letterCount, maxWidth));
				lineList.clear();
				letterCount = 0;
				spacesBtwnWords=0;
			}
			lineList.add(word);
			letterCount += word.length();
			spacesBtwnWords++;
		}
		//linesList.add(lineList);
		justifiedStringList.add(justifyLastLine(lineList, letterCount, maxWidth));
        return justifiedStringList;
    }
	
	public void printResultList(List<String> resultList) {
		System.out.println("--------- ResultList ------------------");
		for(String result:resultList) {
			System.out.println(result);
		}
		System.out.println("---------------------------------------");
	}

	public static void main(String[] args) {
		TextJustification textJustify = new TextJustification();
		String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth1 = 16;
		System.out.println("Justified words1 list ");
		textJustify.printResultList(textJustify.fullJustify(words1, maxWidth1));
		String[] words2 = {"What","must","be","acknowledgment","shall","be"};
		int maxWidth2 = 16;
		System.out.println("Justified words2 list ");
		textJustify.printResultList(textJustify.fullJustify(words2, maxWidth2));
		String[] words3 = {"Science","is","what","we","understand","well","enough","to","explain","to","a",
                "computer.","Art","is","everything","else","we","do"};
		int maxWidth3 = 20;
		System.out.println("Justified words3 list ");
		textJustify.printResultList(textJustify.fullJustify(words3, maxWidth3));
	}

}
