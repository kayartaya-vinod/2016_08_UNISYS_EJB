<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product list</title>
</head>
<body>
<h1>Product list</h1>

<ul>
<c:forEach items="${products}" var="p">
	<li>${p.productName}</li>
</c:forEach>
</ul>
</body>
</html>