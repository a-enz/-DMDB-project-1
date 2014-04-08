package ch.ethz.inf.dbproject.model;

/**
 * Object that represents a registered in user.
 */
public final class NoteText {

	private final String Text;

	
	public NoteText(final String text) {
		this.Text = text;
	}

	public String getText() {
		return Text;
	}
	
	
}
