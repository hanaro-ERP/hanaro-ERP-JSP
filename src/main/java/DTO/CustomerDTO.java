package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerDTO {
	private int customerId;
	private int employeeId;
	private int bankId;
	private String customerName;
	private String grade;
	private int age;
	private boolean gender;
	private String phoneNumber;
	private String address;
	private String jobCode;
	private String country;
	private boolean disability;
	private float risk;
	private int credit;
}