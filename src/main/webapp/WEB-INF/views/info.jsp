<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<body>
	<header>
		<nav>
			<ul>
				<li>이벤트
					<ul>
						<li><a href="${rootPath }/board/events/latest">최신이벤트</a></li>
						<li><a href="${rootPath }/board/events/popular">인기이벤트</a></li>
						<li><a href="${rootPath }/board/events/closing-soon">마감임박</a></li>
						<li><a href="${rootPath }/board/events/announcing-soon">발표임박</a></li>
					</ul>
				</li>
				<li>글쓰기
					<ul>
						<li><a href="${rootPath }/board/event-write">이벤트등록</a></li>
						<li><a href="#">정보공유</a></li>
						<li><a href="#">무료나눔</a></li>
						<li><a href="#">쿠폰장터</a></li>
					</ul>
				</li>
				<li>마이팡
					<ul>
						<li><a href="#">내 응모함</a></li>
						<li><a href="#">알림 서비스</a></li>
						<li><a href="#">북마크</a></li>
						<li><a href="#">정보수정</a></li>
					</ul>
				</li>
				<li>팡캐시
					<ul>
						<li><a href="#">출석체크</a></li>
						<li><a href="#">머니룰렛</a></li>
						<li><a href="#">충전소</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<img src="${rootPath }/static/images/logo.png" alt="logo" />
		<form action="${rootPath }/board/search">
			<div>
				<input type="text" placeholder="검색어를 입력하세요" name="search" />
			</div>
		</form>
	</header>
	<section class="main">
		<div class="button-container">
			<a href="${rootPath }/board/big-event">
				<button class="btn-hover color-1 bigEventBtn">
					대박이벤트<span class="firework"></span> <span class="firework"></span>
					<span class="firework"></span> <span class="firework"></span> <span
						class="firework"></span>
				</button>
			</a> <img class="tales-1"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<a href="${rootPath }/board/minor-event">
				<button class="btn-hover color-2 minorEventBtn">
					소소한 이벤트<span class="firework"></span> <span class="firework"></span>
					<span class="firework"></span> <span class="firework"></span> <span
						class="firework"></span>
				</button>
			</a> <img class="tales-2"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<a href="${rootPath }/board/benefit">
				<button class="btn-hover color-3 benefitBtn">
					혜택<span class="firework"></span> <span class="firework"></span> <span
						class="firework"></span> <span class="firework"></span> <span
						class="firework"></span>
				</button>
			</a> <img class="tales-3"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<a href="${rootPath }/board/community">
				<button class="btn-hover color-4 communityBtn">
					커뮤니티<span class="firework"></span> <span class="firework"></span> <span
						class="firework"></span> <span class="firework"></span> <span
						class="firework"></span>
				</button>
			</a> <img class="tales-4"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<button class="btn-hover color-5">
				<span class="firework"></span> <span class="firework"></span> <span
					class="firework"></span> <span class="firework"></span> <span
					class="firework"></span>
			</button>
			<img class="tales-5"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<button class="btn-hover color-6">
				★<span class="firework"></span> <span class="firework"></span> <span
					class="firework"></span> <span class="firework"></span> <span
					class="firework"></span>
			</button>
			<img class="tales-6"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="button-container">
			<button class="btn-hover color-7">
				♥<span class="firework"></span> <span class="firework"></span> <span
					class="firework"></span> <span class="firework"></span> <span
					class="firework"></span>
			</button>
			<img class="tales-7"
				src="${rootPath }/static/images/balloon_tales.png" alt="tales" />
		</div>
		<div class="cloud type-1"></div>
		<div class="cloud type-2"></div>
		<div class="cloud type-3"></div>
		<div class="cloud type-4"></div>
		<div class="cloud type-5"></div>
	</section>
</body>