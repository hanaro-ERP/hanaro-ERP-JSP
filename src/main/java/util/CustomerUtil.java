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
	
	public int getAgeFromIdentification(String id) {
		int year = Integer.parseInt(id.substring(0, 2));
		int month = Integer.parseInt(id.substring(2, 4));
		int day = Integer.parseInt(id.substring(4, 6));

		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();

		int age = currentYear - (year+1900);
                
		return age;
	}
}
