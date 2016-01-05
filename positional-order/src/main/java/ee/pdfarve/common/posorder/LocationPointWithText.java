package ee.pdfarve.common.posorder;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class LocationPointWithText {
	
	private final int x, y;
	private String text;

	public LocationPointWithText(final int x, final int y, final String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof LocationPointWithText) {
			LocationPointWithText comp = (LocationPointWithText)other;
			if (comp.text != null && comp.text.equals(this.text)) {
				return true;
			}
		}
		return super.equals(other);
	}

}
