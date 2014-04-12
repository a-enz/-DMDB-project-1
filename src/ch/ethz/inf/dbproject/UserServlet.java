package ch.ethz.inf.dbproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ethz.inf.dbproject.model.DatastoreInterface;
import ch.ethz.inf.dbproject.model.User;
import ch.ethz.inf.dbproject.util.UserManagement;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;
import ch.ethz.inf.dbproject.util.html.MessageHelper;

@WebServlet(description = "Page that displays the user login / logout options.", urlPatterns = { "/User" })
public final class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	public final static String SESSION_USER_LOGGED_IN = "userLoggedIn";
	public final static String SESSION_USER_DETAILS = "userDetails";
	public final static String SESSION_USER = "LOGGED_IN_USER";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request,
		final HttpServletResponse response) throws ServletException,
		IOException {
		
		final HttpSession session = request.getSession(true);
		final String action = request.getParameter("action");
		
		
		if (action != null && action.trim().equals("logout")){
			session.setAttribute(SESSION_USER_LOGGED_IN, false);
			UserManagement.Logout(session);
		}
		

		if (action != null && action.trim().equals("login") && UserManagement.getCurrentlyLoggedInUser(session) == null) {

			final String username = request.getParameter("username");
			// Note: It is really not safe to use HTML get method to send passwords.
			// However for this project, security is not a requirement.
			final String password = request.getParameter("password");

			// TODO
			// Ask the data store interface if it knows this user
			User user = dbInterface.getUser(username, password);
			
			if (user != null){				
				session.setAttribute(SESSION_USER_LOGGED_IN, true);
				session.setAttribute(SESSION_USER, user);
			}
			// Retrieve User
			// Store this user into the session
			
		}
		
		else if (action != null && action.trim().equals("register") && UserManagement.getCurrentlyLoggedInUser(session) == null) {
			
			MessageHelper mh = new MessageHelper();
			
			
			final String newuser = request.getParameter("newuser");
			final String realname = request.getParameter("realname");
			// Note: It is really not safe to use HTML get method to send passwords.
			// However for this project, security is not a requirement.
			final String password = request.getParameter("newpassword");
			final String passwordconf = request.getParameter("passwordconfirm");

			//check if this user allready exists. password doesn't matter
		
			//check if password confirmation succeeded
			if(password != null && !password.equals("") && newuser != null && !newuser.equals("") && realname != null || !realname.equals("")){
				if(password.equals(passwordconf)){
					//..and user doesn't already exist
					if (!dbInterface.isRegistered(newuser)){
						this.dbInterface.insertUser(newuser, password, realname);
						mh.SuccessMessage("REGISTRATION SUCCESSFUL!");
					}
					else mh.ErrorMessage("Username allready exists.");
				}
				else mh.ErrorMessage("Password confirmation failed.");
			}
			else mh.ErrorMessage("Fill out all fields.");
			// Retrieve User
			// Store this user into the session
			

		    session.setAttribute("error", mh.toString());
		}

		//---------- show Userinformation or Login -----------
		
		final User loggedUser = UserManagement.getCurrentlyLoggedInUser(session);
		
		if (loggedUser == null) {
			// Not logged in!
			session.setAttribute(SESSION_USER_LOGGED_IN, false);
		} else {
			// Logged in
			final BeanTableHelper<User> userDetails = new BeanTableHelper<User>("userDetails", "userDetails", User.class);
			userDetails.addBeanColumn("Username", "username");
			userDetails.addBeanColumn("Name", "name");
			userDetails.setVertical(true);
			
			userDetails.addObject(UserManagement.getCurrentlyLoggedInUser(session));

			session.setAttribute(SESSION_USER_LOGGED_IN, true);
			session.setAttribute(SESSION_USER_DETAILS, userDetails);
		}


		// Finally, proceed to the User.jsp page which will renden the profile
		this.getServletContext().getRequestDispatcher("/User.jsp").forward(request, response);

	}

}
