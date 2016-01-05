package ee.pdfarve.common.posorder;

import static ee.pdfarve.common.posorder.CutDirectionType.HORIZONTAL;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ee.pdfarve.common.posorder.CutDirectionType;
import ee.pdfarve.common.posorder.GridConstants;
import ee.pdfarve.common.posorder.LocationPointWithText;
import ee.pdfarve.common.posorder.Node;

public class NodeTest {
	
	private Node root;
	
	public static Node getRootInitializedWithTrainingData() {
		List<LocationPointWithText> locations = new ArrayList<LocationPointWithText>();
		// Filling locations with reference number data; change to use your own training data
		locations.add(new LocationPointWithText(498, 158, "02613469450"));
		locations.add(new LocationPointWithText(502, 166, "23295000009"));
		locations.add(new LocationPointWithText(458, 93, "50064856910"));
		locations.add(new LocationPointWithText(145, 325, "10429150"));
		locations.add(new LocationPointWithText(445, 116, "1026665907"));
		locations.add(new LocationPointWithText(113, 147, "6428"));
		locations.add(new LocationPointWithText(421, 179, "110054807"));
		locations.add(new LocationPointWithText(513, 155, "15422"));
		locations.add(new LocationPointWithText(431, 179, "4012638628"));
		locations.add(new LocationPointWithText(278, 383, "110025454"));
		locations.add(new LocationPointWithText(382, 203, "13905053"));
		locations.add(new LocationPointWithText(445, 185, "449206232"));
		locations.add(new LocationPointWithText(445, 202, "284810443"));
		locations.add(new LocationPointWithText(43, 164, "20054968"));
		locations.add(new LocationPointWithText(421, 135, "10210301"));
		locations.add(new LocationPointWithText(278, 273, "110015943"));
		locations.add(new LocationPointWithText(432, 159, "822990"));
		locations.add(new LocationPointWithText(114, 260, "410000231579"));
		locations.add(new LocationPointWithText(426, 55, "101715294"));
		locations.add(new LocationPointWithText(425, 110, "8583082"));
		locations.add(new LocationPointWithText(462, 177, "35774"));
		locations.add(new LocationPointWithText(293, 219, "1657928"));
		locations.add(new LocationPointWithText(313, 689, "19065798018"));
		
		Node ret = new Node(HORIZONTAL, 0, GridConstants.A4_WIDTH_PX, 0, GridConstants.A4_HEIGHT_PX);
		ret.addLocations(locations);
		return ret;
	}

	@Before
	public void setUp() throws Exception {
		root = getRootInitializedWithTrainingData();
	}

	@Test
	public void testBuildResult() {
		// root node ->
		assertNotNull(root);
		assertEquals(root.getNumberOfLocations(), 23);
		assertEquals(root.getCutDirection(), CutDirectionType.HORIZONTAL);
		// root node
		
		// upper half of A4 ->
		assertNotNull(root.getSmallerValues());
		assertEquals(root.getSmallerValues().getNumberOfLocations(), 22);
		assertEquals(root.getSmallerValues().getCutDirection(), CutDirectionType.VERTICAL);
		// Rigorous test of upper left quarter
		assertNotNull(root.getSmallerValues().getSmallerValues());
		assertEquals(root.getSmallerValues().getSmallerValues().getCutDirection(), CutDirectionType.HORIZONTAL);
		assertNotNull(root.getSmallerValues().getSmallerValues().getSmallerValues());
		assertEquals(root.getSmallerValues().getSmallerValues().getSmallerValues().getNumberOfLocations(), 2);
		assertEquals(root.getSmallerValues().getSmallerValues().getSmallerValues().getCutDirection(), CutDirectionType.VERTICAL);
		assertNull(root.getSmallerValues().getSmallerValues().getSmallerValues().getSmallerValues());
		assertNull(root.getSmallerValues().getSmallerValues().getSmallerValues().getBiggerValues());
		assertNotNull(root.getSmallerValues().getSmallerValues().getBiggerValues());
		assertEquals(root.getSmallerValues().getSmallerValues().getBiggerValues().getNumberOfLocations(), 5);
		assertEquals(root.getSmallerValues().getSmallerValues().getBiggerValues().getCutDirection(), CutDirectionType.VERTICAL);
		assertNull(root.getSmallerValues().getSmallerValues().getBiggerValues().getSmallerValues());
		assertNull(root.getSmallerValues().getSmallerValues().getBiggerValues().getBiggerValues());
		// not so rigorous test of upper right quarter
		assertNotNull(root.getSmallerValues().getBiggerValues());
		assertEquals(root.getSmallerValues().getBiggerValues().getSmallerValues().getNumberOfLocations(), 15);
		assertEquals(root.getSmallerValues().getBiggerValues().getBiggerValues().getNumberOfLocations(), 0);
		// upper half of A4
		
		// lower half of A4 ->
		assertNotNull(root.getBiggerValues());
		assertEquals(root.getBiggerValues().getNumberOfLocations(), 1);
		assertEquals(root.getBiggerValues().getCutDirection(), CutDirectionType.VERTICAL);
		assertNull(root.getBiggerValues().getSmallerValues());
		assertNull(root.getBiggerValues().getBiggerValues());
		// lower half of A4
	}

	@Test
	public void testMaxDepthWithDepth1() {
		int depthForPointInLowerHalf = root.getMaxDepthForLocation(new LocationPointWithText(313, 689, "123"));
		assertEquals(1, depthForPointInLowerHalf);
	}

	@Test
	public void testMaxDepthWithDepth3() {
		int depthForPointInLowerHalf = root.getMaxDepthForLocation(new LocationPointWithText(382, 203, "123"));
		assertEquals(3, depthForPointInLowerHalf);
	}

	@Test
	public void testDataPointsAtLevelInUpperRight() {
		int dataPoints = root.getDataPointsAtLevelForLocation(new LocationPointWithText(502, 166, "123"), 3);
		assertEquals(15, dataPoints);
	}

	@Test
	public void testDataPointsAtLevelInBottomHalf() {
		int dataPoints = root.getDataPointsAtLevelForLocation(new LocationPointWithText(313, 689, "123"), 1);
		assertEquals(1, dataPoints);
	}

}
