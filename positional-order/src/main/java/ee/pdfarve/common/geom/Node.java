package ee.pdfarve.common.geom;

import static ee.pdfarve.common.geom.CutDirectionType.HORIZONTAL;
import static ee.pdfarve.common.geom.CutDirectionType.VERTICAL;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private Node smallerValues, biggerValues;

	private final CutDirectionType cutDirection;

	private List<LocationPointWithText> locations = new ArrayList<LocationPointWithText>();

	private final int minX, maxX, minY, maxY;

	public Node(final CutDirectionType cutDirection, final int minX, final int maxX, final int minY, final int maxY) {
		this.cutDirection = cutDirection;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public Node getSmallerValues() {
		return smallerValues;
	}

	public void setSmallerValues(final Node smallerValues) {
		this.smallerValues = smallerValues;
	}

	public Node getBiggerValues() {
		return biggerValues;
	}

	public void setBiggerValues(final Node biggerValues) {
		this.biggerValues = biggerValues;
	}

	public int getNumberOfLocations() {
		return locations.size();
	}

	public CutDirectionType getCutDirection() {
		return cutDirection;
	}

	public void addLocation(final LocationPointWithText point) {
		this.locations.add(point);
	}

	public List<LocationPointWithText> getLocations() {
		return locations;
	}

	public void addLocations(final List<LocationPointWithText> locations) {
		this.locations.addAll(locations);
	}

	public void split() {
		if (getLocations().size() <= Dimensions.MIN_NUMBER_OF_DATA_POINTS_IN_CELL) {
			return;
		}
		if ((getMaxX() - getMinX()) < 2 * Dimensions.MIN_CELL_LENGTH) {
			return;
		}
		if ((getMaxY() - getMinY()) < 2 * Dimensions.MIN_CELL_LENGTH) {
			return;
		}
		int cutline;
		switch (getCutDirection()) {
		case VERTICAL:
			cutline = (getMinX() + getMaxX()) / 2;
			setSmallerValues(new Node(HORIZONTAL, getMinX(), cutline - 1, getMinY(), getMaxY()));
			setBiggerValues(new Node(HORIZONTAL, cutline, getMaxX(), getMinY(), getMaxY()));
			for (LocationPointWithText point : getLocations()) {
				if (point.getX() < cutline) {
					getSmallerValues().addLocation(point);
				} else {
					getBiggerValues().addLocation(point);
				}
			}
			break;
		case HORIZONTAL:
			cutline = (getMinY() + getMaxY()) / 2;
			setSmallerValues(new Node(VERTICAL, getMinX(), getMaxX(), getMinY(), cutline - 1));
			setBiggerValues(new Node(VERTICAL, getMinX(), getMaxX(), cutline, getMaxY()));
			for (LocationPointWithText point : getLocations()) {
				if (point.getY() < cutline) {
					getSmallerValues().addLocation(point);
				} else {
					getBiggerValues().addLocation(point);
				}
			}
			break;
		default:
			throw new IllegalStateException("Unknown cut direction: " + getCutDirection());
		}
		getSmallerValues().split();
		getBiggerValues().split();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getStringRepresentation(""));
		return sb.toString();
	}

	private String getStringRepresentation(final String prefixWhitespace) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefixWhitespace).append('+').append(cutDirection);
		sb.append('(').append("x:").append(minX).append(',').append(maxX).append(';');
		sb.append("y:").append(minY).append(',').append(maxY);
		sb.append(')').append(' ').append(getNumberOfLocations());
		if (this.smallerValues != null) {
			sb.append('\n');
			sb.append(smallerValues.getStringRepresentation(prefixWhitespace + "\t"));
		}
		if (this.biggerValues != null) {
			sb.append('\n');
			sb.append(biggerValues.getStringRepresentation(prefixWhitespace + "\t"));
		}
		return sb.toString();
	}

}
