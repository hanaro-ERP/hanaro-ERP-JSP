package DTO;

import lombok.*;

@Getter
@Setter

public class EmployeeDTO {
	private int employeeId, bankId, isAdmin;
	private String password, eName, department, position;

	public EmployeeDTO(int employeeId, String password) {
		this.employeeId = employeeId;
		this.password = password;
	}
}