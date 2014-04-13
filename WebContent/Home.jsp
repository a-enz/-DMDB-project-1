<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.HomeServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>


<font size=4>
<%
if (user != null) {
	// There is a user logged in! Display a greeting!
%>
	Welcome back <%=user.getName()%>
<%
} else {
	// No user logged in.%>
	Welcome!
<%
}
%>

<br /><br />
<br /><br />
See all available <a href="Cases">cases</a> and <a href="Person">Persons of Interest</a>.

<br /><br />

there are currently <%=session.getAttribute("openCasesCount") %> open cases <a href="Cases?filter=open"> view all</a>

<br /><br />

there are currently <%=session.getAttribute("closedCasesCount") %> closed cases <a href="Cases?filter=closed"> view all</a>
<br /><br />

Total Convictions: <%=session.getAttribute("perpetratorCount") %>
</font>
<%@ include file="Footer.jsp" %>