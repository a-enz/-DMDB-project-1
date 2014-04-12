<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<% final User user = (User) session.getAttribute(UserManagement.SESSION_USER); %>

<h1>Person Details</h1>

<%=session.getAttribute("persondetailTable")%>

<%
if ((Boolean) session.getAttribute("edited") == true) {	//client has just edited some entries of this person
%>
	Entries edited <br /> 
<%
}
%>

<h1>Involved Cases</h1>

<%=session.getAttribute("involvedCasesTable") %>

<%
if (user != null) {
	// User is logged in. He can edit the person
%>
	<form action="PersonEdit" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>"/>
		<input type="submit" value="Edit"/>
	</form>
<%
}
%>
<h1>Notes</h1>
<%=session.getAttribute("personnoteTable")%>


<%
if (user != null) {
	// User is logged in. He can add a comment
%>
	<form action="PersonDetail" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="add_comment" />
		<input type="hidden" name="user_name" value="<%= user.getUsername() %>" />
		Add Comment
		<br />
		<textarea rows="4" cols="50" name="comment"></textarea>
		<br />
		<input type="submit" value="Submit" />
	</form>
	
	<form action="Person" method="get">
		<input type="hidden" name="action" value="delete" />
		<input type="hidden" name="id" value="<%=session.getAttribute("id")%>" />
		<input type="submit" value="Delete Person"/>
	</form>
<%
}
%>

<%@ include file="Footer.jsp"%>