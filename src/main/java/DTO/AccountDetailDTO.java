package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountDetailDTO {
	private int accountId;
	private long accountAmount;
	private String accountType;
	private String accountLocation;
}
