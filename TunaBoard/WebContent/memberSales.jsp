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
	<jsp:include page="header.jsp"/>
	<section class="container">
	<h3>회원 매출 조회</h3>
	<table>
		<tr><th>회원 번호</th><th>회원성명</th><th>고객 등급</th><th>매출</th></tr>
		<% if(list !=null && list.size() > 0){
			for(MemberVO x: list){ %>
		<tr><td><%=x.getCustno() %></td>
		<td><%=x.getCustname() %></td>
		<td><%=x.getGrade() %></td>
		<td><%=x.getSales() %></td></tr>
		<% }
		}%>
		</table>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>