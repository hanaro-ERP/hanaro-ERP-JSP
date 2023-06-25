package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanSearchDTO {
	private String type;
	private String name;
	private String[] jobs;
	private String[] collaterals;
	private String[] periods;
	private String[] incomes;
	private String[] limits;
}
