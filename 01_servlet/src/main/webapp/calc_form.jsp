<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="calc_result.jsp">
		<input name="number1">
		<input name="number2">
		<button>전송</button>
		<button formaction="calcServ">서블릿으로</button>
	</form>
	<h3><%= request.getParameter("v")%></h3>
</body>
</html>