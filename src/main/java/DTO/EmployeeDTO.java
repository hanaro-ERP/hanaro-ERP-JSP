package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeDTO {
	private int employeeId;
	private int bankId;
	private String employeeName;
	private String department;
	private String position;
	private String password;
	private boolean admin;
}