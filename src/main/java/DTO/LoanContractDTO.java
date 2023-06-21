package DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanContractDTO {
	private int loanContractId;
	private int loanId;
	private int customerId;
	private int employeeId;
	private Timestamp startDate;
	private Timestamp muturityDate;
	private String paymentMethod;
	private long balance;
	private Date paymentDate;
	private long delinquentAmount;
	private int guarantorId;
	private long interestRate;
}
