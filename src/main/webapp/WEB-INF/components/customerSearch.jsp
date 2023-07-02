<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1"></head>
<body>
	<%@page import="java.util.Arrays"%>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.CustomerDTO" %>
	<main>
		<div class="innerSubTitle"><h2>고객 검색 결과</h2></div>
		<table class="searchTable" id="customerSearchTable">
			<tr>
				<th>고객 ID</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>나이</th>
				<th>성별</th>
				<th>신용 등급</th>
				<th>작업 코드</th>
				<th>고객 등급</th>
				<th>위험도</th>
			</tr>
			<!-- 받아온 유저 정보를 테이블로 나타내는 코드 -->
			<%
				String pageId = (String) request.getAttribute("pageId");
				List<CustomerDTO> findCustomers = (List<CustomerDTO>) request.getAttribute("customerList");

				if (findCustomers != null && !findCustomers.isEmpty()) {
				    for (CustomerDTO customer : findCustomers) {
			%>
			<tr>
				<td class="customerId">
				<%= customer.getCustomerId() %></td>
				<td><%= customer.getCustomerName() %></td>
				<td><%= customer.getIdentification() %>
				<td><%= customer.getAge() %></td>
				<td><%= customer.getStrGender() %>
				<td><%= customer.getCredit() %></td>
				<td><%= customer.getJobCode() %></td>
				<td><%= customer.getGrade() %></td>
				<td>더미데이터 98</td>
			</tr>
			<%
				}
			}
			%>
		</table>
		<input type="hidden" id="pageId" value="<%= pageId != null ? pageId : "" %>">
	</main>
	<script>
		const pageId = document.getElementById("pageId");
		const searchTableRows = document.querySelectorAll('#customerSearchTable tr');
		searchTableRows.forEach((item, index) => {
			if (index !== 0) {
				var firstTd = item.querySelector(".customerId");
				item.addEventListener('click', () => {
					var value = firstTd.innerHTML;
				    const url = "/customer/searchReturn?userId=" + value + "&pageId=" + pageId.value;

					window.opener.location.href = url;
		            window.close();
				});	
			}
		});	
	</script>
</body>
</html>