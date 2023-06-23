<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee/employeeList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.EmployeeDTO" %> <!-- EmployeeDTO의 패키지 경로에 따라 수정해야 함 -->
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<% EmployeeDTO employeeDTO = (EmployeeDTO)request.getAttribute("searchInputValue"); %>
		<div class="innerContainer" id="container1">
			<div class="innerTitle"><h1>직원 목록</h1></div>
			<form action="${pageContext.request.contextPath}/employee/list" method="post">
				<div class="innerSubTitle"><h2>기본 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input name="employeeName" id="employeeNameSearchInput" class="innerSearchInput3"
						value="<%= employeeDTO == null || employeeDTO.getEmployeeName() == null ? "" : employeeDTO.getEmployeeName() %>">
					<div class="innerInformationRowTitle">소속 지점</div>
					<select name="bankLocation" class="innerSelectBox">
						<option value="">-</option>
						<option value="성수점" <% if (employeeDTO != null && "성수점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>성수점</option>
						<option value="하나로점" <% if (employeeDTO != null && "하나로점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>하나로점</option>
						<option value="신자양점" <% if (employeeDTO != null && "신자양점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>신자양점</option>
						<option value="성수역점" <% if (employeeDTO != null && "성수역점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>성수역점</option>
						<option value="서울숲점" <% if (employeeDTO != null && "서울숲점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>서울숲점</option>
						<option value="화양동점" <% if (employeeDTO != null && "화양동점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>화양동점</option>
							<option value="청담사거리점" <% if (employeeDTO != null && "청담사거리점".equals(employeeDTO.getBankLocation())) { %>selected<% } %>>청담사거리점</option>
					</select>
				</div>
				<div class="innerInformationRow">
					<div class="innerInformationRowTitle">부서</div>
					<select name="department" class="innerSelectBox">
						<option value="">-</option>
						<option value="디지털 혁신팀" <% if (employeeDTO != null && "디지털 혁신팀".equals(employeeDTO.getDepartment())) { %>selected<% } %>>디지털 혁신팀</option>
						<option value="디지털 보안팀" <% if (employeeDTO != null && "디지털 보안팀".equals(employeeDTO.getDepartment())) { %>selected<% } %>>디지털 보안팀</option>
						<option value="디지털 관리팀" <% if (employeeDTO != null && "디지털 관리팀".equals(employeeDTO.getDepartment())) { %>selected<% } %>>디지털 관리팀</option>
					</select>
					<div class="innerInformationRowTitle">직책</div>
					<select name="position" class="innerSelectBox">
						<option value="">-</option>
						<option value="사원" <% if (employeeDTO != null && "사원".equals(employeeDTO.getPosition())) { %>selected<% } %>>사원</option>
						<option value="대리" <% if (employeeDTO != null && "대리".equals(employeeDTO.getPosition())) { %>selected<% } %>>대리</option>
						<option value="팀장" <% if (employeeDTO != null && "팀장".equals(employeeDTO.getPosition())) { %>selected<% } %>>팀장</option>
						<option value="차장" <% if (employeeDTO != null && "차장".equals(employeeDTO.getPosition())) { %>selected<% } %>>차장</option>
						<option value="부장" <% if (employeeDTO != null && "부장".equals(employeeDTO.getPosition())) { %>selected<% } %>>부장</option>
						<option value="부행장" <% if (employeeDTO != null && "부행장".equals(employeeDTO.getPosition())) { %>selected<% } %>>부행장</option>
						<option value="상무" <% if (employeeDTO != null && "상무".equals(employeeDTO.getPosition())) { %>selected<% } %>>상무</option>
						<option value="전무" <% if (employeeDTO != null && "전무".equals(employeeDTO.getPosition())) { %>selected<% } %>>전무</option>
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
				<%
				List<EmployeeDTO> findEmployee = (List<EmployeeDTO>)request.getAttribute("findEmployee");

				if (findEmployee != null && !findEmployee.isEmpty()) {
				    for (EmployeeDTO employee : findEmployee) {
				      %>
				      <tr>
				        <td><%= employee.getEmployeeId() %></td>
				        <td><%= employee.getBankLocation() %></td>
				        <td><%= employee.getEmployeeName() %></td>
				        <td><%= employee.getPhoneNumber() %>
				        <td><%= employee.getDepartment() %></td>
				        <td><%= employee.getPosition() %></td>
				        <td>
				        <% if(employee.isAdmin()) { %>
				        관리자
				        <% } else { %>
				        행원
				        <% } %>
				        </td>
				      </tr>
				      <%
				    }
				  }
				
				%>
			</table>
		</div>
	</main>
	<script>
		generateMenu('employee', 'employeeList');		
	</script>
	<script src="${pageContext.request.contextPath}/js/employee/employeeList.js "></script>
</body>
</html>
