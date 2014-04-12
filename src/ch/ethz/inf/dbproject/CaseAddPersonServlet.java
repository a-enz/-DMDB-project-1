package ch.ethz.inf.dbproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.Case;
import ch.ethz.inf.dbproject.model.Person;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;

/**
 * Servlet implementation class of Case list page
 */
@WebServlet(description = "The home page of the project", urlPatterns = { "/CaseAddPerson" })
public final class CaseAddPersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseAddPersonServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {

		final HttpSession session = request.getSession(true);

		/*******************************************************
		 * Construct a table to present all our results
		 *******************************************************/
		final BeanTableHelper<Person> table = new BeanTableHelper<Person>("",
				"table" 		/* The table html id property */,
				Person.class 	/* The class of the objects (rows) that will bedisplayed */
		);

		// Add columns to the new table

		/*
		 * Column 1: The name of the item (This will probably have to be changed)
		 */
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

		/*
		 * Column 4: This is a special column. It adds a link to view the
		 * Project. We need to pass the case identifier to the url. */
		table.addCheckColumn("",	/* The header. We will leave it empty */
				"Add Person", 	/* What should be displayed in every row */
				"person",
				"PersonID"); 			/* For every case displayed, the ID will be retrieved and will be attached to the url base above */

		table.addEmptyTextColumn("Add a reason", "reason", "PersonID");
		
		// Pass the table to the session. This will allow the respective jsp page to display the table.
		session.setAttribute("person", table);


		table.addObjects(this.dbInterface.getAllPerson());


		// Finally, proceed to the Projects.jsp page which will render the Projects
		this.getServletContext().getRequestDispatcher("/CaseAddPerson.jsp").forward(request, response);
	}
}