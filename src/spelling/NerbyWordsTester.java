package spelling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Jean-Baptiste Bouhier
 */
public class NerbyWordsTester {

	private Dictionary d;
	private NearbyWords nw;
	private List<String> list;

	@Before
	public void setup() {
		d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		nw = new NearbyWords(d);
		list = new ArrayList<>();
	}

	@Test
	public void deletionsIsWord() {
		List<String> expected = new ArrayList<>();
		expected.add("horse");
		String word = "horsee";
		nw.deletions(word, list, true);
		Assert.assertEquals(expected, list);
	}

	@Test
	public void deletionsNotWord() {
		List<String> expected = Stream
				.of("orsee", "hrsee", "hosee", "horee", "horse")
				.collect(Collectors.toCollection(ArrayList::new));

		String word = "horsee";
		nw.deletions(word, list, false);
		Assert.assertEquals(expected, list);
	}

	@Test
	public void insertionsIsWord() {
		List<String> expected = new ArrayList<>(Arrays.asList("conveyed", "conveyer"));
		String word = "conveye";
		nw.insertions(word, list, true);
		Assert.assertEquals(expected, list);
	}

	@Test
	public void substitutionIsWord() {
		List<String> expected = new ArrayList<>(Arrays.asList("convene", "conveys"));
		String word = "conveye";
		nw.substitution(word, list, true);
		Assert.assertEquals(expected, list);
	}


}
