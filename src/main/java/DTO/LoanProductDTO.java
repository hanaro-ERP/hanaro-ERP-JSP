package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoanProductDTO extends PaginationDTO {
	private int loanId;
	private String loanType;
	private String loanName;
	private int minDuration;
	private int maxDuration;
	private long minAmount;
	private long maxAmount;
	private float maxRate;
	private float minRate;
	private String job;
	private String collateral;
	private long income;
	private int subscriberCount;
}
