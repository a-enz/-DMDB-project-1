package ch.ethz.inf.dbproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.Case;
import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.Person;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;

/**
 * Servlet implementation class Search
 */
@WebServlet(description = "The search page for cases", urlPatterns = { "/Search" })
public final class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession(true);
		session.setAttribute("search", false);
		/*******************************************************
		 * Construct a table to present all our case search results
		 *******************************************************/
		final BeanTableHelper<Case> casetable = new BeanTableHelper<Case>(
				"cases" 		/* The table html id property */,
				"table" /* The table html class property */,
				Case.class 	/* The class of the objects (rows) that will bedisplayed */
		);

		casetable.addBeanColumn("CaseNr", "CaseNr");
		casetable.addBeanColumn("Title", "Title");
		casetable.addBeanColumn("Date", "Date");
		casetable.addBeanColumn("Location", "Location");
		casetable.addBeanColumn("Status", "Status");
		casetable.addBeanColumn("DateCon", "DateCon");
		casetable.addBeanColumn("DateEnd", "DateEnd");

		/*
		 * Column 4: This is a special column. It adds a link to view the
		 * Case. We need to pass the case identifier to the url.
		 */
		casetable.addLinkColumn(""	/* The header. We will leave it empty */,
				"View Case" 	/* What should be displayed in every row */,
				"Case?id=" 	/* This is the base url. The final url will be composed from the concatenation of this and the parameter below */, 
				"CaseNr" 			/* For every case displayed, the ID will be retrieved and will be attached to the url base above */);
		
		/*******************************************************
		 * Construct a table to present all our person search results
		 *******************************************************/
		final BeanTableHelper<Person> persontable = new BeanTableHelper<Person>(
				"person" 		/* The table html id property */,
				"table" /* The table html class property */,
				Person.class 	/* The class of the objects (rows) that will bedisplayed */
		);

		persontable.addBeanColumn("PersonID", "PersonID");
		persontable.addBeanColumn("FirstName", "FirstName");
		persontable.addBeanColumn("SurName", "SurName");
		persontable.addBeanColumn("Street", "Street");
		persontable.addBeanColumn("BirthDate", "BirthDate");
		persontable.addBeanColumn("Nationality", "Nationality");
		persontable.addBeanColumn("Bounty", "Bounty");

		/*
		 * Column 4: This is a special column. It adds a link to view the
		 * Case. We need to pass the case identifier to the url.
		 */
		persontable.addLinkColumn(""	/* The header. We will leave it empty */,
				"View Person" 	/* What should be displayed in every row */,
				"PersonDetail?id=" 	/* This is the base url. The final url will be composed from the concatenation of this and the parameter below */, 
				"PersonID" 			/* For every case displayed, the ID will be retrieved and will be attached to the url base above */);

		// Pass the table to the session. This will allow the respective jsp page to display the table.

		// The filter parameter defines what to show on the cases page
		final String filter = request.getParameter("filter");
		
		if (filter != null) {
			session.setAttribute("search", true);
		
			if(filter.equals("description")) {

				final String name = request.getParameter("description");
				casetable.addObjects(this.dbInterface.searchByName(name));
				//System.out.println(table.generateHtmlCode());

				session.setAttribute("results", casetable);

			} else if (filter.equals("category")) {

				final String category = request.getParameter("category");
				casetable.addObjects(this.dbInterface.searchByCategory(category));
				
				session.setAttribute("results", casetable);

			} else if (filter.equals("personname")) {
				
				final String personname = request.getParameter("personname");
				persontable.addObjects(this.dbInterface.searchPersonByName(personname));	
				
				session.setAttribute("results", persontable);
			}		
		}

		// Finally, proceed to the Seaech.jsp page which will render the search results
        this.getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);
        session.setAttribute("results", null);
        
	}
}