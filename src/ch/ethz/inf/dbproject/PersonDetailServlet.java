package ch.ethz.inf.dbproject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		
		if (idString == null) {
			this.getServletContext().getRequestDispatcher("/Person").forward(request, response);
		}

		try {

			final Integer id = Integer.parseInt(idString);
			session.setAttribute("id", id.toString());
			
			/*******************************************************
			 * Construct a table to present all properties of a case
			 *******************************************************/
			final BeanTableHelper<Person> table = new BeanTableHelper<Person>(
					"person" 		/* The table html id property */,
					"table" /* The table html class property */,
					Person.class 	/* The class of the objects (rows) that will be displayed */
			);

			// Add columns to the new table

			table.addBeanColumn("PersonID", "PersonID");

			/*
			 * Columns 2 & 3: Some random fields. These should be replaced by i.e. funding progress, or time remaining
			 */
			table.addBeanColumn("First Name", "FirstName");
			table.addBeanColumn("Surname", "SurName");
			table.addBeanColumn("Street", "Street");
			table.addBeanColumn("Birthdate", "BirthDate");
			table.addBeanColumn("Nationality", "Nationality");
			table.addBeanColumn("Bounty", "Bounty");
			table.setVertical(true);
			
			table.addObject(this.dbInterface.getPersonById(id))
			;

			session.setAttribute("persondetailTable", table);
			
			//------------------ Add Notes ---------------------------
			final String action = request.getParameter("action"); //add_comment
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
			
			
			
		} catch (final Exception ex) {
			ex.printStackTrace();
			this.getServletContext().getRequestDispatcher("/Person.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/PersonDetail.jsp").forward(request, response);
	}
}