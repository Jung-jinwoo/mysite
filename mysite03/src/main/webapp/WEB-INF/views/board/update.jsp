<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board/update">
					<input type="hidden" name="no" value="${board.no }" />
					<input type="hidden" name="userNo" value="${board.userNo }" />
					<input type="hidden" name="currentno" value="${page.currentno}"/>
					<input type="hidden" name="start" value="${page.start}"/>
					<input type="hidden" name="end" value="${page.end}"/>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${board.title }"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="contents">${board.contents }</textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/view/${board.userNo }&${board.no }&${page.currentno}&${page.start}&${page.end}">취소</a> <input type="submit" value="수정">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>