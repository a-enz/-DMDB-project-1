w<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<%=session.getAttribute("error")%>
<%session.removeAttribute("error");%>

<h1>Case Details</h1>

<%=session.getAttribute("caseTable")%>

<h1>Person</h1>
<%=session.getAttribute("personTable")%>

<%
if (user != null) {
	// User is logged in. He can add a comment
%>

   	<%
   	if((Boolean) session.getAttribute("is_closed") == false){%>	
	<form action="CaseAddPerson" method="get">
		<input type="hidden" name="id" value="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="add_person" />
		<input type="submit" value="Add Person" />
	</form>
	<%
	}
   	%>
	<form action="Case" method="get">		
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="edit_state"/>
		
	<h1>Change Status to:</h1>
   	<%
   	if((Boolean) session.getAttribute("is_closed") == true){%>	
		<input type="submit" name="openclose" value="open" style="height:50px; width:100px;font-size:20px"/>
	<%
   	}else{ 
	%>
		<input type="submit" name="openclose" value="closed" style="height:50px; width:100px;font-size:20px"/>
	<%
	} 
	%>
	</form>

   	<%
   	if((Boolean) session.getAttribute("is_closed") == false){%>	 	
   	<form action="Case" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="edit_case"/>
		<input type="hidden" name="user_name" value="<%= user.getUsername() %>" />
		<h1>Edit Informations</h1>
		Status <br/>
 		<br/>
		<br/>
		Title <br/>
		<textarea rows="1" cols="50" name="title"></textarea><br/>
		Date <br/>
		<input type="date" name="date"></input><br/>
		Location <br/>
		<textarea rows="1" cols="50" name="location"></textarea><br/>
		<input type ="submit" value="Submit" />
	</form>
	
	

	<h1>Note</h1>
	
	
	<%
	if (request.getParameter("action") != null && request.getParameter("action").equals("edit_note")){ 
	%>
		<form action="Case" method="get">
	<%
	}
	%>
	
	<%=session.getAttribute("noteTable")%>
	
	<%
	if (request.getParameter("action") != null && request.getParameter("action").equals("edit_note")){ 
	%>
			<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>"/>
			<input type="hidden" name="action" value="save_note"/>
			<input type="submit" value="Save">
		</form>
	<%
	}
	%>
	
	<%
	if (request.getParameter("action") == null || !(request.getParameter("action").equals("edit_note"))){ 
	%>
		<form action="Case" method="get">
			<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>"/>
			<input type="hidden" name="action" value="edit_note"/>
			<input type="submit" value="Edit Note">
		</form>
	<%
	}
	%>
	
	
	<br/>
	<form action="Case" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="add_comment" />
		<input type="hidden" name="username" value="<%= user.getUsername() %>" />
		<h1>Add Note</h1>
		<br />
		<textarea rows="4" cols="50" name="comment"></textarea>
		<br />
		<input type="submit" value="Submit" />
	</form>
	<%
	}
	%>
<%
}
%>



<%@ include file="Footer.jsp"%>