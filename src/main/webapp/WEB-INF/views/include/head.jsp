<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이벤트팡!</title>
<link href="${rootPath }/static/css/main.css" rel="stylesheet" />
<link href="${rootPath }/static/css/board.css" rel="stylesheet" />
<link href="${rootPath }/static/css/user.css" rel="stylesheet" />
<script>
	const rootPath = "${rootPath}";
</script>
<script src="${rootPath }/static/js/main.js"></script>
<script src="${rootPath }/static/js/user/join.js"></script>
<script src="${rootPath }/static/js/user/login.js"></script>
<script src="${rootPath }/static/js/user/modify.js"></script>
<script src="${rootPath }/static/js/board/board.js"></script>
<script src="${rootPath }/static/js/board/event-write.js"></script>
<script src="${rootPath }/static/js/fireworks.js"></script>
<script src="${rootPath }/static/js/menu.js"></script>
<script src="${rootPath }/static/js/pageLoader.js"></script>
</head>
