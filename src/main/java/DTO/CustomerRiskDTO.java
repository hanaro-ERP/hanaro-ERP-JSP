package DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerRiskDTO {
	private int customerId;
	private int riskResult;
	private String jobCode;
	private int loanCount;
	private Date recentTransaction;
	private String country;
	private int age;
	private boolean disability;
	private Timestamp modifiedAt;
}
