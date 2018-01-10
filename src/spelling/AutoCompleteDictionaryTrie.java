package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Jean-Baptiste Bouhier
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode              root;
    private LinkedList<TrieNode>  predictions;
	private ArrayList<String>     completions;
    private int                   size;
    

    public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
		predictions = new LinkedList<>();
		completions = new ArrayList<>();
		size = 0;
	}
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	@Override
	public boolean addWord(String word) {
		char[]    chars  =  word.toLowerCase().toCharArray();
		int       len    =  chars.length - 1;
		TrieNode  n      =  root;

		for (int i = 0; i <= len; i++) {
			char c = chars[i];
			TrieNode next = n.getChild(c);

			if (next == null) {
				n.insert(c);
				n.getChild(c).setEndsWord(i == len);
				if (i == len) return true;
			} else if (i == len) {
				next.setEndsWord(true);
				return true;
			}
			n = n.getChild(c);
		}

		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	@Override
	public int size() {
		wordCount(root);
	    return size;
	}

	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {
		char[]    chars  =  s.toLowerCase().toCharArray();
		int       len    =  chars.length - 1;
		TrieNode  n      =  root;
		TrieNode  next;

		for (int i = 0; i <= len; i++) {
			char c = chars[i];
			next = n.getChild(c);
			if (next == null)   break;
			else if (i == len)  return next.endsWord();
			n = n.getChild(c);
		}

		return false;
	}

	public static void main(String args[]) {

	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
	 * in the list of returned words.
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
	@Override
 	public List<String> predictCompletions(String prefix, int numCompletions) {
		char[]             chars       =  prefix.toLowerCase().toCharArray();
		int                len         =  chars.length - 1;
		TrieNode           n           =  root;
		TrieNode           next;

		completions.clear();

		for (int i = 0; i <= len; i++) {
			char c = chars[i];
			next = n.getChild(c);
			if (next == null) return completions;
			n = next;
		}

		predictions.clear();
		predictions.add(n);
		TrieNode curr;

		// 3. Perform a Breadth first search to generate completions
		// 	  While the LinkedList is not empty and you don't have enough completions:
		while (!predictions.isEmpty() && numCompletions > 0) {

			// Remove the first Node from the LinkedList
			curr = predictions.getFirst();

			// If it is a word, add it to the completions list
			if (curr.endsWord()) {
				completions.add(curr.getText());
				numCompletions--;
			}

			// Add all of its child nodes to the back of the LinkedList
			for (Character c : curr.getValidNextCharacters()) {
				TrieNode nextN = curr.getChild(c);
				if (nextN != null) predictions.add(nextN);
			}
		}

         return completions;
     }


 	// For debugging
 	public void printTree() {
 		printNode(root);
 	}


 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr) {
 		if (curr == null) return;
		String leaf = curr.endsWord() ? " -- LEAF" : "";
		System.out.println(curr.getText() + leaf);
 		TrieNode next = null;

 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}


	/** Helper method for size() */
	private void wordCount(TrieNode curr) {
		if (curr == null) return;
		if (curr.endsWord()) size++;

		for (Character c : curr.getValidNextCharacters()) {
			TrieNode next = curr.getChild(c);
			if (next != null) wordCount(next);
		}
	}

	
}