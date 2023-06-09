package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanRepaymentDTO extends PaginationDTO {
	private int loanRepaymentId;
	private int loanContractId;
	private int accountId;
	private Timestamp tradeDatetime;
	private long tradeAmount;
	private boolean agent;
	private long balance;
	private String accountNumber;
	private String customerName;
	private String employeeName;
	private String tradeAmountString;
	private String balanceString;
}
