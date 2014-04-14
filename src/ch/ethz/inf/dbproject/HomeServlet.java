package ch.ethz.inf.dbproject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.util.html.MessageHelper;

/**
 * Servlet implementation class HomePage
 */
@WebServlet(description = "The home page of the project", urlPatterns = { "/Home" })
public final class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private final DatastoreInterface dbInterface = new DatastoreInterface();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }
   
        
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession(true);
		
		try{
		    session.setAttribute("openCasesCount", this.dbInterface.countOpenCases());
		    session.setAttribute("closedCasesCount", this.dbInterface.countClosedCases());
		    session.setAttribute("perpetratorCount", this.dbInterface.countPerpetrators());
		    
		    //session.setAttribute("recentConvicts",this.dbInterface);
		    
		}catch(Exception e){
			e.getStackTrace();
			this.getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
		}
		
        this.getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);	  
	}
}