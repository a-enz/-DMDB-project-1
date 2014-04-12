package ch.ethz.inf.dbproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.Person;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;



@WebServlet(description = "The home page of the project", urlPatterns = { "/PersonEdit" })
public final class EditPersonServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();
	
    public EditPersonServlet() {
        super();
    }
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
		
		final HttpSession session = request.getSession(true);
		
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
		
//		try {
//			final int id = request.getParameter("id");
//		} catch {
//			
//		}
		//table.addObject(this.dbInterface.getPersonById(id));

		//session.setAttribute("persondetailTable", table);
		
		this.getServletContext().getRequestDispatcher("/PersonEdit.jsp").forward(request, response);
	}
	
}
