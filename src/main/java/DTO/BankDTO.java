package DTO;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankDTO {
	private int bankId;
	private String bankName;
	private String location;
	private String phoneNumber;
}
