package ee.pdfarve.common.posorder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CandidatesComparatorTest {
	
	private Node root;
	private CandidatesComparator comparator;

	@Before
	public void setUp() throws Exception {
		root = NodeTest.getRootInitializedWithTrainingData();
		comparator = new CandidatesComparator(root);
	}

	@Test
	public void test() {
		LocationPointWithText candidate1 = new LocationPointWithText(313, 689, "123");
		LocationPointWithText candidate2 = new LocationPointWithText(502, 166, "456");
		LocationPointWithText candidate3 = new LocationPointWithText(113, 147, "789");
		List<LocationPointWithText> candidates = new ArrayList<>();
		candidates.add(candidate1);
		candidates.add(candidate2);
		candidates.add(candidate3);
		Collections.sort(candidates, comparator);
		assertEquals(candidates.get(0), candidate2);
		assertEquals(candidates.get(1), candidate3);
		assertEquals(candidates.get(2), candidate1);
	}

}
