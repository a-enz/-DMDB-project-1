package ch.ethz.inf.dbproject;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.Case;
import ch.ethz.inf.dbproject.model.Conviction;
import ch.ethz.inf.dbproject.model.Comment;
import ch.ethz.inf.dbproject.model.PersonNote;
import ch.ethz.inf.dbproject.model.NoteText;
import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.Person;
import ch.ethz.inf.dbproject.util.UserManagement;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;

/**
 * Servlet implementation class of Case Details Page
 */
@WebServlet(description = "Displays a specific case.", urlPatterns = { "/PersonDetail" })
public final class PersonDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession(true);

		final String idString = request.getParameter("id");
		
		session.setAttribute("edited", false);

		
		if (idString == null) {
			this.getServletContext().getRequestDispatcher("/Person").forward(request, response);
		}

		try {

			final Integer id = Integer.parseInt(idString);
			session.setAttribute("id", id.toString());
			
			final String action = request.getParameter("action");	//can be null, edit, add_comment
			
			//________SHOW_EDITED_MESSAGE____________
			if(action != null) {    //entries have been edited
				if(action.equals("edit")){
					boolean succ = dbInterface.updatePerson(
							request.getParameter("id"), 
							request.getParameter("firstname"), 
							request.getParameter("surname"), 
							request.getParameter("street"), 
							request.getParameter("birthdate"), 
							request.getParameter("nationality"), 
							request.getParameter("bounty"));
					//System.out.print("Succesfully edited: " + succ);
				}
				session.setAttribute("edited", true);
			}
			/*******************************************************
			 * Construct a table to present all properties of a person
			 *******************************************************/
			final BeanTableHelper<Person> table = new BeanTableHelper<Person>(
					"person" 		/* The table html id property */,
					"table" /* The table html class property */,
					Person.class 	/* The class of the objects (rows) that will be displayed */
			);

			table.addBeanColumn("PersonID", "PersonID");
			table.addBeanColumn("First Name", "FirstName");
			table.addBeanColumn("Surname", "SurName");
			table.addBeanColumn("Street", "Street");
			table.addBeanColumn("Birthdate", "BirthDate");
			table.addBeanColumn("Nationality", "Nationality");
			table.addBeanColumn("Bounty", "Bounty");
			table.setVertical(true);
			
			table.addObject(this.dbInterface.getPersonById(id));

			session.setAttribute("persondetailTable", table);
			
			//------------------ Add Notes ---------------------------
			final String username = request.getParameter("user_name"); 
			final String text = request.getParameter("comment");
			
			
			
			if (action != null && action.equals("add_comment")){
				System.out.println("Insert a comment");
				dbInterface.insertPersonNote(id, username, text);
			}
			else if (action !=null && action.equals("remove_note")){
				System.out.println("remove a comment");
				String NoteNr = request.getParameter("notenr");
				dbInterface.removePersonNote(NoteNr);
			}
			
			
			//------------------ Show Notes------------------------------
			
			final BeanTableHelper <NoteText> notetable = new BeanTableHelper<NoteText>(
				"table",
				"table",
				NoteText.class
			);
			
			notetable.addBeanColumn("Note", "Text");
			notetable.addLinkColumn("",
					"Remove Note",
					"PersonDetail?id="+ id +"&action=remove_note&notenr=",
					"NoteNr");
			
			notetable.addObjects(this.dbInterface.getPersonNoteById(id));

			
			session.setAttribute("personnoteTable", notetable);
			
			
			
			//______SHOW INVOLVED CASES____________
			
			final BeanTableHelper <Case> casetable = new BeanTableHelper<Case>(
				"table",
				"table",
				Case.class
			);
			
			casetable.addBeanColumn("CaseNr", "CaseNr");
			casetable.addBeanColumn("Title", "Title");
			casetable.addBeanColumn("Date", "Date");
			casetable.addBeanColumn("Location", "Location");
			casetable.addBeanColumn("Status", "Status");
			casetable.addBeanColumn("Conviction Date", "DateCon");
			casetable.addBeanColumn("DateEnd", "DateEnd");
			
			casetable.addLinkColumn("",
					"View Details", 
					"Case?id=", 
					"CaseNr");
		
			casetable.addObjects(this.dbInterface.getInvolvedCases(id));
			
			session.setAttribute("involvedCasesTable", casetable);
			
			
		} catch (final Exception ex) {
			ex.printStackTrace();
			this.getServletContext().getRequestDispatcher("/Person.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/PersonDetail.jsp").forward(request, response);
	}
}