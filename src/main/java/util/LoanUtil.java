package util;

public class LoanUtil {
	public String convertMoneyUnit(long money) {

		if (money >= 100000000) {
			return money/100000000 + "억원";
		} else {
			return money/10000 + "만원";
		}
	}	
}
