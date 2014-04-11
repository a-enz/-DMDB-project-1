package ch.ethz.inf.dbproject.model;
import java.sql.*;

/**
 * Object that represents a registered in user.
 */
public final class Person {

	private final int PersonID;
	private final String FirstName;
	private final String SurName;
	private final String Street;
	private final Date BirthDate;
	private final String Nationality;
	private final int Bounty;
	
	public Person(final int personid, final String firstname, final String surname, final String street, final Date birthdate, final String nationality, final int bounty) {
		this.PersonID = personid;
		this.FirstName = firstname;
		this.SurName = surname;
		this.BirthDate = birthdate;
		this.Street = street;
		this.Nationality = nationality;
		this.Bounty = bounty;
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

	public String getStreet() {
		return Street;
	}
	
	public Date getBirthDate(){
		return BirthDate;
	}

	public String getNationality() {
		return Nationality;
	}

	public int getBounty() {
		return Bounty;
	}
	
	
}
