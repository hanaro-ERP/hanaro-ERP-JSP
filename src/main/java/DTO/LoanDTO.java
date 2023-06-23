package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanDTO {
	private int loanId;
	private int duration;
	private long amount;
	private float interestRate;
}
