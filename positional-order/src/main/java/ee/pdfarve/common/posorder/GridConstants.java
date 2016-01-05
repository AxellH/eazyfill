package ee.pdfarve.common.posorder;

public interface GridConstants {

	int
	/**
	 * PDFBox library has this number for A4 page size width in pixels
	 */
	A4_WIDTH_PX = 596,

	/**
	 * PDFBox library has this number for A4 page size height in pixels
	 */
	A4_HEIGHT_PX = 838,

	/**
	 * No grid cell is split into smaller cells if it contains less than or
	 * equal to this number of data points
	 */
	MIN_NUMBER_OF_DATA_POINTS_IN_CELL = 2,

	/**
	 * Minimum side length for a cell, so that cells do not become too small
	 */
	MIN_CELL_LENGTH_PX = (A4_WIDTH_PX - 4) / 4;

}
