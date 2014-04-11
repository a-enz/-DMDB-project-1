<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Person</h1>

<form action="AddPerson" method="get">
	Firstname*: <input type="text" name="FirstName"/><br/>
	Surname*: <input type="text" name="SurName"/><br/>
	Street: <input type="text" name="Street" /><br/>
	Birthdate: <input type="date" name="BirthDate"/><br/>
	Nationality: <input type="text" name="Nationality"/><br/>
	<!-- TODO: Implement editing option -->
	Bounty: <input type="number" name="Bounty" step="100"/><br/>
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