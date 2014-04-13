<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Case</h1>

<form action="AddCase" method="get">
	<input type="hidden" name="action" value="add">

<table style="text-align:left">
	<tr><th>Title*:</th><th> <input type="text" name="Title"/></th></tr>
	<tr><th>Date*:</th><th> <input type="date" name="Date"/></th></tr>
	<tr><th>Location:</th><th> <input type="text" name="Location" /></th></tr>
	<tr><th>Date of Conviction:</th><th> <input type="date" name="DateCon"/></th></tr>
	<tr><th>Enddate:</th><th> <input type="date" name="DateEnd"></th></tr>
</table>
*Mandatory field<br/>

<%= session.getAttribute("cattable") %> <br />

<input type="submit" value="Add" title="AddCase"/>


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
		<br />
		<%= session.getAttribute("success") %> <br />
		<%= session.getAttribute("result") %> <br />
		<%= session.getAttribute("resultcat") %>
<%
}
%>
<%session.setAttribute("success", "");%>



<%@ include file="Footer.jsp" %>