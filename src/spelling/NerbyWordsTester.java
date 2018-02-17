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
		List<String> control = new ArrayList<>(Arrays.asList("horse", "horse"));
		String word = "horsee";
		nw.deletions(word, list, true);
		Assert.assertEquals(control, list);
	}

	@Test
	public void deletionsNotWord() {
		List<String> control = Stream
				.of("orsee", "hrsee", "hosee", "horee", "horse", "horse")
				.collect(Collectors.toCollection(ArrayList::new));

		String word = "horsee";
		nw.deletions(word, list, false);
		Assert.assertEquals(control, list);
	}


}
