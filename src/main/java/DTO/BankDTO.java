package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankDTO extends PaginationDTO{
	private int bankId;
	private String bankName;
	private String location;
	private String phoneNumber;
	
	private String city;
	private String district;
}
