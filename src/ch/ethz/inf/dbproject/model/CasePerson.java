package ch.ethz.inf.dbproject.model;
import java.sql.*;

/**
 * Object that represents a registered in user.
 */
public final class CasePerson {

	private final int PersonID;
	private final String FirstName;
	private final String SurName;
	private final String Reason;
	
	public CasePerson(final int personid, final String firstname, final String surname, final String reason) {
		this.PersonID = personid;
		this.FirstName = firstname;
		this.SurName = surname;
		this.Reason = reason;
	}

	public int getPersonID() {
		return PersonID;
	}
	
	public String getFirstName() {
		return FirstName;
	}

	public String getSurName() {
		return SurName;
	}

	public String getReason() {
		return Reason;
	}


	
}
