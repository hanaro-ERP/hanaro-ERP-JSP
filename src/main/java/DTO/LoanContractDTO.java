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
	private long loanAmount;
	private long balance;
	private Date paymentDate;
	private Date latePaymentDate;
	private long delinquentAmount;
	private int guarantorId;
	private long interestRate;
	private String loanType;
	private String loanName;
	private String employeeName;
	private String customerName;
	private String guarantorName;
	private int[] balanceRange;
	private int latePaymentPeriod;
}
