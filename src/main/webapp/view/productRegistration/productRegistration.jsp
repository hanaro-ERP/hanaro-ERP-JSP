<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>상품가입</title>
        <script src="../components/aside/aside.js "></script>
        <script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>

        <link rel="stylesheet" href="productRegistration.css" />
    </head>

    <body>
        <%@ include file="../../components/header/header.jsp" %>
        <main>
            <%@ include file="../../components/aside/aside.jsp" %>
            <div class="mainContainer">
                <div class="innerTitle"><h1>상품 가입</h1></div>
                <div class="customerInformationContainer">
                    <div class="innerSubTitle"><h2>고객 정보</h2></div>

                    <table class="customerInformationTable">
                        <tr>
                            <th id="name">이름</th>
                            <td>
                                <input id="name" />
                            </td>

                            <th>전화번호</th>
                            <td>
                                <input id="phoneNumber" />
                            </td>
                        </tr>
                        <tr>
                            <th id="surety">보증인</th>
                            <td>
                                <input id="surety" />
                            </td>

                            <th>주민번호</th>
                            <td>
                                <input
                                    class="short"
                                    id="residentRegistrationNumber1"
                                />
                                -
                                <input
                                    class="short"
                                    id="residentRegistrationNumber2"
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>나이</th>
                            <td>
                                <input
                                    type="text"
                                    class="short"
                                    id="ageMin"
                                />세 이상
                                <input type="text" class="short" id="ageMax" />
                                세 이하
                            </td>
                            <th>성별</th>
                            <td>
                                <select>
                                    <option value="male" class="short">
                                        남성
                                    </option>
                                    <option value="female" class="short">
                                        여성
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th id="country">국가</th>
                            <td>
                                <select id="country">
                                    <option value="SouthKorea">대한민국</option>
                                    <option value="USA">미국</option>
                                    <option value="China">중국</option>
                                    <option value="Japan">일본</option>
                                </select>
                            </td>
                            <th id="address">거주지</th>
                            <td>
                                <form name="form">
                                    <select
                                        name="city"
                                        onchange="changeCounty(this.selectedIndex);"
                                    >
                                        <option value="전체">전체</option>
                                        <option value="서울">서울특별시</option>
                                        <option value="부산">부산광역시</option>
                                        <option value="대구">대구광역시</option>
                                        <option value="인천">인천광역시</option>
                                        <option value="광주">광주광역시</option>
                                        <option value="대전">대전광역시</option>
                                        <option value="울산">울산광역시</option>
                                        <option value="경기">경기도</option>
                                        <option value="강원">강원도</option>
                                        <option value="충북">충청북도</option>
                                        <option value="충남">충청남도</option>
                                        <option value="전북">전라북도</option>
                                        <option value="전남">전라남도</option>
                                        <option value="경북">경상북도</option>
                                        <option value="경남">경상남도</option>
                                        <option value="제주">제주도</option>
                                    </select>
                                    <select name="county" class="select">
                                        <option value="">전체</option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <th class="employ">담당 직원</th>
                            <td><input type="text" id="imploy" /></td>
                            <th class="office">주거래지점</th>
                            <td><input type="text" id="office" /></td>
                        </tr>
                        <tr>
                            <th class="customRank">고객 등급</th>
                            <td>
                                <select>
                                    <option value="Green">Green</option>
                                    <option value="Famil">Family</option>
                                    <option value="Hana Family">
                                        Hana Family
                                    </option>
                                    <option value="VIP">VIP</option>
                                    <option value="Hana">Hana VIP</option>
                                </select>
                            </td>
                            <th class="creditRank">신용 등급</th>
                            <td>
                                <select>
                                    <option value="credit1">1</option>
                                    <option value="credit2">2</option>
                                    <option value="credit3">3</option>
                                    <option value="credit4">4</option>
                                    <option value="credit4">5</option>
                                </select>
                                급
                            </td>
                        </tr>
                        <tr>
                            <th class="disRank">장애 등급</th>
                            <td>
                                <select>
                                    <option value="disable0">비장애</option>
                                    <option value="disable1">1급</option>
                                    <option value="disable2">2급</option>
                                    <option value="disable3">3급</option>
                                    <option value="disable4">4급</option>
                                    <option value="disable4">5급</option>
                                </select>
                            </td>
                            <th class="job">직업</th>
                            <td>
                                <select>
                                    <option value="governmentEmployee">
                                        공무원
                                    </option>
                                    <option value="personalBussiness">
                                        자영업
                                    </option>
                                    <option value="houseWife">주부</option>
                                    <option value="unemployed">무직</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <h2 class="customerInformationRow">고객정보</h2>
                    <table class="customerInfo">
                        <tr>
                            <th class="loanClassify">대출 구분</th>
                            <td>
                                <select>
                                    <option value="1">신용대출</option>
                                    <option value="2">담보대출</option>
                                </select>
                            </td>
                            <th class="productName">상품명</th>
                            <td>
                                <select>
                                    <option value="prod1" class="short">
                                        상품1
                                    </option>
                                    <option value="prod2" class="short">
                                        상품2
                                    </option>
                                    <option value="prod3" class="short">
                                        상품3
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>담보</th>
                            <td>
                                <select>
                                    <option value="house" class="short">
                                        주택
                                    </option>
                                    <option value="car" class="short">
                                        자동차
                                    </option>
                                    <option value="car" class="short">
                                        주식
                                    </option>
                                </select>
                            </td>
                            <th>담보가치</th>
                            <td>
                                <input
                                    type="text"
                                    class="short"
                                    id="collateralValue"
                                />만원
                            </td>
                        </tr>
                        <tr>
                            <th>대출 금액</th>
                            <td>
                                <input
                                    type="text"
                                    class="short"
                                    id="loanAmount"
                                />만원
                            </td>
                            <th>이자</th>
                            <td>
                                <select>
                                    <option value="simple" class="short">
                                        복리
                                    </option>
                                    <option value="compound" class="short">
                                        단리
                                    </option>
                                </select>
                                연
                                <input
                                    type="text"
                                    class="short"
                                    id="interest"
                                />
                                %
                            </td>
                        </tr>
                        <tr>
                            <th>대출 목적</th>
                            <td>
                                <select>
                                    <option value="dunno" class="short">
                                        ??
                                    </option>
                                    <option value="dunno" class="short">
                                        ??
                                    </option>
                                </select>
                            </td>
                            <th>상환방법</th>
                            <td>
                                <select>
                                    <option value="dunno" class="short">
                                        ??
                                    </option>
                                    <option value="dunno" class="short">
                                        ??
                                    </option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- <button>등록 버튼을 달아주세</button> -->
            </div>
        </main>
        <script src="productRegistration.js"></script>
    </body>
</html>
