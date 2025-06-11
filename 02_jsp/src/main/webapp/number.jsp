<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${num}</h3>
	<%-- <p>주석처리 됨</p> --%>
	
	<h3><%=request.getAttribute("num") %></h3>
</body>
</html>