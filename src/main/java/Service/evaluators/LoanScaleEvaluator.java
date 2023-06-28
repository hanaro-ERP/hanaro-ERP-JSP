package Service.evaluators;

import DTO.CreditScoringDTO;

public class LoanScaleEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		long loanAmount = creditScoringDTO.getLoanAmount();
		int loanDuration = creditScoringDTO.getLoanDuration();

		return (int) (score * getWeightForLoanScale());
	}

}
