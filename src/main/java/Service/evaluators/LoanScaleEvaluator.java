package Service.evaluators;

import DTO.CreditScoringDTO;

public class LoanScaleEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {
	private final int MAX_AMOUNT_STANDARD = 200000000;
	private final int MAX_DURATION_STANDARD = 120;

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		int amountScore = 0;
		int durationScore = 0;
		long loanAmount = creditScoringDTO.getLoanAmount();
		int loanDuration = creditScoringDTO.getLoanDuration();

		if (loanAmount >= MAX_AMOUNT_STANDARD) {
			amountScore = 0;
		} else if (loanAmount >= MAX_AMOUNT_STANDARD / 2) {
			amountScore = 10;
		} else if (loanAmount >= MAX_AMOUNT_STANDARD / 3) {
			amountScore = 20;
		} else if (loanAmount >= MAX_AMOUNT_STANDARD / 5) {
			amountScore = 30;
		} else if (loanAmount >= MAX_AMOUNT_STANDARD / 10) {
			amountScore = 50;
		}

		if (loanDuration >= MAX_DURATION_STANDARD) {
			durationScore = 0;
		} else if (loanDuration >= MAX_DURATION_STANDARD / 2) {
			durationScore = 10;
		} else if (loanDuration >= MAX_DURATION_STANDARD / 3) {
			durationScore = 20;
		} else if (loanDuration >= MAX_DURATION_STANDARD / 4) {
			durationScore = 30;
		} else if (loanDuration >= MAX_DURATION_STANDARD / 6) {
			durationScore = 40;
		} else if (loanDuration >= MAX_DURATION_STANDARD / 12) {
			durationScore = 50;
		}

		score = amountScore + durationScore;

		return (int) (score * getWeightForLoanScale());
	}

}
