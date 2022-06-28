<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> AdminLog Out</title>
</head>
<body>
<%
	session.removeAttribute("a");
	session.invalidate();
	response.sendRedirect("adminlogin.jsp");
%>
</body>
</html>