package ch.ethz.inf.dbproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.Case;
import ch.ethz.inf.dbproject.model.Category;
import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;


@WebServlet(description = "The add case page", urlPatterns = { "/AddCase" })
public class AddCaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	public AddCaseServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession(true);
		
		final BeanTableHelper<Case> table = new BeanTableHelper<Case>(
				"cases" 		/* The table html id property */,
				"table" /* The table html class property */,
				Case.class 	/* The class of the objects (rows) that will be displayed */
		);
		
		final BeanTableHelper<Category> catTable = new BeanTableHelper<Category>(
				"category",
				"table",
				Category.class);
		
		session.setAttribute("added", false);
		
		catTable.addBeanColumn("Name", "CatName");
		catTable.addBeanColumn("Parent Category", "Parent");
		catTable.addCheckColumn("Add", "", "check", "CatName");
		table.addBeanColumn("Title", "Title");
		table.addBeanColumn("Date", "Date");
		table.addBeanColumn("Location", "Location");
		table.addBeanColumn("Status", "Status");
		table.addBeanColumn("DateCon", "DateCon");
		table.addBeanColumn("DateEnd", "DateEnd");
		
		final String title = request.getParameter("Title");
		final String date = request.getParameter("Date");
		final String location = request.getParameter("Location");
		final String dateCon = request.getParameter("DateCon");
		final String dateEnd = request.getParameter("DateEnd");
		final String action = request.getParameter("action");

		//System.out.println("Date: " + date);
		if(action != null && action.equals("add") && dbInterface.insertCase(title, date, location, dateCon, dateEnd))
		{
			table.addObject(new Case(0, title, date, location, "open", dateCon, dateEnd));
			session.setAttribute("added", true);
			session.setAttribute("result", table);
		}
		else{
		}

		
        this.getServletContext().getRequestDispatcher("/AddCase.jsp").forward(request, response);	
	}
}
