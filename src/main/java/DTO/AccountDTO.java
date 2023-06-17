package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountDTO {
	private int accountId;
	private int customerId;
	private String accountType;
	private Timestamp accountOpenDate;
	private long accountBalance;
}
