<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<%
if (user != null) {
	// User is logged in. He can edit the person
%>
<h1>Person</h1>

<form method="get" action="PersonDetail">
	<%=session.getAttribute("editTable")%>
	<input type="hidden" name="action" value="edit"/>
 	<input type="hidden" name="id" value="<%=session.getAttribute("id")%>"/>
	<input type="submit" value="Submit"/>
</form>
<%
}
%>
<%@ include file="Footer.jsp" %>