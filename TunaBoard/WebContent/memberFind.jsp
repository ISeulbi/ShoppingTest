<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>  
<%@ page import = "dbpkg.MemberVO" %>  
<% List<MemberVO> list = (List<MemberVO>)request.getAttribute("data"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberJoin</title>
<link rel="stylesheet" type="text/css" href="common.css">
</head>
<body>
	<script>
		var msg = "${msg}"
		if(msg != ""){
			alert(msg);
		}
	</script>
	<jsp:include page="header.jsp"/>
	<section class="container">
		<h3>회원 목록 조회/수정</h3>
		<table>
		<tr><th>회원 번호</th><th>회원성명</th><th>전화번호</th><th>주소</th>
		<th>가입일자</th><th>고객 등급</th><th>거주지역</th></tr>
		<% for(MemberVO x: list){ %>
		<tr><td><a href="memberModify?custno=<%=x.getCustno() %>"><%=x.getCustno() %></a></td>
		<td><%=x.getCustname() %></td><td><%=x.getPhone() %></td><td><%=x.getAddress() %></td>
		<td><%=x.getJoindate() %></td><td><%=x.getGrade() %></td><td><%=x.getCity() %></td></tr>
		<% } %>
		</table>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>