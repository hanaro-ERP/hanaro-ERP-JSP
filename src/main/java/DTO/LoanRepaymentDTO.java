package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanRepaymentDTO {
	private int loanRepaymentId;
	private int loanContractId;
	private int accountId;
	private Timestamp tradeDatetime;
	private long tradeAmount;
	private boolean agent;
	private long loanAmount;
	private String accountNumber;
	private String customerName;
	private String employeeName;
}
