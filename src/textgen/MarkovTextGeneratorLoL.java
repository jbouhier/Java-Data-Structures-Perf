package textgen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;
	
	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	
	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	private ArrayList<String> cutText(String text) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[!?.]+|[a-zA-Z]+");
		Matcher m = tokSplitter.matcher(text);

		while (m.find())
			tokens.add(m.group());

		Iterator iter = tokens.iterator();

		while (iter.hasNext()) {
			if (!isWord( (String)iter.next() ))
				iter.remove();
		}
		return tokens;
	}

	private boolean isWord(String tok) {
		return !(tok.indexOf("!") >= 0 || tok.indexOf(".") >= 0 || tok.indexOf("?") >= 0);
	}

	private void wordListAlgo(String word, String prevWord) {
		ListNode newWord = null;

		for (ListNode list : wordList) {
			newWord = null;

			if (list.getWord().equals(prevWord)) {
				list.addNextWord(word);
				break;
			} else {
				newWord = new ListNode(prevWord);
				newWord.addNextWord(word);
			}
		}

		if (newWord != null)
			wordList.add(newWord);
	}

	@Override
	public void train(String sourceText) {
		ArrayList<String> textArr = cutText(sourceText);
		starter = textArr.get(0);
		wordList.add(new ListNode(starter));
		String prevWord = starter;

		for (int i = 1; i < textArr.size(); i++) {
			String word = textArr.get(i);
			wordListAlgo(word, prevWord);
			prevWord = word;
		}

		ListNode lastNode = new ListNode(textArr.get(textArr.size() - 1));
		lastNode.addNextWord(starter);
		wordList.add(lastNode);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (numWords <= 0) return "";

		int i = 0;
		StringBuilder output = new StringBuilder();
		String currWord = starter;
		output.append(currWord);
		i++;

		while (i < numWords) {
			for (int j = 0; j < wordList.size(); j++) {
				ListNode node = wordList.get(j);
				String nodeWord = node.getWord();

				if (currWord.equals(nodeWord)) {
					String rdmWord = node.getRandomNextWord(rnGenerator);
					output.append(rdmWord);
					currWord = rdmWord;
					break;
				}
			}
			i++;
		}

		return output.toString();
	}
	
	// Can be helpful for debugging
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();

		for (ListNode n : wordList)
			toReturn.append(n.toString());

		return toReturn.toString();
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);

		gen.train(textString);
		System.out.println(gen);

		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode {
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator) {
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString() {
		StringBuilder toReturn = new StringBuilder(word);
		toReturn.append(": ");

		for (String s : nextWords) {
			toReturn.append(s);
			toReturn.append("->");
		}

		toReturn.append("\n");
		return toReturn.toString();
	}
}


