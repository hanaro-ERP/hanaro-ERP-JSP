package Service.evaluators;

import DTO.CreditScoringDTO;

public class IncomeEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForIncome());
	}

}
