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
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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
									<td style="text-align:left; padding-left:${board.depth*20}px"><img
										src="${pageContext.servletContext.contextPath }/assets/images/reply.png" /><a
										href="${pageContext.request.contextPath }/board?a=view&no=${board.userNo}&boardNo=${board.no}">${board.title }</a></td>
									<td>${board.userName }</td>
									<td>${board.hit }</td>
									<td>${board.regDate }</td>
									<%-- <td>${board.groupNo }</td>
									<td>${board.orderNo }</td>
									<td>${board.depth }</td> --%>
									<td><a
										href="${pageContext.servletContext.contextPath }/board?a=delete&userNo=${board.userNo}&boardNo=${board.no}"
										class="del">삭제</a></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>삭제된 게시글 입니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<%-- <div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li class="selected">1</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath }/board?a=page&page=">2</li>
						<li><a href="">3</a></li>
						<li><a href="">4</li>
						<li><a href="">5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div> --%>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:set var="totalpage" value="${page.totalpage }" />
						<c:choose>
							<c:when test="${totalpage lt 5 }">
								<c:forEach var='pageno' begin="${page.currentno }" end="5">
									<li><a href="">${pageno }</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var='pageno' begin="${page.currentno }"
									end="${page.totalpage}">
									<li><a href=""><c:out value="${pageno }" /></li>

								</c:forEach>
							</c:otherwise>
						</c:choose>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath }/board?a=writeform"
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