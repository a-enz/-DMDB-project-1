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
@WebServlet(description = "The home page of the project", urlPatterns = { "/Cases" })
public final class CasesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CasesServlet() {
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
		final BeanTableHelper<Case> table = new BeanTableHelper<Case>(
				"cases" 		/* The table html id property */,
				"table" /* The table html class property */,
				Case.class 	/* The class of the objects (rows) that will be displayed */
		);

		// Add columns to the new table

		/*
		 * Column 1: The name of the item (This will probably have to be changed)
		 */
		table.addBeanColumn("CaseNr", "CaseNr");

		/*
		 * Columns 2 & 3: Some random fields. These should be replaced by i.e. funding progress, or time remaining
		 */
		table.addBeanColumn("Title", "Title");
		table.addBeanColumn("Date", "Date");
		table.addBeanColumn("Location", "Location");
		table.addBeanColumn("Status", "Status");
		table.addBeanColumn("DateCon", "DateCon");
		table.addBeanColumn("DateEnd", "DateEnd");

		/*
		 * Column 4: This is a special column. It adds a link to view the
		 * Project. We need to pass the case identifier to the url.
		 */
		table.addLinkColumn("",	/* The header. We will leave it empty */
				"View Case", 	/* What should be displayed in every row */
				"Case?id=", 	/* This is the base url. The final url will be composed from the concatenation of this and the parameter below */
				"CaseNr"); 			/* For every case displayed, the ID will be retrieved and will be attached to the url base above */

		// Pass the table to the session. This will allow the respective jsp page to display the table.
		session.setAttribute("cases", table);

		// The filter parameter defines what to show on the Projects page
		final String filter = request.getParameter("filter");
		final String category = request.getParameter("category");

		if (filter == null && category == null) {

			table.addObjects(this.dbInterface.getAllCases());

		} else if (category != null) {

			table.addObjects(this.dbInterface.getCasesByCategory(category));
			
		} else if (filter != null) {
		
			if(filter.equals("open")) {

				table.addObjects(this.dbInterface.getOpenCases());

			} else if (filter.equals("closed")) {

				table.addObjects(this.dbInterface.getClosedCases());

			} else if (filter.equals("recent")) {

				table.addObjects(this.dbInterface.getMostRecentCases());

			}
			
			else if (filter.equals("oldest")) {

				table.addObjects(this.dbInterface.getOldestUnsolvedCases());
			}
			
		} else {
			throw new RuntimeException("Code should not be reachable!");
		}

		// Finally, proceed to the Projects.jsp page which will render the Projects
		this.getServletContext().getRequestDispatcher("/Cases.jsp").forward(request, response);
	}
}