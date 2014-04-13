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
	
		try{
			sqlStatement = sqlConnection.createStatement();
			final ResultSet rs = sqlStatement.executeQuery("Select PersonID, FirstName, SurName, Street, BirthDate, Nationality, Bounty FROM Person WHERE PersonID=" + PersonID.toString());
			
		      while(rs.next()){
			         //Display values
			         result = new Person(rs.getInt("PersonID"), rs.getString("FirstName"), rs.getString("SurName"), rs.getString("Street"), rs.getDate("BirthDate"), rs.getString("Nationality"), rs.getInt("Bounty"));
		      }
		      sqlStatement.close();
		      rs.close();
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
			sqlStatement.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
		

	public final void updateCaseTitle(final int id, final String title){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Title='" + title + "' WHERE CaseNr=" + id);
			sqlStatement.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseLocation(final int id, final String location){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Location='" + location + "' WHERE CaseNr=" + id);
			sqlStatement.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseDate(final int id, final String date){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Date='" + date + "' WHERE CaseNr=" + id);
			sqlStatement.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updatePersonNote(final String NoteNr, final String NoteText){
		try{
			final Statement stmt = sqlConnection.createStatement();
			stmt.executeUpdate("UPDATE PersonNote SET Text='" + NoteText + "' WHERE NoteNr=" + NoteNr);
			stmt.close();

		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final void updateCaseStatus(final int id, final String status){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();

			sqlStatement.executeUpdate("UPDATE Cases SET Status='" + status + "' WHERE CaseNr=" + id);

			if (status.equals("close")){
				sqlStatement.executeUpdate("UPDATE Connected SET Role = 'perpetrator' WHERE CaseID = " + id + " AND Role = 'suspect'");
			}
			else if (status.equals("open")){
				sqlStatement.executeUpdate("UPDATE Connected SET Role = 'suspect' WHERE CaseID = " + id + " AND Role = 'perpetrator'");
			}
			sqlStatement.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public final void addPersonToCase(final int id, final String personid, final String reason){
		try{
			final Statement sqlStatement = sqlConnection.createStatement();
			sqlStatement.executeUpdate("INSERT INTO Connected (CaseID, PersonID, Role) VALUES (" + id + "," + personid + ", 'perpetrates')");
			
			ResultSet rs = sqlStatement.executeQuery("SELECT * FROM Connected WHERE CaseID = "+ id +" AND PersonID = " + personid);
			if(rs.next()){
				sqlStatement.executeUpdate("UPDATE Connected SET Reason = '" + reason + "' WHERE CaseID = " + id + " AND PersonID = " + personid);
			}else{
				sqlStatement.executeUpdate("INSERT INTO Connected (CaseID, PersonID, Role, Reason) VALUES (" + id + "," + personid + ", 'suspects', '" + reason + "')");
			}
			
			sqlStatement.close();
			rs.close();
			
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final boolean removePersonCase(final int caseID, final String personID){
		try{
			final Statement stmt = sqlConnection.createStatement();
			System.out.println("about to remove personID" + personID + "connected to CaseID" + caseID);
			int i = stmt.executeUpdate("DELETE FROM Connected WHERE CaseID = '" + caseID + "' AND PersonID = '" + personID + "' AND Role != 'perpetrator'");
			System.out.println(i);
			if(i>0) return true;
			else return false;
			
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("remove person failed");
			return false;
		}
	}
	
	public final Case getCaseById(final int id) {
		
		final Statement sqlStatement;
		final Integer CaseNr=id;
		Case result = null;

		System.out.println("Enter getCaseByID");
		try{
			sqlStatement = sqlConnection.createStatement();
			final ResultSet rs = sqlStatement.executeQuery("Select * FROM Cases WHERE CaseNr=" + CaseNr.toString());
			
		      while(rs.next()){
			         //Display values
		    	  result = new Case(rs.getInt("CaseNr"), rs.getString("Title"), rs.getDate("Date"), rs.getString("Location"), rs.getString("Status"), rs.getDate("DateCon"), rs.getDate("DateEnd"));
			  }
		      rs.close();
		      sqlStatement.close();
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

	public final List<Case> getCasesByCategory(final String Category) {
		try {
			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("Select * FROM Cases, ContainIn "); //TODO: Implement this
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
			stmt.close();
			rs.close();
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
			stmt.close();
			rs.close();
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
			stmt.close();
			rs.close();
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
			stmt.close();
			rs.close();

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
			stmt.close();
			rs.close();
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
			stmt.close();
		}catch (final SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public final List<Case> getInvolvedCases(int id){
		List<Case> res = new ArrayList<Case>();
		String query = "SELECT ca.CaseNr, ca.Title, ca.Date, ca.Location, ca.Status, ca.DateCon, DateEnd " +
				"FROM Cases ca, Connected co " +
				"WHERE ca.CaseNr =  co.CaseID AND PersonID = '" + id + "'";
		try{
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				res.add(new Case(rs));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return res;
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
	
	public final void insertUser(String username, String password, String realname) {
		try{
			final Statement sqlStatement = sqlConnection.createStatement();
			
			sqlStatement.executeUpdate("INSERT INTO User (Username, Passwort, Name) values ( '" + username + "', '" + password + "', '" + realname + "' )");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final boolean isRegistered(String username){
		try{
			final Statement stmt = sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * " +
													"FROM User " +
													"WHERE Username = '" + username + "'");
			
			if(rs.next()){
				rs.close();
				stmt.close();
				return true;
			}
			else{
				rs.close();
				stmt.close();
				return false;
			}

			
		} catch (SQLException e){
			e.printStackTrace();
			return true;
		}
	}

	public List<Case> searchByName(String name) {
		List<Case> res = new ArrayList<Case>();
		try {
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM Cases WHERE Title like '" + name + "'");
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
	
	/**************************
	 * SEARCHES IN PERSON:
	 ************************/
	
	public List<Person> searchPersonByName(String name) {
		List<Person> res = new ArrayList<Person>();
		String query = "SELECT p.PersonID, p.FirstName, p.SurName, p.Street, p.BirthDate, p.Nationality, p.Bounty " +
				"FROM Person p " +
				"WHERE p.FirstName like '" + name + "' OR p.SurName like '" +name + "'";
		try {			
			final Statement stmt = this.sqlConnection.createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				res.add(new Person(rs));
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
			if(street != null) {fields = fields + ", Street"; values = values + ", " + (street.equals("") ? "NULL" : "'" + street + "'");}
			if(birthDate != null) {fields = fields + ", BirthDate"; values = values + ", " + (birthDate.equals("") ? "NULL" : "'" + birthDate + "'");}
			if(nationality != null) {fields = fields + ", Nationality"; values = values + ", " + (nationality.equals("") ? "NULL" : "'" + nationality + "'");}
			if(bounty != null) {fields = fields + ", Bounty"; values = values + ", " + (bounty.equals("") ? "NULL" : "'" + bounty + "'" );}
			fields = fields + ") ";
			values = values + ") ";
			insert = "INSERT INTO Person " + fields + values;

			try {
				stmt = this.sqlConnection.createStatement();
				stmt.executeUpdate(insert);
				stmt.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		else return false;	//invalid input

	}

	public boolean deletePerson(String id) {
		String delete = "DELETE FROM Person WHERE PersonID = " + id;
		Statement stmt;
		try {
			stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate(delete);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updatePerson(String id, String firstName, String surName, String street, String birthDate, String nationality, String bounty) {
		String update = "UPDATE Person SET PersonID = '" + id + "'";
		String where = " WHERE PersonID = " + id;
		
		Statement stmt;
		
		if(firstName != null && !firstName.equals("")) update = update + ", FirstName = '" + firstName + "'";
		if(surName != null && !surName.equals("")) update = update + ", SurName = '" + surName + "'";
		if(street != null && !street.equals("")) update = update + ", Street = '" + street + "'";
		if(birthDate != null && !birthDate.equals("")) update = update + ", BirthDate = '" + birthDate + "'";
		if(nationality != null && !nationality.equals("")) update = update + ", Nationality = '" + nationality + "'";
		if(bounty != null && !bounty.equals("")) update = update + ", Bounty = '" + bounty + "'";
		
		try {
			//System.out.println(update + where);
			stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate(update + where);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertCase(String title, String date, String location, String dateCon, String dateEnd) {
		String insert = "INSERT INTO Cases (Title, Date";
		String values = " values(";
		
		Statement stmt;

		if(title == null || title.equals("") || date == null || date.equals("")) {System.out.println("fuckedup");return false;}		//invalid input
		else values = values + "'" + title + "', '" + date + "'";
		
		if(location != null && !location.equals("")) {insert = insert + ", Location"; values = values + ", '" + location + "'";}
		if(dateCon != null && !dateCon.equals("")) {insert = insert + ", DateCon"; values = values + ", '" + dateCon  + "'";}
		if(dateEnd != null && !dateEnd.equals("")) {insert = insert + ", DateEnd"; values = values + ", '" + dateEnd  + "'";}
		
		values = values + ")";
		insert = insert + ")";
		
		try {
			//System.out.println(insert + values);
			stmt = this.sqlConnection.createStatement();
			stmt.executeUpdate(insert + values);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
