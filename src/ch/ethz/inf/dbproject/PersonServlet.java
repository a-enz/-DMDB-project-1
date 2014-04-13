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
@WebServlet(description = "The home page of the project", urlPatterns = { "/Person" })
public final class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {

		final HttpSession session = request.getSession(true);
		
		/*******************************************************
		 * Calculate the Bounty for each Person
		 *******************************************************/		
		
		
		
		
		/*******************************************************
		 * Construct a table to present all our results
		 *******************************************************/
		final BeanTableHelper<Person> table = new BeanTableHelper<Person>("",
				"table" 		/* The table html id property */,
				Person.class 	/* The class of the objects (rows) that will bedisplayed */
		);
		
		final BeanTableHelper<Person> deletedPerson = new BeanTableHelper<Person>("",
				"table" 		/* The table html id property */,
				Person.class 	/* The class of the objects (rows) that will bedisplayed */
		);

		table.addBeanColumn("PersonID", "PersonID");
		table.addBeanColumn("First Name", "FirstName");
		table.addBeanColumn("Surname", "SurName");
		table.addBeanColumn("Street", "Street");
		table.addBeanColumn("Birthdate", "BirthDate");
		table.addBeanColumn("Nationality", "Nationality");
		table.addBeanColumn("Bounty", "Bounty");
		
		deletedPerson.addBeanColumn("PersonID", "PersonID");
		deletedPerson.addBeanColumn("First Name", "FirstName");
		deletedPerson.addBeanColumn("Surname", "SurName");
		deletedPerson.addBeanColumn("Street", "Street");
		deletedPerson.addBeanColumn("Birthdate", "BirthDate");
		deletedPerson.addBeanColumn("Nationality", "Nationality");
		deletedPerson.addBeanColumn("Bounty", "Bounty");

		/*
		 * Column 4: This is a special column. It adds a link to view the
		 * Project. We need to pass the case identifier to the url.
		 */
		table.addLinkColumn("",	/* The header. We will leave it empty */
				"View Person", 	/* What should be displayed in every row */
				"PersonDetail?id=", 	/* This is the base url. The final url will be composed from the concatenation of this and the parameter below */
				"PersonID"); 			/* For every case displayed, the ID will be retrieved and will be attached to the url base above */

		// Pass the table to the session. This will allow the respective jsp page to display the table.
		session.setAttribute("person", table);

		// The filter parameter defines what to show on the Projects page
		final String filter = request.getParameter("filter");
		final String category = request.getParameter("category");
		final String action = request.getParameter("action");
		
		if(action != null && action.equals("delete")) {
			Person person = dbInterface.getPersonById(Integer.parseInt(request.getParameter("id")));
			if(person != null){
				deletedPerson.addObject(person);
				session.setAttribute("deletedPerson", deletedPerson);
				dbInterface.deletePerson(request.getParameter("id"));
			}
		}
		if (filter == null ) {
			// If no filter is specified, then we display all the cases!
			table.addObjects(this.dbInterface.getAllPerson());

		} else if (filter.equals("mostWanted")) {

			table.addObjects(this.dbInterface.getMostWanted());
			
		} 			
		else {
			throw new RuntimeException("Code should not be reachable!");
		}

		// Finally, proceed to the Projects.jsp page which will render the Projects
		this.getServletContext().getRequestDispatcher("/Person.jsp").forward(request, response);
	}
}