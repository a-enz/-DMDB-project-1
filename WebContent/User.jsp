<%@page import="ch.ethz.inf.dbproject.UserServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>

<h1>Your Account</h1>

<% 
if ((Boolean) session.getAttribute(UserServlet.SESSION_USER_LOGGED_IN)) {
	// User is logged in. Display the details:
%>
	
<font size="3"> <%= session.getAttribute(UserServlet.SESSION_USER_DETAILS) %></font>


	<form action="User" method="get">
	<input type="hidden" name="action" value="logout" />
	<table>
		<tr>
			<td><input type="submit" value="Logout"/></td>
		</tr>
	</table>
	</form>

<%
} else {
	// User not logged in. Display the login and register form.
%>

	<form action="User" method="get">
	<input type="hidden" name="action" value="login" />
	<table>
		<tr>
			<th>Username</th>
			<td><input type="text" name="username" value="" /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="password" value="" /></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="Login" />
			</th>
		</tr>
	</table>
	</form>	
	<%if(session.getAttribute("loginerror") != null){ %>
		<%=session.getAttribute("loginerror")%>
	<%} %>
	<br>
	<h1>if you are a new User register here:</h1>
	
		<form action="User" method="get">
	<input type="hidden" name="action" value="register" />
	<table>
		<tr>
			<th>Username</th>
			<td><input type="text" name="newuser" value="" /></td>
		</tr>
		<tr>
			<th>Name</th>
			<td><input type="text" name="realname" value="" /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="newpassword" value="" /></td>
		</tr>
		<tr>
			<th>Confirm password</th>
			<td><input type="password" name="passwordconfirm" value="" /></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="Register" />
			</th>
		</tr>
	</table>
	</form>
	<%if(session.getAttribute("regerror") != null){ %>
		<%=session.getAttribute("regerror")%>
	<%} %>
<%
}
%>

<%session.setAttribute("regerror","");%>
<%session.setAttribute("loginerror", ""); %>

<%@ include file="Footer.jsp" %>