<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Case Search</h1>


<form method="get" action="Search">
<div>
	<input type="hidden" name="filter" value="description" />
	Search By Name:
	<input type="text" name="description" />
	<input type="submit" value="Search" title="Search by Description" />
</div>
</form>


<form method="get" action="Search">
<div>
	<input type="hidden" name="filter" value="category" />
	Search By Category:
	<input type="text" name="category" />
	<input type="submit" value="Search" title="Search by Category" />
</div>
</form>

<h1>Person Search</h1>

<form method="get" action="Search">
<div>
	<input type="hidden" name="filter" value="personname" />
	Search By Name:
	<input type="text" name="personname" />
	<input type="submit" value="Search" title="Search by PersonName" />
</div>
</form>


<%
	if((Boolean) session.getAttribute("search") == true) {
%>
		<%=session.getAttribute("results") %> 
<%
}
%>

<hr/>

<%@ include file="Footer.jsp" %>