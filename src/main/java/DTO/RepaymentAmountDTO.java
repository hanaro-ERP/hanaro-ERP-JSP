package DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RepaymentAmountDTO {
	private int customerId;
	private int loanProductId;
	private List<Integer> repaymentAmount;
}
