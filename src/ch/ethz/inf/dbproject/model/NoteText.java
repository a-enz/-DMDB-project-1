package ch.ethz.inf.dbproject.model;

import java.sql.*;

/**
 * Object that represents a registered in user.
 */
public final class NoteText {

	private final String Text;
	private final int NoteNr;

	
	public NoteText(final String text, final int notenr) {
		this.Text = text;
		this.NoteNr = notenr;
	}
	
	public NoteText(final ResultSet rs) throws SQLException{
		this.Text = rs.getString("Text");
		this.NoteNr = rs.getInt("NoteNr");
	}

	public String getText() {
		return Text;
	}
	
	public int getNoteNr(){
		return NoteNr;
	}
	
}
