package ch.ethz.inf.dbproject.model;
import java.sql.*;

/**
 * Object that represents a registered in user.
 */
public final class PersonNote {

	private final int NoteNr;
	private final int PersonID;
	private final String Username;
	private final String Text;

	
	public PersonNote(final int notenr, final int personid, final String username, final String text) {
		this.NoteNr = notenr;
		this.PersonID = personid;
		this.Username = username;
		this.Text = text;
	}

	public int getPersonID() {
		return PersonID;
	}

	public int getNoteNr() {
		return NoteNr;
	}

	public String getText() {
		return Text;
	}

	public String getUsername() {
		return Username;
	}
	
}
