package util;

import java.time.LocalDate;

public class CustomerUtil {
	public String InvertDash(String[] array) {
		String phoneNumber = String.join("-", array);

		if (phoneNumber.equals("-") || phoneNumber.equals("--")) {
			return "";
		}
		return phoneNumber;
	}

	public boolean convertIntToGender(int gender) {
		if(gender % 2 == 0) //여성
			return true;
		else
			return false;
	}
	
	public boolean convertGenderToBinary(String gender) {
		if (gender.equals("여성"))
			return true;
		else
			return false;
	}

	public String convertBinaryToGender(Boolean gender) {
		if (gender)
			return "여성";
		else
			return "남성";
	}	
	
	public int getAgeFromIdentification(String id, String num) {
		int year = Integer.parseInt(id.substring(0, 2));

		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		
		int parameter = 1900;
		if (Integer.parseInt(num) > 2)
			parameter = 2000;
		
		int age = currentYear - (year+parameter);
                
		return age;
	}
}
