<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<% if (session.getAttribute("deletedPerson") != null && session.getAttribute("error") != null) {%>
<%=session.getAttribute("error") %>
<%=session.getAttribute("deletedPerson")%>
<%} %>

<h1>Person</h1>

<%= session.getAttribute("person") %>

<%@ include file="Footer.jsp" %>