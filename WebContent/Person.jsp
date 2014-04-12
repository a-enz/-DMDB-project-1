<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<% if (session.getAttribute("deletedPerson") != null) {%>
Person Successfully deleted <br/>
<%=session.getAttribute("deletedPerson")%>
<%} %>

<h1>Person</h1>

<%= session.getAttribute("person") %>

<%@ include file="Footer.jsp" %>