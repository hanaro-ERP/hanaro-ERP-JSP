var loanProduct = new Array();
loanProduct[0] = new Array(
	"-",
	"하나햇살론뱅크",
	"하나예금담보대출",
	"하나원큐주택담보대출"
);
loanProduct[1] = new Array(
	"-",
	"닥터클럽대출-골드",
	"로이어클럽대출",
	"하나 수의사클럽대출",
	"공무원클럽대출",
	"프리미엄 직장인론",
	"하나 새희망홀씨"
);

function changeLoan(add) {
	const selectElement = document.getElementsByName("loanProductName")[0];
    
    /* 옵션메뉴삭제 */
	for (let i = selectElement.length - 1; i >= 0; i--) {
		selectElement.options[i] = null;
	}
	/* 옵션박스추가 */
	for (let i = 0; i < loanProduct[add].length; i++) {
		selectElement.options[i] = new Option(loanProduct[add][i], loanProduct[add][i]);
	}
	
	var collateralField = document.getElementsByName("collateral")[0];
	
	// selectedIndex가 2 (신용대출)이면 담보 필드를 비활성화하고 값을 초기화합니다.
	if (add === 1) {
		collateralField.disabled = true;
		collateralField.value = "";
		
		collateralField.style.backgroundColor = "#E5E8EB";
	} else {
		collateralField.disabled = false;
		
		// 색상 변경
		collateralField.style.backgroundColor = "#fff";
	}
}
changeLoan(0);

var countyList = new Array();
countyList[0] = new Array("-");
countyList[1] = new Array(
	"",
	"강남구",
	"강동구",
	"강북구",
	"강서구",
	"관악구",
	"광진구",
	"구로구",
	"금천구",
	"노원구",
	"도봉구",
	"동대문구",
	"동작구",
	"마포구",
	"서대문구",
	"서초구",
	"성동구",
	"성북구",
	"송파구",
	"양천구",
	"영등포구",
	"용산구",
	"은평구",
	"종로구",
	"중구",
	"중랑구"
);
countyList[2] = new Array(
	"",
	"강서구",
	"금정구",
	"남구",
	"동구",
	"동래구",
	"부산진구",
	"북구",
	"사상구",
	"사하구",
	"서구",
	"수영구",
	"연제구",
	"영도구",
	"중구",
	"해운대구",
	"기장군"
);
countyList[3] = new Array(
	"",
	"남구",
	"달서구",
	"동구",
	"북구",
	"서구",
	"수성구",
	"중구",
    "달성군"
);
countyList[4] = new Array(
	"",
	"계양구",
	"남구",
	"남동구",
	"동구",
	"부평구",
	"서구",
	"연수구",
	"중구",
	"강화군",
	"옹진군"
);
countyList[5] = new Array("-", "광산구", "남구", "동구", "북구", "서구");
countyList[6] = new Array("-", "대덕구", "동구", "서구", "유성구", "중구");
countyList[7] = new Array("-", "남구", "동구", "북구", "중구", "울주군");
countyList[8] = new Array(
	"",
	"고양시",
	"과천시",
	"광명시",
	"구리시",
	"군포시",
	"남양주시",
	"동두천시",
	"부천시",
	"성남시",
	"수원시",
	"시흥시",
	"안산시",
	"안양시",
	"오산시",
	"의왕시",
	"의정부시",
	"평택시",
	"하남시",
	"가평군",
	"광주시",
	"김포시",
 	"안성시",
	"양주군",
	"양평군",
	"여주군",
	"연천군",
	"용인시",
	"이천군",
	"파주시",
	"포천시",
	"화성시"
);
countyList[9] = new Array(
	"",
	"강릉시",
	"동해시",
	"삼척시",
	"속초시",
	"원주시",
	"춘천시",
	"태백시",
	"고성군",
	"양구군",
	"양양군",
	"영월군",
	"인제군",
	"정선군",
	"철원군",
	"평창군",
	"홍천군",
	"화천군",
	"황성군"
);
countyList[10] = new Array(
	"",
	"제천시",
	"청주시",
	"충주시",
	"괴산군",
	"단양군",
	"보은군",
	"영동군",
	"옥천군",
	"음성군",
	"진천군",
	"청원군"
);
countyList[11] = new Array(
	"",
	"공주시",
	"보령시",
	"서산시",
	"아산시",
	"천안시",
	"금산군",
	"논산군",
	"당진군",
	"부여군",
	"서천군",
	"연기군",
	"예산군",
	"청양군",
	"태안군",
	"홍성군"
);
countyList[12] = new Array(
	"",
	"군산시",
	"김제시",
	"남원시",
	"익산시",
	"전주시",
	"정읍시",
	"고창군",
	"무주군",
	"부안군",
	"순창군",
	"완주군",
	"임실군",
	"장수군",
	"진안군"
);
countyList[13] = new Array(
	"",
	"광양시",
	"나주시",
	"목포시",
	"순천시",
	"여수시",
	"여천시",
	"강진군",
	"고흥군",
	"곡성군",
	"구례군",
	"담양군",
	"무안군",
	"보성군",
	"신안군",
	"여천군",
	"영광군",
	"영암군",
	"완도군",
	"장성군",
	"장흥군",
	"진도군",
	"함평군",
	"해남군",
	"화순군"
);
countyList[14] = new Array(
	"",
	"경산시",
	"경주시",
	"구미시",
	"김천시",
	"문겅시",
	"상주시",
	"안동시",
	"영주시",
	"영천시",
	"포항시",
	"고령군",
	"군위군",
	"봉화군",
	"성주군",
	"영덕군",
	"영양군",
	"예천군",
	"울릉군",
	"울진군",
	"의성군",
	"청도군",
	"청송군",
	"칠곡군"
);
countyList[15] = new Array(
	"",
	"거제시",
	"김해시",
	"마산시",
	"밀양시",
	"사천시",
	"울산시",
	"진주시",
	"진해시",
	"창원시",
	"통영시",
	"거창군",
	"고성군",
	"남해군",
	"산청군",
	"양산시",
	"의령군",
	"창녕군",
	"하동군",
	"함안군",
	"함양군",
	"합천군"
);
countyList[16] = new Array("", "서귀포시", "제주시", "남제주군", "북제주군");

function changeCounty(add) {
	const selectElement = document.getElementsByName("district")[0];
    
    /* 옵션메뉴삭제 */
	for (let i = selectElement.length - 1; i >= 0; i--) {
		selectElement.options[i] = null;
	}
	/* 옵션박스추가 */
	for (let i = 0; i < countyList[add].length; i++) {
		selectElement.options[i] = new Option(countyList[add][i], countyList[add][i]);
	}
}
