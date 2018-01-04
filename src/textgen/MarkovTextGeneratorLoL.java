package textgen;

import java.util.*;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	private List<ListNode> wordList;
	private String starter;
	private Random rnGenerator;


	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	@Override
	public void train(String sourceText) {
		if (isEmpty(sourceText)) return;

		String[] textArr = sourceText.split(" +");
		starter = textArr[0];
		wordList.add(new ListNode(starter));
		String prevWord = starter;

		for (int i = 1; i < textArr.length; i++) {
			String word = textArr[i];
			wordListAlgo(word, prevWord);
			prevWord = word;
		}

		ListNode lastNode = new ListNode(textArr[textArr.length - 1]);
		lastNode.addNextWord(starter);
		wordList.add(lastNode);
	}


	@Override
	public void retrain(String sourceText) {
		wordList.clear();
		starter = "";
		train(sourceText);
	}


	@Override
	public String generateText(int numWords) {
		if (numWords <= 0 || starter.equals("") || wordList.isEmpty())
			return "";

		int i = 0;
		StringBuilder output = new StringBuilder();
		String currWord = starter;
		output.append(currWord);
		output.append(" ");
		i++;

		while (i < numWords) {
			for (ListNode node : wordList) {
				String nodeWord = node.getWord();

				if (currWord.equals(nodeWord)) {
					String rdmWord = node.getRandomNextWord(rnGenerator);
					output.append(rdmWord);
					output.append(" ");
					currWord = rdmWord;
					break;
				}
			}
			i++;
		}

		return output.toString();
	}


	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();

		for (ListNode n : wordList)
			toReturn.append(n.toString());

		return toReturn.toString();
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


	private boolean isEmpty(String s) {
		return (s == null || s.isEmpty());
	}


	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println("INPUT: " + textString);
		gen.train(textString);
//		System.out.println(gen);
		System.out.println("OUTPUT: "+ gen.generateText(20) + "\n\n");

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
		System.out.println("INPUT: " + textString2);
		gen.retrain(textString2);
//		System.out.println(gen);
		System.out.println("OUTPUT: "+ gen.generateText(20) + "\n");
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


