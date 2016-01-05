package ee.pdfarve.common.posorder;

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