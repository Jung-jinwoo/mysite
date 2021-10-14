<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navigation">
		<ul>
			<c:choose>
				<c:when test='${empty authUser }'>
					<li><a href="${pageContext.request.contextPath }">정진우</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }">Home</a></li>
					<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/board?a=page">게시판
					<input type="hidden" name="userno" value="${authUser.no }"/></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>