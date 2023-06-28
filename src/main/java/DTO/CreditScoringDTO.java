package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreditScoringDTO {
	private int customerId;
	private String Identification;
	private int jobCode;
	private String guaranteeId;
	private String country;
	private long loanAmount;
	private int loanDuration;
	private int riskResult;
}
