<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Case</h1>

<form action="AddCase" method="get">
	<input type="hidden" name="action" value="add">
	Title*: <input type="text" name="Title"/><br/>
	Date*: <input type="date" name="Date"/><br/>
	Location: <input type="text" name="Location" /><br/>
	Date of Conviction: <input type="date" name="DateCon"/><br/>
	Enddate: <input type="date" name="DateEnd"><br/>
	*Mandatory field <br/>
	<input type="submit" value="Add" title="AddCase"/> <br/> <br/>
</form>

<%-- <%
	if((Boolean) session.getAttribute("valid_input") == false && (Boolean) session.getAttribute("added") == false) {
%>
		Invalid Input <br/>
<%
}
%> --%>
<%
	if((Boolean) session.getAttribute("added") == true) {
%>
		Person successfully added <br/>
		<%= session.getAttribute("result") %>
<%
}
%>



<%@ include file="Footer.jsp" %>