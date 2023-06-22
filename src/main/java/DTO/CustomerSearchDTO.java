package DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerSearchDTO {
	private String customerName;
	private String employeeName;
	private String bankName;
	private String jobCode;
	private Boolean gender; 
	private String strGender; 
	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;
	private String identification1;
	private String identification2;
	private String[] customerAges;	
	private String[] customerGrades;
	private String[] customerCredits;
	private String country;
	private String city;
	private String district;
	private String isOpen;
}