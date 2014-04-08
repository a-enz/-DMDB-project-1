<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Note on Person</h1>

<%= session.getAttribute("personNote") %>

<%@ include file="Footer.jsp" %>