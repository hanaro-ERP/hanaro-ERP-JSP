<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../components/header/header.jsp" %>
	<main>
		<%@ include file="../components/aside/aside.jsp" %> 
		메인 화면입니다.
	</main>
	<script>
		generateMenu('customer');		
	</script>
</body>
</html>
