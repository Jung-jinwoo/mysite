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
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user?a=update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${user.name }">

					<label class="block-label" for="email">이메일</label>
					<h1>${user.email }</h1>
					
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${user.gender eq "male" }'>
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
								<label>여</label> <input type="radio" name="gender" value="female" >
							</c:when>
							<c:otherwise>
								<label>남</label> <input type="radio" name="gender" value="male">
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							</c:otherwise>
						</c:choose>
						
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>