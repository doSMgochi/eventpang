<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />

<h1>${categoryTitle}</h1>

<div class="tag-library">
	<button class="btn tag">전체</button>
	<button class="btn tag" id="toggleButton">태그 열기</button>
	<div id="extraTags" style="display: none;">
		<button class="btn tag">퀴즈</button>
		<button class="btn tag">작문/댓글</button>
		<button class="btn tag">팔로우/구독</button>
		<button class="btn tag">공유/초대</button>
		<button class="btn tag">인증샷</button>
		<button class="btn tag">단순응모</button>
		<button class="btn tag">체험단</button>
		<button class="btn tag">설문/투표</button>
		<button class="btn tag">즉석당첨</button>
		<button class="btn tag">신규가입</button>
		<button class="btn tag">구매/샘플</button>
		<button class="btn tag">100%당첨</button>
		<button class="btn tag">선착순</button>
		<button class="btn tag">출석체크</button>
		<button class="btn tag">앱설치</button>
		<button class="btn tag">공모전</button>
		<button class="btn tag">라이브방송</button>
		<button class="btn tag">기타</button>
	</div>
</div>

<section class="items">
	<c:forEach var="event" items="${events}">
		<div class="item">
			<a href="${rootPath}/board/view/${event.evt_num}">
				${event.evt_title} </a>
		</div>
	</c:forEach>
</section>
