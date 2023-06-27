package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RepaymentMethodDTO {
	private int times;	// 회차
	private long repaymentAmount;	// 상환금
	private long principalPayment;	// 납입 원금
	private long interest;	// 이자
	private long cumulativePrincipalPayment;	// 납입원금누계
	private long balance;	// 잔금
}
