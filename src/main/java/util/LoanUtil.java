package util;

public class LoanUtil {
	public String convertMoneyUnit(long money) {

		if (money >= 100000000) {
			return money/100000000 + "억원";
		} else {
			return money/10000 + "만원";
		}
	}	
	
	public String convertJobCode(String jobCode) {
		if (jobCode.equals("000"))
			return "무관";
		else if (jobCode.equals("001"))
			return "직장인";
		else if (jobCode.equals("002"))
			return "공무원";
		else if (jobCode.equals("003"))
			return "군인";
		else if (jobCode.equals("004"))
			return "금융인";
		else if (jobCode.equals("005"))
			return "전문직";
		else if (jobCode.equals("006"))
			return "의사";
		else if (jobCode.equals("007"))
			return "자영업";
		else if (jobCode.equals("100"))
			return "무직";
		else
			return "-";
	}
}
