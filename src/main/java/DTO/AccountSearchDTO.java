package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountSearchDTO extends PaginationDTO {
	private String customerName;
	private String identification1;
	private String identification2;
	private String accountNumber;
	private String depositType;
	private String[] accountOpenDate;
	private String[] depositBalance;
}