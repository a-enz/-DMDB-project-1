package ch.ethz.inf.dbproject;

import java.io.IOException;

import java.util.List;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.Conviction;
import ch.ethz.inf.dbproject.model.Comment;
import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.Case;
import ch.ethz.inf.dbproject.model.CasePerson;
import ch.ethz.inf.dbproject.model.NoteText;
import ch.ethz.inf.dbproject.model.Person;
import ch.ethz.inf.dbproject.util.UserManagement;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;

@WebServlet(description = "The add person page", urlPatterns = { "/AddPerson" })
public final class AddPersonServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPersonServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession(true);
		
		final BeanTableHelper<Person> table = new BeanTableHelper<Person>(
				"person" 		/* The table html id property */,
				"table" /* The table html class property */,
				Person.class 	/* The class of the objects (rows) that will be displayed */
		);
		
		session.setAttribute("valid_input", true);
		session.setAttribute("added", null);
		
		final String firstName = request.getParameter("FirstName");
		final String surName = request.getParameter("SurName");
		final String street = request.getParameter("Street");
		final String birthDate = request.getParameter("BirthDate");
		final String nationality = request.getParameter("Nationality");
		final String bounty = request.getParameter("Bounty");
		
		if(dbInterface.addPerson(firstName, surName, street, birthDate, nationality, bounty)) {
			session.setAttribute("valid_input", true);
			session.setAttribute("added", true);
			session.setAttribute("personinfo", printInputHtml(firstName, surName, street, birthDate, nationality, bounty));
		}
		else {
			session.setAttribute("valid_input", false);
		}
		
		showInput(firstName, surName, street, birthDate, nationality, bounty);
		
		
		//System.out.println((request.getServletContext() == null) ? "Context Null" : "Context Not null");
        this.getServletContext().getRequestDispatcher("/AddPerson.jsp").forward(request, response);	
	}
	
	public String printInputHtml(String firstName, String surName, String street, String birthDate, String nationality, String bounty){
		return "Firstname: " + firstName + "<br/>" + "Surname: " + surName + "<br/>" + "Street: " + street + "<br/>" + "Birthdate: " + birthDate + "<br/>" + "Nationality: " +nationality + "<br/>" + "Bounty: " + bounty +"<br/>";
	}
	
	public void showInput(String firstName, String surName, String street, String birthDate, String nationality, String bounty){
		System.out.println("----------------------------");
		System.out.println("FirstName:\t" + firstName);
		System.out.println("SurName:\t" + surName);
		System.out.println("Street:\t\t" + street);
		System.out.println("BirthDate:\t" + birthDate);
		System.out.println("Nationality:\t" + nationality);
		System.out.println("Bounty:\t\t" + bounty);
		System.out.println("----------------------------");
	}
	
	
}
