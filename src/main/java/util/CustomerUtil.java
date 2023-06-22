package util;

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
}

