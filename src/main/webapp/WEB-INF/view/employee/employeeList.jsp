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
		<%
			//이쪽 부분 script로 따로 옮기고싶은데 자꾸만 데이터 인식을 못해서 그냥 여기에 임시로 뒀습니다 왜 안될까요 ㅜ-ㅜ?
			String[] storeNames = {
			  "개포점", "가회점", "교남점", "금호점", "논현점", "대치점", "도곡점", "마장점", "무악점", "부암점",
			  "상왕십리점", "서울숲점", "성수역점", "성수점", "성북점", "신사점", "신자양점", "신촌점", "압구정점",
			  "역삼점", "옥수점", "왕십리점", "용답점", "이화점", "정릉점", "창신점", "청담사거리점", "청담점",
			  "청운효자점", "평창점", "하나로점"
			};
			String[] depatments = {
			  "디지털 혁신팀", "디지털 보안팀", "디지털 관리팀", "신용여신팀", "담보여신팀", "기업여신팀", "가계여신팀"
			};
			String[] positions = {
			  "대리", "사원", "과장", "차장", "부장", "이사"
			};
		%>
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
						<% for(String storeName : storeNames) { %> 
						<option value="<%= storeName %>" <% if (employeeDTO != null && storeName.equals(employeeDTO.getBankLocation())) { %>selected<% } %>><%= storeName %></option>
					  	<% } %>
					</select>
					</div>
				<div class="innerInformationRow">
					<div class="innerInformationRowTitle">부서</div>
					<select name="department" class="innerSelectBox">
						<option value="">-</option>
						<% for(String department : depatments) { %> 
						<option value="<%= department %>" <% if (employeeDTO != null && department.equals(employeeDTO.getDepartment())) { %>selected<% } %>><%= department %></option>
					  	<% } %>
					</select>
					<div class="innerInformationRowTitle">직책</div>
					<select name="position" class="innerSelectBox">
					<option value="">-</option>
						<% for(String position : positions) { %> 
						<option value="<%= position %>" <% if (employeeDTO != null && position.equals(employeeDTO.getPosition())) { %>selected<% } %>><%= position %></option>
					  	<% } %>
					</select>
				</div>
				</div>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
			<div class="searchTitle"><h1>검색 결과</h1><p><%= (employeeDTO != null && employeeDTO != null) ? "총 " + employeeDTO.getCount() + "개의 검색 결과가 있습니다." : "" %></p></div>
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
				<%
				// customerSearchDTO에서 page 값과 count 변수 추출
				int count = (employeeDTO != null && employeeDTO.getCount() != 0) ? employeeDTO.getCount() : 0;
				int pages = (employeeDTO != null && employeeDTO != null) ? employeeDTO.getPage() : 1;

				// 페이징 처리 로직
				int pageSize = 20; // 한 페이지에 표시할 레코드 수
				int totalPages = (int) Math.ceil((double) count / pageSize); // 전체 페이지 수
				int currentPage = pages; // 현재 페이지
				int startPage = Math.max(1, currentPage - ((currentPage-1) % 10)) ; // 시작 페이지
				int endPage = Math.min(startPage + 9, totalPages); // 끝 페이지
				System.out.println(count);

				// 이전 페이지와 다음 페이지 계산
				int prevPage = startPage - 1;
				int nextPage = endPage + 1;
				
				// 이전 페이지와 다음 페이지 범위 검사
				prevPage = Math.max(1, prevPage);
				nextPage = Math.min(totalPages, nextPage);
				%>
				
				<!-- 페이지 번호 표시 -->
				<div class="pagination">
					<% if (currentPage > 1) { %>
						<button type="submit" name="page" value="1"><<</button>
						<button type="submit" name="page" value="<%= prevPage %>"><</button>
					<% } %>
					
					<% for (int i = startPage; i <= endPage; i++) { %>
						<% if (i == currentPage) { %>
							<button type="submit" class="activePage" name="page" value="<%= i %>"><%= i %></button>
						<% } else { %>
							<button type="submit" name="page" value="<%= i %>"><%= i %></button>
						<% } %>
					<% } %>
					
					<% if (currentPage < totalPages) { %>
						<button type="submit" name="page" value="<%= nextPage %>">></button>
						<button type="submit" name="page" value="<%= totalPages %>">>></button>
					<% } %>
				</div>
			</form>
		</div>
	</main>
	<script>
		generateMenu('employee', 'employeeList');
	</script>
	<script src="${pageContext.request.contextPath}/js/employee/employeeList.js "></script>
</body>
</html>
