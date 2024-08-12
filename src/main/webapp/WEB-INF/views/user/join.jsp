<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<link rel="stylesheet" href="${rootPath }/static/css/user/join.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<div class="speech-bubble">회원가입</div>
<section class="join">
	<form class="user join join-form" method="post">
		<c:if test="${JOIN_MSG == 'FAIL' }">
			<h3>회원가입에 실패했습니다</h3>
		</c:if>
		<div>
			<input type="text" placeholder="아이디" name="user_id" id="user_id"
				autocomplete='off' /> <span>*</span>
		</div>
		<div>
			<input type="password" placeholder="비밀번호" name="user_password"
				id="user_password" autocomplete='off' /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="닉네임" name="user_nick" id="user_nick"
				autocomplete='off' /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="이메일" name="user_email"
				id="user_email" autocomplete='off' /> <span>*</span>
		</div>
		<div>
			<input type="date" placeholder="생년월일 (6자리)" name="user_birth"
				id="user_birth" /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="전화번호" name="user_tel" id="user_tel"
				autocomplete='off' /> <span>*</span>
		</div>
		<div class="radio-group">
			<input type="radio" name="user_gender" value="male" id="male" checked />
			<label for="male">남성</label> <input type="radio" name="user_gender"
				value="female" id="female" /> <label for="female">여성</label>
		</div>
		<div class="radio-group">
			<input type="radio" name="user_role" value="enterprise"
				id="enterprise" /> <label for="enterprise">기업</label> <input
				type="radio" name="user_role" value="personal" id="personal" checked />
			<label for="personal">개인</label>
		</div>
		<div>
			<input type="button" value="회원가입" />
		</div>
	</form>
</section>
