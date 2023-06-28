package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountDTO extends PaginationDTO{
	private int accountId;
	private int customerId;
	private String accountType;
	private Timestamp accountOpenDate;
	private String stringAccountOpenDate;
	private long accountBalance;
	private String stringAccountBalance;
	private String accountNumber;
	
	private String customerName;
	private String employeeName;
}