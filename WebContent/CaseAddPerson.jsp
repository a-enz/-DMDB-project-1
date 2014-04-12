<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Add Person to Case</h1>

<form action="Case" method="get">

<%= session.getAttribute("person") %>
	<input type="hidden" name="action" value="add_person" />
	<input type="hidden" name="id" value="<%=session.getAttribute("id")%>" />
    <input type="submit" value="Submit" />
</form>

<%@ include file="Footer.jsp" %>