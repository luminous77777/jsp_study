<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
	<div class="container p-0">
        <main>
            <div class="clearfix py-0">
                <a href="board_write.html" class="btn btn-primary btn-sm float-end"><i class="fa-solid fa-pen-fancy"></i>글쓰기</a>
            </div>
            <div class="list-group p-0 py-5 ps-1 small border-bottom border-1 border-muted">
                <div class="list-group-item small">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">글번호</div>
                        <div class="col-1 small">카테고리</div>
                        <div class="col text-start">제목</div>
                        <div class="col-1  small">작성일</div>
                        <div class="col-1  small">조회수</div>
                    </div>
                </div>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">1</div>
                        <div class="col-1 small">자유</div>
                        <div class="col text-start fw-bold text-black">제목</div>
                        <div class="col-1  small"><span class="small">25.06.13</span></div>
                        <div class="col-1  small"><span class="small">13</span></div>
                    </div>
                </a>
                <c:forEach items="${boards}" var="board">
                <a href="view?bno=${board.bno}" class="list-group-item list-group-item-action">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">${board.bno}</div>
                        <div class="col-1 small">${board.cno}</div>
                        <div class="col text-start fw-bold text-black">${board.title}<span class="small text-danger fw-bold">1</span></div>
                        <div class="col-1  small"><span class="small">
                        <fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="parseDate" />
                        <fmt:formatDate value="${parseDate}" pattern="yy.MM.dd"/>
                       </span></div>
                        <div class="col-1  small"><span class="small">${board.cnt}</span></div>
                    </div>
                </a>
				</c:forEach>
				
				
            </div>
            
            <div>
                    <button class="btn btn-secondary btn-sm"> <i class="fa-solid fa-list-ul"></i> 목록</button>
                    <div class="float-end">
                        <button class="btn btn-outline-primary btn-sm"><i class="fa-solid fa-share-nodes"></i> 공유</button>
                        <button class="btn btn-outline-primary btn-sm"><i class="fa-solid fa-clipboard"></i> 스크랩</button>
                    </div>
                </div>
        </main>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>