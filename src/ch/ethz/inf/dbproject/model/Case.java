package ch.ethz.inf.dbproject.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public final class Case {

	private final int CaseNr;
	private final String Title;
	private Date Date;
	private final String Location;
	private final String Status;
	private Date DateCon;
	private Date DateEnd;
	
	/**
	 * Construct a new case.
	 * 
	 * @param description		The name of the case
	 */
	public Case(final int casenr, final String title, final Date date, final String location, final String status, final Date datecon, final Date dateend) {
		this.CaseNr = casenr;
		this.Title = title;
		this.Date = date;
		this.Location = location;
		this.Status = status;
		this.DateCon = datecon;
		this.DateEnd = dateend;
	}
	
	public Case(final int casenr, final String title, final String date, final String location, final String status, final String datecon, final String dateend) {
		this.CaseNr = casenr;
		this.Title = title;
		try {this.Date = java.sql.Date.valueOf(date);}
		catch (Exception e) {this.Date = null;}
		this.Location = location;
		this.Status = status;
		try {this.DateCon = java.sql.Date.valueOf(datecon);}
		catch (Exception e) {this.DateCon = null;}
		try {this.DateEnd = java.sql.Date.valueOf(dateend);}
		catch (Exception e) {this.DateEnd = null;}
	}
	
	public Case(final ResultSet rs) throws SQLException {
		this.CaseNr = rs.getInt("CaseNr");
		this.Title = rs.getString("Title");
		this.Date = rs.getDate("Date");
		this.Location = rs.getString("Location");
		this.Status = rs.getString("Status");
		this.DateCon = rs.getDate("DateCon");
		this.DateEnd = rs.getDate("DateEnd");
	}

	public int getCaseNr() {
		return CaseNr;
	}

	public String getTitle() {
		return Title;
	}

	public Date getDate() {
		return Date;
	}

	public String getLocation() {
		return Location;
	}

	public String getStatus() {
		return Status;
	}

	public Date getDateCon() {
		return DateCon;
	}

	public Date getDateEnd() {
		return DateEnd;
	}

	/**
	 * HINT: In eclipse, use Alt + Shirt + S menu and choose:
	 * "Generate Getters and Setters to auto-magically generate
	 * the getters. 
	 */
	
	
	
}