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
			<li><a href="${pageContext.request.contextPath }">Home</a></li>
			<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
			<li><a href="${pageContext.request.contextPath }/board/page?pageno=1&start=1&end=5">게시판</a></li>
		</ul>
	</div>
</body>
</html>