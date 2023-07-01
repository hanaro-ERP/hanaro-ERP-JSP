package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TransactionDTO {
	private int transactionId;
	private int accountId;
	private long transactionAmount;
	private String transactionType;
	private String transactionLocation;
	private Timestamp transactionDate;
	private String stringTransactionDate;
	private String accountNumber;
	private String customerName;
	private long balance;
}