<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <nav>
       <ul class="container p-0">
       		<c:forEach items="${cate}" var="c">
       			<li><a href="${cp}/board/list?cno=${c.cno}">${c.cname}</a></li>
       			<%-- ${c}--%>
       		</c:forEach>
       
          <%--   <li><a href="${cp}/board/list?cno=1">갤러리</a></li>
           <!-- <li><a href="board?cno=2">자유게시판</a></li> -->
            <li><a href="${cp}/board/list?cno=2">자유게시판</a></li>
           
           <li><a href="${cp}/board/list?cno=3">Q&amp;A</a></li>
           <li><a href="${cp}/board/list?cno=4">자료실</a></li>--%>
       </ul>
    </nav>