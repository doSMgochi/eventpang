<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />

<div class="speech-bubble">회원정보수정</div>
<section class="join">
	<form class="user join modify-form" method="post">
		<c:if test="${not empty MODIFY_MSG}">
			<h3 style="color: red;">${MODIFY_MSG}</h3>
		</c:if>
		<div>
			<input type="text" placeholder="아이디" name="user_id" id="user_id"
				value="${USER.user_id }" readonly /> <span>*</span>
		</div>
		<div>
			<input type="password" placeholder="현재 비밀번호" name="current_password"
				id="current_password" autocomplete='off' required /> <span>*</span>
		</div>
		<div>
			<input type="password" placeholder="변경할 비밀번호" name="new_password"
				id="new_password" autocomplete='off' /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="닉네임" name="user_nick" id="user_nick"
				autocomplete='off' value="${USER.user_nick }" /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="이메일" name="user_email"
				id="user_email" autocomplete='off' value="${USER.user_email }" /> <span>*</span>
		</div>
		<div>
			<input type="date" placeholder="생년월일 (6자리)" name="user_birth"
				id="user_birth" value="${USER.user_birth }" /> <span>*</span>
		</div>
		<div>
			<input type="text" placeholder="전화번호" name="user_tel" id="user_tel"
				autocomplete='off' value="${USER.user_tel }" /> <span>*</span>
		</div>
		<div class="radio-group">
			<c:choose>
				<c:when test="${not empty USER && USER.user_gender eq 'male'}">
					<input type="radio" name="user_gender" value="male" id="male"
						checked />
					<label for="male">남성</label>
					<input type="radio" name="user_gender" value="female" id="female" />
					<label for="female">여성</label>
				</c:when>
				<c:otherwise>
					<input type="radio" name="user_gender" value="male" id="male" />
					<label for="male">남성</label>
					<input type="radio" name="user_gender" value="female" id="female"
						checked />
					<label for="female">여성</label>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="radio-group">
			<c:choose>
				<c:when test="${not empty USER && USER.user_role eq 'enterprise'}">
					<input type="radio" name="user_role" value="enterprise"
						id="enterprise" checked />
					<label for="enterprise">기업</label>
					<input type="radio" name="user_role" value="personal" id="personal" />
					<label for="personal">개인</label>
				</c:when>
				<c:otherwise>
					<input type="radio" name="user_role" value="enterprise"
						id="enterprise" />
					<label for="enterprise">기업</label>
					<input type="radio" name="user_role" value="personal" id="personal"
						checked />
					<label for="personal">개인</label>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<input type="button" value="정보수정" />
		</div>
		<div>
			<a href="${rootPath }/user/quit">회원탈퇴</a>
		</div>
	</form>
</section>