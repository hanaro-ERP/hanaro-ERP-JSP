package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanRegistrationDTO {
	private String productName;
	private String loanType;
	private String collateralType;
	private String jobCode;
	private long loanIncome;
	private long loanMinLimit;
	private long loanMaxLimit;
	private int loanMinPeriod;
	private int loanMaxPeriod;
	private float loanMinRate;
	private float loanMaxRate;
}
