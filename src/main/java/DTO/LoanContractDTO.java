package DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanContractDTO extends PaginationDTO {
	private int loanContractId;
	private int loanId;
	private int customerId;
	private int employeeId;
	private Timestamp startDate;
	private String[] StartDateString;
	private Timestamp muturityDate;
	private String paymentMethod;
	private long loanAmount;
	private long balance;
	private int paymentDate;
	private Date latePaymentDate;
	private long delinquentAmount;
	private int guarantorId;
	private float interestRate;
	private String loanType;
	private String loanName;
	private String collateralDetails;
	private String employeeName;
	private String customerName;
	private String guarantorName;
	private int[] balanceRange;
	private String[] balanceList;
	private int latePaymentPeriod;
	private String balanceString;
	private String delinquentAmountString;
	private String[] muturityDateList;
	private String muturityDateString;
	private int gracePeriod;
}
