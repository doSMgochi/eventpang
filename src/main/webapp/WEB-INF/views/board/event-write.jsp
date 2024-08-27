<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />

<h1>
	  <c:choose>
        <c:when test="${not empty event}">
            이벤트 수정
            <div id="page-type" data-page-type="modify"></div> 
        </c:when>
        <c:otherwise>
            이벤트 작성
            <div id="page-type" data-page-type="write"></div>
        </c:otherwise>
    </c:choose>
</h1>

<form class="write" method="post" id="event-write"
	action="<c:choose>
                <c:when test='${not empty event}'>
                    ${rootPath}/board/event-modify
                </c:when>
                <c:otherwise>
                    ${rootPath}/board/event-write
                </c:otherwise>
            </c:choose>">
	<c:if test="${not empty event}">
		<input type="hidden" name="evt_num" value="${event.evt_num}" />
		<input type="hidden" name="evt_userid" value="${event.evt_userid}" />
		<input type="hidden" name="evt_writed_time"
			value="<fmt:formatDate value='${event.evt_writed_time}' pattern='yyyy-MM-dd'/>" />
	</c:if>
	<input type="text" name="evt_title" placeholder="이벤트명"
		value="${event.evt_title}" />

	<div class="half-box">
		<select id="firstSelect" name="category">
			<option value="" selected disabled hidden>카테고리</option>
			<option value="big-event"
				${category == 'big-event' ? 'selected' : ''}>대박이벤트</option>
			<option value="minor-event"
				${category == 'minor-event' ? 'selected' : ''}>소소한 이벤트</option>
			<option value="benefit" ${category == 'benefit' ? 'selected' : ''}>혜택</option>
			<option value="community"
				${category == 'community' ? 'selected' : ''}>커뮤니티</option>
		</select> <select id="secondSelect" name="detailCategory">
			<option selected disabled hidden>세부 카테고리</option>
			<option value="퀴즈" ${detailCategory == '퀴즈' ? 'selected' : ''}>퀴즈</option>
			<option value="작문/댓글" ${detailCategory == '작문/댓글' ? 'selected' : ''}>작문/댓글</option>
			<option value="팔로우/구독"
				${detailCategory == '팔로우/구독' ? 'selected' : ''}>팔로우/구독</option>
			<option value="공유/초대" ${detailCategory == '공유/초대' ? 'selected' : ''}>공유/초대</option>
			<option value="인증샷" ${detailCategory == '인증샷' ? 'selected' : ''}>인증샷</option>
			<option value="단순응모" ${detailCategory == '단순응모' ? 'selected' : ''}>단순응모</option>
			<option value="체험단" ${detailCategory == '체험단' ? 'selected' : ''}>체험단</option>
			<option value="설문/투표" ${detailCategory == '설문/투표' ? 'selected' : ''}>설문/투표</option>
			<option value="즉석당첨" ${detailCategory == '즉석당첨' ? 'selected' : ''}>즉석당첨</option>
			<option value="신규가입" ${detailCategory == '신규가입' ? 'selected' : ''}>신규가입</option>
			<option value="구매/샘플" ${detailCategory == '구매/샘플' ? 'selected' : ''}>구매/샘플</option>
			<option value="100%당첨"
				${detailCategory == '100%당첨' ? 'selected' : ''}>100%당첨</option>
			<option value="선착순" ${detailCategory == '선착순' ? 'selected' : ''}>선착순</option>
			<option value="출석체크" ${detailCategory == '출석체크' ? 'selected' : ''}>출석체크</option>
			<option value="앱설치" ${detailCategory == '앱설치' ? 'selected' : ''}>앱설치</option>
			<option value="공모전" ${detailCategory == '공모전' ? 'selected' : ''}>공모전</option>
			<option value="라이브방송" ${detailCategory == '라이브방송' ? 'selected' : ''}>라이브방송</option>
			<option value="기타" ${detailCategory == '기타' ? 'selected' : ''}>기타</option>
		</select>
	</div>

	<div class="half-box">
		<input type="text" name="evt_host" placeholder="주최자"
			value="${event.evt_host}" /> <input type="text" name="evt_link"
			placeholder="응모 링크" value="${event.evt_link}" />
	</div>

	<textarea name="evt_body" id="evt_body" rows="10" cols="80">${event.evt_body}</textarea>

	<input type="file" name="imageFile" id="imageFile" accept="image/*"
		style="display: none;">
	<button type="button" id="uploadImage">이미지 업로드</button>

	<input type="text" name="evt_tags" placeholder="태그"
		value="${event.evt_tags}" />

	<div class="three-box">
		<div>시작 날짜</div>
		<div>마감 날짜</div>
		<div>당첨 날짜</div>
	</div>
	<c:choose>
		<c:when test="${not empty event}">
			<div class="three-box">
				<input type="date" name="evt_start_time"
					value="<fmt:formatDate value='${event.evt_start_time}' pattern='yyyy-MM-dd'/>" />
				<input type="date" name="evt_end_time"
					value="<fmt:formatDate value='${event.evt_end_time}' pattern='yyyy-MM-dd'/>" />
				<input type="date" name="evt_winning_time"
					value="<fmt:formatDate value='${event.evt_winning_time}' pattern='yyyy-MM-dd'/>" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="three-box">
				<input type="date" name="evt_start_time" />
				<input type="date" name="evt_end_time" />
				<input type="date" name="evt_winning_time" />
			</div>
		</c:otherwise>
	</c:choose>
	<input type="number" name="evt_winner" placeholder="당첨자 수"
		value="${event.evt_winner}"> <input type="text"
		name="evt_reward" placeholder="보상 내용" value="${event.evt_reward}">
	<input type="hidden" name="evt_category" id="evt_category"> <input
		type="submit"
		value="<c:choose><c:when test='${not empty event}'>수정</c:when><c:otherwise>작성</c:otherwise></c:choose>" />
</form>
<div class="margin-bottom"></div>
