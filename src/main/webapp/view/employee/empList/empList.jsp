<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/employee/empList/empList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.EmployeeDTO" %> <!-- EmployeeDTO의 패키지 경로에 따라 수정해야 함 -->
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer" id="container1">
			<div class="innerTitle"><h1>직원 목록</h1></div>
			<form action="${pageContext.request.contextPath}/employeeList" method="post">
				<div class="innerSubTitle"><h2>기본 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input name="employeeName" id="employeeNameSearchInput"></input>
						<div class="innerInformationRowTitle">소속 지점</div>
						<select name="bankLocation" class="innerSelectBox">
							<option value="location1">성수역점</option>
							<option value="location2">서울숲점</option>
							<option value="location3">신자양점</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">부서</div>
						<select name="department" class="innerSelectBox">
							<option value="deptLoan1">대출1팀</option>
							<option value="deptLoan2">대출2팀</option>
							<option value="deptLoan3">대출3팀</option>
						</select>
						<div class="innerInformationRowTitle">직책</div>
						<select name="position" class="innerSelectBox">
							<option value="manager">차장</option>
							<option value="assi_manager">과장</option>
							<option value="supervisor">주임</option>
						</select>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
			</form>
			<div class="searchTitle"><h1>검색 결과</h1></div>
			<table class="searchTable" id="employeeSearchTable">
				<tr>
					<th>직원 ID</th>
					<th>소속 지점</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>부서</th>
					<th>직책</th>
					<th>권한</th>
				</tr>
				<% System.out.println(request.getAttribute("name")); %>
				<%
				List<EmployeeDTO> findEmployee = (List<EmployeeDTO>)request.getAttribute("findEmployee");

				if (findEmployee != null && !findEmployee.isEmpty()) {
				    for (EmployeeDTO employee : findEmployee) {
				      %>
				      <tr>
				        <td><%= employee.getEmployeeId() %></td>
				        <td><%= employee.getBankId() %></td>
				        <td><%= employee.getEmployeeName() %></td>
				        <td><%= employee.getDepartment() %></td>
				        <td><%= employee.getPosition() %></td>
				        <td>
				        <% if(employee.isAdmin()) { %>
				        관리자
				        <% } else { %>
				        -
				        <% } %>
				        </td>
				      </tr>
				      <%
				    }
				  }
				
				else { %>
					<tr>
				        <td>-</td>
				        <td>-</td>
				        <td>-</td>
				        <td>-</td>
				        <td>-</td>
				        <td>-</td>
				        <td>-</td>
				   </tr>
				<% } %>
			</table>
		</div>
	</main>
	<script>
		generateMenu('employee', 'employeeList');		
	</script>
	<script src="${pageContext.request.contextPath}/view/employee/empList/empList.js "></script>
</body>
</html>
