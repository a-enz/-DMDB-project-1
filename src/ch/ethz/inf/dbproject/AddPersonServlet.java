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
		
		//System.out.println((request.getServletContext() == null) ? "Context Null" : "Context Not null");
        this.getServletContext().getRequestDispatcher("/AddPerson.jsp").forward(request, response);	
	}
	
	
}
