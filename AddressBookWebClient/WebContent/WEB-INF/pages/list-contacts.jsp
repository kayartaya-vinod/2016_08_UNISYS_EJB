<%@page import="training.ejb.entity.Contact"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>EJB 2.1 AddressBook App</h1>
	<h2>List of contacts (<a href="./">Home</a>)</h2>
	
	<table border="1">
		<tr>
			<td>Name</td>
			<td>Email</td>
			<td>Phone</td>
		</tr>
		<%
			Collection list = (Collection)request.getAttribute("contacts");
			Iterator it = list.iterator();
			while(it.hasNext()){
				Contact c = (Contact)it.next();
		%>
		<tr>
			<td><%=c.getName() %></td>
			<td><%=c.getEmail() %></td>
			<td><%=c.getPhone() %></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>