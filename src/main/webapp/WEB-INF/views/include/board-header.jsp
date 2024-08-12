<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<c:set var="logon" value="${sessionScope.USER}" />
<body class="info">
	<nav>
		<ul class="top-nav">
			<li></li>
			<li><a href="${rootPath }/"><img
					src="${rootPath }/static/images/logo.png" alt="logo" /></a></li>
			<c:choose>
				<c:when test="${logon == null}">
					<li><a href="${rootPath}/user/login">로그인</a></li>
					<li><a href="${rootPath}/user/join">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${rootPath}/user/logout">${logon.user_nick} 님
							로그아웃</a></li>
					<li><a href="${rootPath}/user/modify">정보수정</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<hr class="hr-1" />
</body>