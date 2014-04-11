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
	private final String Role;
	
	public CasePerson(ResultSet rs) throws SQLException{
		this.PersonID = rs.getInt("PersonID");
		this.FirstName = rs.getString("FirstName");
		this.SurName = rs.getString("SurName");
		this.Reason = rs.getString("Reason");
		this.Role = rs.getString("Role");
	}
	
	public CasePerson(final int personid, final String firstname, final String surname, final String reason, final String role) {
		this.PersonID = personid;
		this.FirstName = firstname;
		this.SurName = surname;
		this.Reason = reason;
		this.Role = role;
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
	
	public String getRole() {
		return Role;
	}


	
}
