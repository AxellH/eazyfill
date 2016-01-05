package ee.pdfarve.common.geom;

public enum CutDirectionType {
	
	/**
	 * 
	 * |-----|
	 * |  x  |
	 * |  x  |
	 * |  x  |
	 * |  x  |
	 * |  x  |
	 * |_____|
	 * 
	 * Cutting along the vertical line
	 * 
	 */
	VERTICAL,
	
	/**
	 * 
	 * |-----|
	 * |     |
	 * |     |
	 * |xxxxx|
	 * |     |
	 * |     |
	 * |_____|
	 * 
	 * Cutting along the horizontal line
	 * 
	 */
	HORIZONTAL
	
}