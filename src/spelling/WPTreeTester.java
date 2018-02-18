package spelling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WPTreeTester {

	private WPTree tree;
	private List<String> list;


	@Before
	public void setup() {
		tree = new WPTree();
		list = new ArrayList<>();
	}

	@Test
	public void shortPath() {
		List<String> expected = Stream
				.of("pool", "spool", "spoon")
				.collect(Collectors.toList());
		list = tree.findPath("pool", "spoon");
		Assert.assertEquals(expected, list);
	}

	@Test
	public void longPath() {
		List<String> expected = Stream
				.of("stools", "spools", "spoons", "spoon", "soon", "moon")
				.collect(Collectors.toList());
		list = tree.findPath("stools", "moon");
		Assert.assertEquals(expected, list);
	}

}
