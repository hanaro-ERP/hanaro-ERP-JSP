<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여신 상품 삭제</title>
<link rel="icon" href="${pageContext.request.contextPath}/public/images/favicon.svg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
</head>
<body style="padding: 0 20px">
<%@page import="DTO.LoanProductDTO"%>
<% 
	LoanProductDTO loanProduct =(LoanProductDTO) request.getAttribute("loanProduct"); 
	String del = loanProduct.getDel();
%>
<script>
if ("<%= del %>" !== null) {
	if ("<%= del %>" === "1") {
		alert("여신 상품이 성공적으로 삭제되었습니다.");
		window.opener.location.href = "/navigation/loanList";
		window.close();
	} else if ("<%= del %>" === "-1") {
		alert("여신 상품 삭제에 실패하였습니다.");
		window.opener.location.href = "/navigation/loanList";
		window.close();
	}
}
</script>
</body>
</html>