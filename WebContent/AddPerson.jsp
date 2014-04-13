<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Person</h1>

<form action="AddPerson" method="get">
	
	<table style="text-align: left">
		<tr><th>Firstname*: </th><th><input type="text" name="FirstName"/></th></tr>
		<tr><th>Surname*: </th><th><input type="text" name="SurName"/></th></tr>
		<tr><th>Street: </th> <th> <input type="text" name="Street" /></th></tr>
		<tr><th>Birthdate: </th><th><input type="date" name="BirthDate"/></th></tr>
		<tr><th>Nationality: </th><th><input type="text" name="Nationality"/></th></tr>
	</table>
	
	*Mandatory field <br/>
	<input type="submit" value="Add" title="AddPerson"/> <br/> <br/>
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
		<%= session.getAttribute("personinfo") %>
<%
}
%>



<%@ include file="Footer.jsp" %>