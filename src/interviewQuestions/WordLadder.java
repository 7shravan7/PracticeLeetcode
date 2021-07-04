package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* **Hard**   127. Word Ladder
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words 
 * beginWord -> s1 -> s2 -> ... -> sk such that:

   Every adjacent pair of words differs by a single letter.
   Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
   sk == endWord
   Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest 
   transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

   	Example 1:
		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
		Output: 5
		Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

	Example 2:
		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
		Output: 0
		Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

	Constraints:
		1 <= beginWord.length <= 10
		endWord.length == beginWord.length
		1 <= wordList.length <= 5000
		wordList[i].length == beginWord.length
		beginWord, endWord, and wordList[i] consist of lowercase English letters.
		beginWord != endWord
		All the words in wordList are unique.
 */
public class WordLadder {
	/*
	 * 88ms
	 * Time Complexity : O(M^2×N) M is the length of each word and N is the total number of words in the input list
	 * Space Complexity: O(M^2×N)
	 */
	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		if(!wordList.contains(endWord)) {
			return 0;
		}
		Queue<String> visitedQueue = new LinkedList<>();
		visitedQueue.add(beginWord);
		Set<String> wordSet = new HashSet<>(wordList);
		wordSet.remove(beginWord);
		int level = 0;
		List<String> visitedList = new ArrayList<>();
		while(!visitedQueue.isEmpty()) {
			int size = visitedQueue.size();
			level++;
			for(int i=0;i<size;i++) {
				String word = visitedQueue.poll();
				visitedList.add(word);
				List<String> neighboursList = getNeighboursList(word);
				if(neighboursList.contains(endWord)) {
					return level+1;
				}
				for(String neighbour : neighboursList) {
					if(wordSet.contains(neighbour)) {
						visitedQueue.add(neighbour);
						wordSet.remove(neighbour);
					}
				}
			}
		}
		return 0;
	}
	
	/*	Double ended BFS : 17ms
	 * Time and space would be same as prev but search time is reduced to half since two parallel searches meet somewhere in the middle.
	 * Time Complexity : O(M^2×N) M is the length of each word and N is the total number of words in the input list
	 * Space Complexity: O(M^2×N)
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if(!wordList.contains(endWord)) {
			return 0;
		}
		Set<String> wordSet = new HashSet<>(wordList);
		Set<String> beginSet = new HashSet<>();
		beginSet.add(beginWord);
		Set<String> endSet = new HashSet<>();
		endSet.add(endWord);
		int level = 1;
		while(!beginSet.isEmpty() && !endSet.isEmpty()) {
			if(beginSet.size()>endSet.size()) {
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}
			Set<String> newBeginSet = new HashSet<>();
			for(String str: beginSet) {
				List<String> neighboursList = getNeighboursList(str);
				for(String neighbour: neighboursList) {
					if(endSet.contains(neighbour)) {
						return level+1;
					}
					if(wordSet.contains(neighbour)) {
						newBeginSet.add(neighbour);
						wordSet.remove(neighbour);
					}
				}
			}
			beginSet = newBeginSet;
			level++;
		}
		return 0;
	}
	
	private List<String> getNeighboursList(String beginWord) {
		List<String> neighboursList = new ArrayList<>();
		char[] wordArray = beginWord.toCharArray();
		for(int i=0;i<wordArray.length;i++) {
			char temp = wordArray[i];
			for(Character ch='a';ch<='z';ch++) {
				wordArray[i] = ch;
				neighboursList.add(new String(wordArray));
			}
			wordArray[i] = temp;
		}
		return neighboursList;
	}

	public static void main(String[] args) {
		WordLadder wordLadder = new WordLadder();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		System.out.println("Ladder length : "+wordLadder.ladderLength(beginWord, endWord, wordList));
		String beginWord1 ="cet";
		String endWord1 = "ism";
		List<String> wordList1 = Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay",
				"per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry",
				"lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag",
				"yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry",
				"led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply",
				"awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut",
				"why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe",
				"ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot",
				"tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot",
				"ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop"
				,"but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp",
				"wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day",
				"apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum",
				"pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat",
				"pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap",
				"lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla",
				"auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see",
				"zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet",
				"ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog",
				"ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada",
				"bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye",
				"pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp",
				"hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly",
				"rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg",
				"put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix",
				"cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea",
				"bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban",
				"flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad",
				"nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job",
				"wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill",
				"was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia",
				"kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig",
				"cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor",
				"ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay",
				"hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe",
				"amp","ale","bud","gee","pin","dun","pat","ten","mob");
		System.out.println("Ladder length 1 : "+wordLadder.ladderLength(beginWord1, endWord1, wordList1));
	}

}
