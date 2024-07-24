<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<style>
.wrap {
	width: 1200px;
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

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 50px;
}

th {
	background: #555;
	color: #fff;
	padding: 5px 0px;
	text-align: center;
}

td {
	padding: 5px 0px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="wrap">
		<nav>
			<ul>
				<li><a href="/registerweb/create">회원 가입 테이블 생성</a></li>
				<li><a href="/registerweb/register">회원 가입</a></li>
				<li><a href="/registerweb/login">로그인</a></li>
				<li><a href="/registerweb/logout_action">로그아웃</a></li>
				<li><a href="/registerweb/">홈으로</a></li>
				<li><a href="/registerweb/memberList">회원목록</a></li>
				<li><a href="#">다른 메뉴</a></li>
				
			</ul>
		</nav>
		<h1>홈</h1>
		<h4>로그인한 유저: ${login_id}</h4><h4> 회원타입: ${login_userType}</h4>
		<table>
			<thead>
				<tr>
                    <th>idx</th>
                    <th>회원타입</th>
                    <th>이름</th>
                    <th>아이디</th>
                    <th>성별</th>
                    <th>주소</th>
                    <th>생성일</th>
                    <th>디테일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				 ${memberList}
			</tbody>
		</table>
	</div>
</body>
</html>