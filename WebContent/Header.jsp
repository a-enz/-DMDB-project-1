<%@page import="ch.ethz.inf.dbproject.model.User"%>
<%@page import="ch.ethz.inf.dbproject.util.UserManagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
final User user = (User) session.getAttribute(UserManagement.SESSION_USER);
%>

<html>
	
	<head>
	    <link href="style.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Law Enforcement Project</title>
	</head>

	<body>

		<!-- Header -->
		
		<table id="Header" cellpadding="0" cellspacing="0">
			<tr>
				<th id="masterHeader" colspan="2">
					<h1>Law Enforcement Database</h1>
					Project by Andi, Matthias and Daniel
				</th>
			</tr>
			
		</table>
		
			<ul id="navigation" class="nav-main">
				<li><a href="Home">Home</a></li>
			  
			    <li class="list"><a href="Person">Person</a>
				<ul class="nav-sub">
			    	<li><a href="Person?filter=mostWanted">Most Wanted</a></li>
					<li><a href="PersonNote">Note of Person</a></li>
					<%
					if (user != null) {
					%>
						<li><a href="AddPerson">Add a new Person</a></li>
					<%
					}
					%>
				</ul>
				</li>
			    
			    <li class="list"><a href="Cases">Case</a>
				<ul class="nav-sub">
			 		<li><a href="Cases?filter=open">Open</a></li>
					<li><a href="Cases?filter=closed">Closed</a></li>
					<li><a href="Cases?filter=recent">Most Recent</a></li>
					<li><a href="Cases?filter=oldest">Oldest Unsolved</a></li>
					<%
					if (user != null) {
					%>
						<li><a href="AddCase">Add a new Case</a></li>
					<%
					}
					%>
				</ul>
				</li>
				
				<li class="list"><a href="#">Categories</a>
				<ul class="nav-sub">
					<li><a href="Cases?category=other">Most Recent</a></li>
					<li><a href="Cases?category=property">Property Crime</a></li>
					<li><a href="Cases?category=theft">Theft</a></li>
					<li><a href="Cases?filter=other">Other</a></li>
				</ul>
				</li>
			    
			    <li><a href="Search">Search</a></li>
			    <li><a href="User">User/Login</a></li>
			    
			    
			    
			</ul>
			
			<table id="masterTable" cellpadding="0" cellspacing="0">
			<tr id="masterContent">
				
				<td id="masterContentPlaceholder">
				
		