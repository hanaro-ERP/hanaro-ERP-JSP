package Service.evaluators;

import java.util.List;

import DAO.LoanContractDAO;
import DTO.CreditScoringDTO;
import DTO.LoanContractDTO;

public class LoanBalanceEvaluator extends EvaluatorParent implements EvaluatorInterface<List> {
	private final int MAX_BALANCE_STANDARD = 200000000;

	@Override
	public List<LoanContractDTO> getTarget(CreditScoringDTO creditScoringDTO) {
		LoanContractDAO loanContractDAO = new LoanContractDAO();

		return loanContractDAO.getLoanContractByCustomerId(creditScoringDTO);
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		List<LoanContractDTO> loanContracts = getTarget(creditScoringDTO);

		long totalBalance = 0;
		for (LoanContractDTO loanContract : loanContracts) {
			totalBalance = totalBalance + loanContract.getBalance();
		}

		if (totalBalance >= MAX_BALANCE_STANDARD) {
			score = 0;
		} else if (totalBalance >= MAX_BALANCE_STANDARD / 2) {
			score = 20;
		} else if (totalBalance >= MAX_BALANCE_STANDARD / 4) {
			score = 40;
		} else if (totalBalance >= MAX_BALANCE_STANDARD / 10) {
			score = 60;
		} else if (totalBalance >= MAX_BALANCE_STANDARD / 20) {
			score = 80;
		} else {
			score = 100;
		}

		return (int) (score * getWeightForField("LOAN_BALANCE"));
	}

}
