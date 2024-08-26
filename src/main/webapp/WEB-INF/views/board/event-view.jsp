<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<h1>${mainCategory}</h1>
<h3>상세보기</h3>
<div class="event-container">
	<div class="event-header">
		<img src="https://via.placeholder.com/60" alt="주최자 이미지">
		<div>
			<h1 class="event-title">${event.evt_title}</h1>
			<div class="event-meta">
				<span>조회수: ${event.evt_views }</span> | <span>${formattedWritedTime}</span>
			</div>
		</div>
	</div>

	<div class="event-details">
		<div class="event-detail">
			<span class="label">주최자:</span> <span class="value">${event.evt_host}</span>
		</div>
		<div class="event-detail">
			<span class="label">응모 링크:</span> <span class="value"><a
				href="${event.evt_link}" target="_blank">${event.evt_link}</a></span>
		</div>
		<div class="event-detail">
			<span class="label">카테고리:</span> <span class="value">${mainCategory}</span>
		</div>
		<div class="event-detail">
			<span class="label">세부 카테고리:</span> <span class="value">${subCategory}</span>
		</div>
		<div class="event-detail">
			<span class="label">태그:</span> <span class="value">${event.evt_tags}</span>
		</div>
		<div class="event-detail">
			<span class="label">시작 날짜:</span> <span class="value">${formattedStartTime}</span>
		</div>
		<div class="event-detail">
			<span class="label">마감 날짜:</span> <span class="value">${formattedEndTime}</span>
		</div>
		<div class="event-detail">
			<span class="label">당첨 날짜:</span> <span class="value">${formattedWinningTime}</span>
		</div>
		<div class="event-detail">
			<span class="label">보상 내용:</span> <span class="value">${event.evt_reward}</span>
		</div>
		<div class="event-detail">
			<span class="label">당첨자 수:</span> <span class="value">${event.evt_winner}</span>
		</div>
	</div>
	<hr class="hr-1" />
	<div>
		<span>${event.evt_body }</span>
	</div>
	<!-- 이미지 나오는거 고장 -->
	<div class="event-actions">
		<button class="save-event">이벤트 북마크</button>
		<button class="apply-event">이벤트 응모하기</button>
	</div>
	<div class="align-left">
		<div>댓글 <!-- join 한 recommend 갯수 적어주기 --></div>
		<div>작성자 : ${sessionScope.USER.user_id }</div>
		<div>
			<input class="comment" type="text" placeholder="댓글을 입력하세요">
		</div>
		<c:if test="${event.evt_recommend > 1 }">
		댓글 표시
		</c:if>
	</div>
</div>
<div class="margin"></div>