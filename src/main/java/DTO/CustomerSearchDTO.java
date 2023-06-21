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
	private String phoneNumber;
	private String identification;
	private String[] selectedAges;	
	private String[] selectedGrades;
	private String[] selectedCredits;

	public CustomerSearchDTO(String customerName, String employeeName, String bankName, String jobCode, Boolean gender, String phoneNumber, String identification, String[] selectedAges, String[] selectedGrades, String[] selectedCredits) {
		this.customerName = customerName;
		this.employeeName = employeeName;
		this.bankName = bankName;
		this.jobCode = jobCode;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.identification = identification;
		this.selectedAges = selectedAges;
		this.selectedGrades = selectedGrades;
		this.selectedCredits = selectedCredits;
    }
}