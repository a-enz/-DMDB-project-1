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
import ch.ethz.inf.dbproject.util.UserManagement;
import ch.ethz.inf.dbproject.util.html.BeanTableHelper;

/**
 * Servlet implementation class of Case Details Page
 */
@WebServlet(description = "Displays a specific case.", urlPatterns = { "/Case" })
public final class CaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatastoreInterface dbInterface = new DatastoreInterface();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession(true);

		final String idString = request.getParameter("id");
		
		if (idString == null) {
			this.getServletContext().getRequestDispatcher("/Cases").forward(request, response);
		}

		try {

			final Integer id = Integer.parseInt(idString);
			session.setAttribute("id", id);

			
			//------------------Edit Informations------------------
			
			final String action = request.getParameter("action");
			
			if (action != null && action.equals("edit_case")){
				System.out.println("\nEditing Case " + id + ":");
				String Title = request.getParameter("title");
				String Date = request.getParameter("date");
				String Location = request.getParameter("location");
				String openclose = request.getParameter("openclose");
				
				if (Title != null && Title != ""){
					System.out.println("Title: " + Title);
					dbInterface.updateCaseTitle(id, Title);
				}
				
				if (Date != null && Date != ""){
					System.out.println("Date: " + Date);
					dbInterface.updateCaseDate(id, Date);
				}
				
				if (Location != null && Location != ""){
					System.out.println("Location: " + Location);
					dbInterface.updateCaseLocation(id, Location);
				}
				
				if (openclose != null){
					System.out.println("openclose: " + openclose);
					dbInterface.updateCaseStatus(id, openclose);
				}
				
			}
			
			//-----------------TODO: Add Comment------------------
			
			if (action !=null && action.equals("add_comment")){
				System.out.println("Action: add a new note");
				String Text = request.getParameter("comment");
				String Username = request.getParameter("username");
				
				if (Text != null && Username != null){
					System.out.println("Inserting a new Case Note");
					dbInterface.insertCaseNote(id, Username, Text);
				}
				
			}
			
			
			//---------------- Create Case Table -----------------
			
			/*******************************************************
			 * Construct a table to present all properties of a case
			 *******************************************************/
			final BeanTableHelper<Case> table = new BeanTableHelper<Case>(
					"cases" 		/* The table html id property */,
					"table" /* The table html class property */,
					Case.class 	/* The class of the objects (rows) that will be displayed */
			);

			// Add columns to the new table

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
			
			table.addObject(dbInterface.getCaseById(id));
			table.setVertical(true);
			

			session.setAttribute("caseTable", table);	
			
			//------------------- Create Person Table ----------------------
			
			final BeanTableHelper<CasePerson> persontable = new BeanTableHelper<CasePerson>(
					"table",
					"table",
					CasePerson.class
			);
			
			persontable.addBeanColumn("PersonID", "PersonID");
			persontable.addBeanColumn("Firstname", "FirstName");
			persontable.addBeanColumn("Surname","SurName");
			persontable.addBeanColumn("Reason", "Reason");
			

			persontable.addObjects(dbInterface.GetCasePersonById(id));
			session.setAttribute("personTable", persontable);
			
			//------------------- TODO Create Note Table-----------------
			
			final BeanTableHelper<NoteText> notetable = new BeanTableHelper<NoteText>(
					"table" 		/* The table html id property */,
					"table" /* The table html class property */,
					NoteText.class 	/* The class of the objects (rows) that will be displayed */
			);
			
			notetable.addBeanColumn("Text", "Text");
			
			notetable.addObjects(dbInterface.getCaseNoteById(id));
			session.setAttribute("noteTable", notetable);
			
		} catch (final Exception ex) {
			ex.printStackTrace();
			this.getServletContext().getRequestDispatcher("/Cases.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/Case.jsp").forward(request, response);
	}
}