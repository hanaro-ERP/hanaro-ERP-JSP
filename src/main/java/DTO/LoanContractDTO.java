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
	private Timestamp muturityDate;
	private String paymentMethod;
	private long balance;
	private Date paymentDate;
	private Date delinquencyStart;
	private int delinquentDay;
	private long delinquentAmount;
	private int guarantorId;
	private boolean hasCollateral;
	private long collateralValue;
}
