package Service.evaluators;

import DAO.LoanContractDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;

public class LoanBalanceEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {
	private final long MAX_PROPERTY_STANDARD = 1000000000L;

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		LoanContractDAO loanContractDAO = new LoanContractDAO();
		CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(creditScoringDTO.getCustomerId());

		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForLoanBalance());
	}

}
