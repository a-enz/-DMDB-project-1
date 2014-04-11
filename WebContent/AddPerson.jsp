<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Person</h1>

<form action="AddPerson" method="get">
	Firstname: <input type="text" name="Firstname"/><br/>
	Surname: <input type="text" name="Surname"/><br/>
	Street: <input type="text" name="Street" /><br/>
	Birthdate: <input type="date" name="Birthdate"/><br/>
	Address: <input type="text" name="Address"/><br/>
	<!-- TODO: Implement editing option -->
	Bounty: <input type="text" name="Bounty"/><br/>
	<input type="submit" value="Add" title="AddPerson"/>
</form>

<%@ include file="Footer.jsp" %>