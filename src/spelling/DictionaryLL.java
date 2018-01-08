package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;

    public DictionaryLL() {
        dict = new LinkedList<>();
    }


    /**
     * Add a word to the dictionary. (it wasn't already there).
     * Convert it to lowercase first.
     *
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     **/
    public boolean addWord(String word) {
        return !isWord(word) && dict.add(word.toLowerCase());
    }


    /** Return the number of words in the dictionary */
    public int size() {
        return dict.size();
    }


    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }


}
