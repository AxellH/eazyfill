package ee.pdfarve.common.posorder;

import java.util.Comparator;

public class CandidatesComparator implements Comparator<LocationPointWithText> {
	
	private Node root;

	/**
	 * 
	 * @param root k-d tree root node that has been trained
	 */
	public CandidatesComparator(final Node root) {
		this.root = root;
	}

	@Override
	public int compare(final LocationPointWithText o1, final LocationPointWithText o2) {
		int depth1 = root.getMaxDepthForLocation(o1);
		int depth2 = root.getMaxDepthForLocation(o2);
		int minDepth = Math.min(depth1, depth2);
		int frequency1 = root.getDataPointsAtLevelForLocation(o1, minDepth);
		int frequency2 = root.getDataPointsAtLevelForLocation(o2, minDepth);
		return frequency2 - frequency1;
	}

}
