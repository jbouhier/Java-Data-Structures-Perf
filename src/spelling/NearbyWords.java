package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;


	public NearbyWords (Dictionary dict) {
		this.dict = dict;
	}


	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly) {
		   List<String> retList = new ArrayList<>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		for (int index = 0; index < s.length(); index++) {
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuilder sb = new StringBuilder(s);
				sb.setCharAt(index, (char)charCode);
				String tmpStr = sb.toString();
				addWordToList(s, tmpStr, currentList, wordsOnly);
			}
		}
	}


	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly) {
		for (int index = 0; index <= s.length(); index++) {
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuilder sb = new StringBuilder(s);
				sb.insert(index, (char)charCode);
				String tmpStr = sb.toString();
				addWordToList(s, tmpStr, currentList, wordsOnly);
			}
		}
	}


	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly) {
		for (int index = 0; index < s.length(); index++) {
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(index);
			String tmpStr = sb.toString();
			addWordToList(s, tmpStr, currentList, wordsOnly);
		}
	}


	/**
	 * Helper method to avoid code duplication
	 * @param s The original String
	 * @param newWord The 'one-off' generated String
	 * @param currentList is the list of words to append modified words
	 * @param wordsOnly controls whether to return only words or any String
	 */
	private void addWordToList(String s, String newWord, List<String> currentList, boolean wordsOnly) {
		if (!currentList.contains(newWord) && !s.equals(newWord)) {
			if (wordsOnly && dict.isWord(newWord))
				currentList.add(newWord);
			else if (!wordsOnly)
				currentList.add(newWord);
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {
		List<String> queue = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		List<String> retList = new LinkedList<>();

		queue.add(word);
		visited.add(word);

		while (!queue.isEmpty() && retList.size() <= numSuggestions) {
			String curr = (String) ((LinkedList) queue).removeFirst();
			List<String> neighbors = distanceOne(curr, true);

			for (String n : neighbors) {
				if (!visited.contains(n)) {
					visited.add(n);
					queue.add(n);
					if (dict.isWord(n)) retList.add(n);
				}
			}
		}

		return retList;
	}


}
