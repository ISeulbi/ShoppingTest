<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberJoin</title>
<link rel="stylesheet" type="text/css" href="common.css">
	<script>
		var msg = "${msg}"
		if(msg != ""){
			alert(msg);
			clkFind()
		}
		
		function chkSubmit(){
			var frm = document.frm;
			if(frm.custno.value == ""){
				alert("회원번호가 입력되지 않았습니다.")
				frm.custno.focus();
				return false;
				} else if(frm.custname.value == ""){
					alert("회원성명이 입력되지 않았습니다.")
					frm.custname.focus();
					return false;
				} else if(frm.phone.value == ""){
					alert("전화번호가 입력되지 않았습니다.")
					frm.phone.focus();
					return false;
				} else if(frm.address.value == ""){
					alert("회원주소가 입력되지 않았습니다.")
					frm.address.focus();
					return false;
				} else if(frm.joindate.value == ""){
					alert("가입일자가 입력되지 않았습니다.")
					frm.joindate.focus();
					return false;
				} else if(frm.grade.value == ""){
					frm.grade.focus();
					alert("고객등급이 입력되지 않았습니다.")
					return false;
				} else if(frm.city.value == ""){
					alert("도시코드가 입력되지 않았습니다.")
					frm.city.focus();
					return false;
				}
				
				return ture;
			}
			
			function clkFind(){
				location.href="memberFind"
			}
		</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<section class="container">
		<h3>홈쇼핑 회원 정보 수정</h3>
		<form action="memberModify" method="post" name="frm" onsubmit="return chkSubmit();">
		<table>
			<tr><td>회원번호</td><td><input type="text" name="custno" value="${vo.custno }" readonly></td></tr>
			<tr><td>회원성명</td><td><input type="text" name="custname" value="${vo.custname }"></td></tr>
			<tr><td>회원전화</td><td><input type="text" name="phone" value="${vo.phone }"></td></tr>
			<tr><td>화원주소</td><td><input type="text" name="address" value="${vo.address }"></td></tr>
			<tr><td>가입일자</td><td><input type="text" name="joindate" value="${vo.joindate }"></td></tr>
			<tr><td>고객등급[A:VIP, B:일반, C:직원]</td><td><input type="text" name="grade" value="${vo.grade }" maxlength="1"></td></tr>
			<tr><td>도시코드</td><td><input type="text" name="city" value="${vo.city }" maxlength="2"></td></tr>
			<tr><td colspan="2"><input type="submit" value="수정">
								<input type="button" value="조회" onclick="clkFind()"></td></tr>
		</table>
		</form>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>