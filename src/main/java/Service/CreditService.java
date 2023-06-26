package Service;

import DTO.CustomerDTO;

public class CreditService {
	private String ass;
	private String bss;

	public String getASS() {
		return ass;
	}

	public void setASS(CustomerDTO CustomerDTO) {
		int score = 0;

		this.ass = convertToGrade(score);
	}

	public String getBSS() {
		return bss;
	}

	public void setBSS(CustomerDTO CustomerDTO) {
		int score = 0;

		this.bss = convertToGrade(score);
	}

	private static String convertToGrade(int score) {
		int grade = Math.min(999, (1000 - score) / 100 + 1);

		return String.valueOf(grade) + "등급";
	}

}