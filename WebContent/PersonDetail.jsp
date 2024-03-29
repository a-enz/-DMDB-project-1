<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<%
if (session.getAttribute("error") != null){ %>

<%=session.getAttribute("error")%>
<%
	session.removeAttribute("error");
}
%>

<h1>Person Details</h1>

<%=session.getAttribute("persondetailTable")%>

<%
if (user != null) {
	// User is logged in. He can edit the person
%>
	<form action="PersonEdit" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>"/>
		<input type="submit" value="Edit Person"/>
	</form>
	<form action="Person" method="get">
		<input type="hidden" name="action" value="delete" />
		<input type="hidden" name="id" value="<%=session.getAttribute("id")%>" />
		<input type="submit" value="Delete Person"/>
	</form>
<%
}
%>
<br/>
<h1>Convictions</h1>
<%=session.getAttribute("involvedCasesTable") %>

<%
if (user != null) {
	// User is logged in. He can add a comment
%>
<br/>
<br/>
<hr/>
<hr/>
<h1>Notes</h1>

<%
if (request.getParameter("action") != null && request.getParameter("action").equals("edit_note")){ 
%>
	<form action="PersonDetail" method="get">
<%
}
%>

<%=session.getAttribute("personnoteTable")%>

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
if ((request.getParameter("action") == null || !(request.getParameter("action").equals("edit_note")))){ 
%>
	<form action="PersonDetail" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>"/>
		<input type="hidden" name="action" value="edit_note"/>
		<input type="submit" value="Edit all Notes">
	</form>
<%
}
%>
<br /> 
<br />
	<form action="PersonDetail" method="get">
		<input type="hidden" name="id" value ="<%=session.getAttribute("id")%>" />
		<input type="hidden" name="action" value="add_comment" />
		<input type="hidden" name="user_name" value="<%= user.getUsername() %>" />
		<h1>Add Note</h1>
		<textarea rows="4" cols="50" name="comment"></textarea>
		<br />
		<input type="submit" value="Submit" />
	</form>
<%
}
%>
<%@ include file="Footer.jsp"%>