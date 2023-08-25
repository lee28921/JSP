<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::modify</title>
	</head>
	<body>
		<h3>User1 수정</h3>
		<a href="/Ch11/">메인</a>
		<a href="/Ch11/modify.do">등록</a>

		<form action="/Ch11/modify.do" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>			
					<td><input type="text" name="uid" readonly value="${member.uid}"></td>
				</tr>
				<tr>
					<td>이름</td>			
					<td><input type="text" name="name" value="${member.name}"></td>
				</tr>
				<tr>
					<td>휴대폰</td>			
					<td><input type="text" name="hp" value="${member.hp}"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option value="사원" ${member.pos.equals("사원")?'selected' : ''}>사원</option>
							<option value="대리" ${member.pos.equals("대리")?'selected' : ''}>대리</option>
							<option value="부장" ${member.pos.equals("부장")?'selected' : ''}>부장</option>
							<option value="과장" ${member.pos.equals("과장")?'selected' : ''}>과장</option>
							<option value="차장" ${member.pos.equals("차장")?'selected' : ''}>차장</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<option value="101" ${member.dep == '101'?'selected' : ''}>영업1부</option>
							<option value="102" ${member.dep == '102'?'selected' : ''}>영업2부</option>
							<option value="103" ${member.dep == '103'?'selected' : ''}>영업3부</option>
							<option value="104" ${member.dep == '104'?'selected' : ''}>인사부</option>
							<option value="105" ${member.dep == '105'?'selected' : ''}>경영지원부</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>