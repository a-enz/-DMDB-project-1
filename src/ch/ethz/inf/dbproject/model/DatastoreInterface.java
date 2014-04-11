package ch.ethz.inf.dbproject.model;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import ch.ethz.inf.dbproject.database.MySQLConnection;

/**
 * This class should be the interface between the web application
 * and the database. Keeping all the data-access methods here
 * will be very helpful for part 2 of the project.
 */
public final class DatastoreInterface {	
	//@SuppressWarnings("unused")
	private Connection sqlConnection;


	public DatastoreInterface() {
		this.sqlConnection = MySQLConnection.getInstance().getConnection();
	}
	
	public final Person getPersonById(final int id) {
		
		final Statement sqlStatement;
		final Integer PersonID=id;
		Person result = null;
	
		/**
		 * TODO this method should return the case with the given id
		 */
		System.out.println("Enter getPersonByID");
		try{
			sqlStatement = sqlConnection.createStatement();
			final ResultSet rs = sqlStatement.executeQuery("Select PersonID, FirstName, SurName, Street, BirthDate, Nationality, Bounty FROM Person WHERE PersonID=" + PersonID.toString());
			
		      while(rs.next()){
			         //Display values
			         result = new Person(rs.getInt("PersonID"), rs.getString("FirstName"), rs.getString("SurName"), rs.getString("Street"), rs.getDate("BirthDate"), rs.getString("Nationality"), rs.getInt("Bounty"));
		      	}
		      return result;
			
		}catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	public final void insertCaseNote(final int CaseID, final String username, final String text){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("INSERT INTO CaseNote (CaseID, Username, Text) values ( " + CaseID + ", '" + username + "', '" + text + "' )");

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
		

	public final void updateCaseTitle(final int id, final String title){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Title='" + title + "' WHERE CaseNr=" + id);

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseLocation(final int id, final String location){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Location='" + location + "' WHERE CaseNr=" + id);

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseDate(final int id, final String date){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Date='" + date + "' WHERE CaseNr=" + id);

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseStatus(final int id, final String status){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Status='" + status + "' WHERE CaseNr=" + id);

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final Case getCaseById(final int id) {
		
		final Statement sqlStatement;
		final Integer CaseNr=id;
		Case result = null;
	
		/**
		 * TODO this method should return the case with the given id
		 */
		System.out.println("Enter getCaseByID");
		try{
			sqlStatement = sqlConnection.createStatement();
			final ResultSet rs = sqlStatement.executeQuery("Select * FROM Cases WHERE CaseNr=" + CaseNr.toString());
			
		      while(rs.next()){
			         //Display values
		    	  result = new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd"));
			  }
		      return result;
			
		}catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
		}
		
	public final List<Case> getAllCases() {
		try {
			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("Select * FROM Cases");
			final List<Case> cases = new ArrayList<Case>(); 
			while (rs.next()) {
				cases.add(new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd")));
			}
			
			rs.close();
			stmt.close();

			return cases;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	
	public final List<Person> getAllPerson() {
		try {
			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("Select * FROM Person");
			final List<Person> people = new ArrayList<Person>(); 
			while (rs.next()) {
				people.add(new Person(rs.getInt("PersonID"), rs.getString("FirstName"), rs.getString("SurName"), rs.getString("Street"), rs.getDate("BirthDate"), rs.getString("Nationality"), rs.getInt("Bounty")));
			}
			
			rs.close();
			stmt.close();

			return people;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	public final List<CasePerson> GetCasePersonById(final int id){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT c.PersonID, FirstName, SurName, Reason, Role FROM Connected c,Person p WHERE CaseID=" + id +" AND c.PersonID = p.PersonID");
			final List <CasePerson> casepersons = new ArrayList<CasePerson>();
			while(rs.next()){
				casepersons.add(new CasePerson(rs));
			}
			return casepersons;
		}catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
 	
	public final List<Case> getOpenCases(){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases WHERE Status = 'open'");
			final List <Case> cases = new ArrayList<Case>();
			while (rs.next()){
				cases.add(new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd")));
			}
			return cases;
		} catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public final List<Case> getClosedCases(){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases WHERE Status = 'closed'");
			final List <Case> cases = new ArrayList<Case>();
			while (rs.next()){
				cases.add(new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd")));
			}
			return cases;
		} catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public final List<Case> getMostRecentCases(){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases ORDER BY Date DESC");
			final List <Case> cases = new ArrayList<Case>();
			while (rs.next()){
				cases.add(new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd")));
			}
			return cases;
		} catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}

	public final List<Case> getOldestUnsolvedCases(){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases WHERE Status='open' ORDER BY Date ASC");
			final List <Case> cases = new ArrayList<Case>();
			while (rs.next()){
				cases.add(new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd")));
			}
			return cases;
		} catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public final List<Person> getMostWanted() {
		try {
			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("Select PersonID, FirstName, SurName, Street, BirthDate, Nationality, Bounty FROM Person ORDER BY Bounty DESC");
			final List<Person> people = new ArrayList<Person>(); 
			while (rs.next()) {
				people.add(new Person(rs.getInt("PersonID"), rs.getString("FirstName"), rs.getString("SurName"), rs.getString("Street"), rs.getDate("BirthDate"), rs.getString("Nationality"), rs.getInt("Bounty")));
			}
			
			rs.close();
			stmt.close();

			return people;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	public final List<NoteText> getCaseNoteById(int id){
		try{
			Integer ID = id;
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs= stmt.executeQuery("SELECT Text, NoteNr FROM CaseNote WHERE CaseID=" + ID.toString());
			final List<NoteText> note = new ArrayList<NoteText>();
			while (rs.next()){
				note.add(new NoteText(rs));
			}
			rs.close();
			stmt.close();
			
			return note;
			
		}catch (final SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public final void removeCaseNote(String id){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate("DELETE FROM CaseNote WHERE NoteNr = " + id);
			System.out.println("DELETE FROM CaseNote WHERE NoteNr = " + id);
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final List<PersonNote> getAllPersonNote() {
		try {
			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("Select * FROM PersonNote");
			final List<PersonNote> note = new ArrayList<PersonNote>(); 
			while (rs.next()) {
				note.add(new PersonNote(rs.getInt("NoteNr"), rs.getInt("PersonID"), rs.getString("Username"), rs.getString("Text")));
			}
			
			rs.close();
			stmt.close();

			return note;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	public final List<NoteText> getPersonNoteById(int id) {
		try {
			Integer iid = id;
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT Text,NoteNr FROM PersonNote WHERE PersonID = " + iid.toString());
			final List<NoteText> note = new ArrayList<NoteText>(); 
			while (rs.next()) {
				note.add(new NoteText(rs));
			}
			
			rs.close();
			stmt.close();

			return note;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}
	
	public final void insertPersonNote(int PersonID, String username, String Text){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate("INSERT INTO PersonNote (PersonID, Username, Text) values ( " + PersonID + ", '" + username + "', '" + Text + "' )");
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void removePersonNote(String NoteNr){
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate("DELETE FROM PersonNote WHERE NoteNr = " + NoteNr);
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final User getUser(String username, String passwort) {
		try {
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE Username = '" + username + "' AND Passwort = '" + passwort + "'");
			User user = null;
			if (rs.next()) {
				user = new User(rs.getString("Username"), rs.getString("Passwort"), rs.getString("Name"));
			}
			rs.close();
			stmt.close();

			return user;
			
		} catch (final SQLException ex) {			
			ex.printStackTrace();
			return null;			
		}
	}

	public List<Case> searchByName(String name) {
		List<Case> res = new ArrayList<Case>();
		try {
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases WHERE Title like '" + name + "'");
			//System.out.print((rs.isFirst() == rs.isLast() ? "Empty " : "NotEmpty ") + "SELECT * FROM Cases WHERE Title = '" + name + "'\n");
			while (rs.next()) {
				res.add(new Case(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Case> searchByCategory(String category) {
		List<Case> res = new ArrayList<Case>();
		String query = "SELECT ca.CaseNr, ca.Title, ca.Date, ca.Location, ca.Status, ca.DateCon, DateEnd FROM Cases ca, ContainedIn co WHERE ca.CaseNr =  co.CaseID AND CatName like '" + category + "'";
		try {
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				res.add(new Case(rs));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean addPerson(String firstName, String surName, String street, String birthDate, String nationality, String bounty)
	{
		String fields = "(FirstName, SurName";
		String values = "values('" + firstName + "', '" + surName + "'";
		String insert;
		
		Statement stmt;

		if(firstName != null && firstName != "" && surName != null && surName != null) {	//valid input as firstname and surname are mandatory and bounty shouldnt be sth silly
			if(street != null) {fields = fields + ", Street"; values = values + ", " + (street == "" ? "NULL" : "'" + street + "'");}
			if(birthDate != null) {fields = fields + ", BirthDate"; values = values + ", " + (birthDate == "" ? "NULL" : "'" + birthDate + "'");}
			if(nationality != null) {fields = fields + ", Nationality"; values = values + ", " + (nationality == "" ? "NULL" : "'" + nationality + "'");}
			if(bounty != null) {fields = fields + ", Bounty"; values = values + ", " + (bounty == "" ? "NULL" : "'" + bounty + "'" );}
			fields = fields + ") ";
			values = values + ") ";
			insert = "INSERT INTO Person " + fields + values;

			try {
				stmt = this.sqlConnection.createStatement();
				stmt.executeUpdate(insert);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		else return false;	//invalid input

	}
	
	
	
	//TODO Implement all missing data access methods


}
