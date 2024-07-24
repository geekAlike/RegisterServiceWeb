<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Message</title>
<style>
.wrap {
	width: 800px;
	padding: 10px;
	margin: 20px auto;
	border-radius: 10px;
	background: #f1f1f1;
}

ul, li {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
}

ul {
	display: flex;
	gap: 10px;
	justify-content: left;
}

li {
	display: inline-block;
	padding: 10px 30px;
	border: 1px solid #333;
	border-radius: 5px;
}

a {
	text-decoration: none;
	color: #333;
}

li:hover {
	background: #8e93c0;
}

li:hover a {
	color: #ffffff;
}

h1 {
	margin-bottom: 40px;
	font-size: 1.2em;
}

form {
	background: #fff;
	padding: 10px;
	border-radius: 10px;
	box-sizing: border-box;
}

input {
	width: 100%;
	line-height: 2em;
	height: 3em;
	box-sizing: border-box;
	padding-left: 10px;
	margin: 10px 5px;
}
</style>
</head>
<body>
	<div class="wrap">
		<nav>
			<ul>
				<li><a href="/registerweb/register">회원 가입</a></li>
				<li><a href="/registerweb/login">로그인</a></li>
				<li><a href="/registerweb/">홈으로</a></li>
				<li><a href="/registerweb/memberList">회원목록</a></li>
				<li><a href="#">다른 메뉴</a></li>
			</ul>
		</nav>
		

		<h1>로그인</h1>
		<form action="login_action">
			<input type="text" placeholder="아이디" value="${regId}" name="regId" />
			<input type="password" placeholder="비밀 번호" value="${password}" name="password" /> 
			<input type="submit" value="로그인" />
		</form>
		
	</div>
</body>
</html>
