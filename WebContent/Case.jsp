w<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<% final User user = (User) session.getAttribute(UserManagement.SESSION_USER); %>

<h1>Case Details</h1>

<%=session.getAttribute("caseTable")%>

<h1>Person</h1>
<%=session.getAttribute("personTable")%>



<%
if (user != null) {
	// User is logged in. He can add a comment
%>
	
	<form action="Case" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="edit_case" />
		<input type="hidden" name="user_name" value="<%= user.getUsername() %>" />
		<h1>Edit Informations</h1>
		<br/>
		Title <br/>
		<textarea rows="1" cols="50" name="title"></textarea><br/>
		Date <br/>
		<input type="date" name="date"></input><br/>
		Location <br/>
		<textarea rows="1" cols="50" name="location"></textarea><br/>
		<br/>
		Status <br/>
		<select name="openclose">
      		<option>open</option>
      		<option>close</option>
    	</select>
    	<br/>
    	<br/>
    	<input type ="submit" value="Submit" />
	</form>
	
	
	<h1>Note</h1>
	
	<%=session.getAttribute("noteTable")%>

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



<%@ include file="Footer.jsp"%>