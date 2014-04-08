<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Person</h1>

<%= session.getAttribute("person") %>

<%@ include file="Footer.jsp" %>