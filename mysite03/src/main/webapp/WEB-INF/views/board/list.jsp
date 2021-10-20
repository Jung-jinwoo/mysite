<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/page" method="post">
					<input type="hidden" name="currentno" value="${page.currentno}"/>
					<input type="hidden" name="start" value="${page.start}"/>
					<input type="hidden" name="end" value="${page.end}"/>
					<input type="text" id="kwd" name="kwd" value=""> 
					<input type="submit" value="찾기">
						
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left; padding-left: 20px">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<!-- <th>그룹번호</th>
						<th>주문번호</th>
						<th>깊이</th> -->
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var="board" varStatus='status'>
						<c:choose>
							<c:when test="${1 eq board.status }">
								<tr>
									<td>${count -status.index }</td>
									
									<c:choose>
										<c:when test="${0 eq board.depth }">
										<td>
											<a href="${pageContext.request.contextPath }/board?a=view&no=${board.userNo}&boardNo=${board.no}&page=${page.currentno}&start=${page.start}&end=${page.end}">${board.title }</a></td>
										</c:when>
										<c:otherwise>
										<td style="text-align:left; padding-left:${board.depth*15}px">
											<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
										<a href="${pageContext.request.contextPath }/board?a=view&no=${board.userNo}&boardNo=${board.no}&page=${page.currentno}&start=${page.start}&end=${page.end}">${board.title }</a></td>
										</c:otherwise>
									</c:choose>
									
									<td>${board.userName }</td>
									<td>${board.hit }</td>
									<td>${board.regDate }</td>
									<td><a
										href="${pageContext.servletContext.contextPath }/board?a=delete&userNo=${board.userNo}&boardNo=${board.no}&page=${page.currentno}&start=${page.start}&end=${page.end}"
										class="del">삭제</a></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td></td>
									<td colspan="4">
										<p>삭제된 게시글 입니다.</p>
									</td>
									<td></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				
				<div class="pager">
					<ul>
					<c:set var="totalpage" value="${page.totalpage }" />
					<c:set var="currentno" value="${page.currentno }" />
					
					<c:choose>
						<c:when test="${page.currentno < 2 }">
							<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${page.prev}&start=${page.start}&end=${page.end}" class="disabled">◀</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${page.prev}&start=${page.start}&end=${page.end}">◀</a></li>
						</c:otherwise>
					</c:choose>
						
					<c:forEach var='pageno' begin="${page.start }" end="${page.end }" varStatus="status">
					<c:set var='index' value='${status.index }' />
						<c:choose>
							<c:when test="${index eq currentno }">
								<li class="selected"><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${pageno}&start=${page.start}&end=${page.end}" >${pageno }</a></li>
							</c:when>
							<c:when test="${pageno > totalpage }">
								<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${pageno}&start=${page.start}&end=${page.end}" class="disabled">${pageno }</a></li>
							</c:when>
							<c:when test="${pageno ne currentno }">
								<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${pageno}&start=${page.start}&end=${page.end}" >${pageno }</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
						
					<c:choose>
						<c:when test="${page.currentno eq totalpage }">
							<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${page.next}&start=${page.start}&end=${page.end}" class="disabled">▶</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.servletContext.contextPath }/board/page&pageno=${page.next}&start=${page.start}&end=${page.end}">▶</a></li>
						</c:otherwise>
					</c:choose>
						
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath }/board/write"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>