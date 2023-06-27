package Service.evaluators;

import DTO.CreditScoringDTO;

public class ProfessionEvaluator extends EvaluatorParent implements EvaluatorInterface<String> {

	@Override
	public String getTarget(CreditScoringDTO creditScoringDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForProfession());
	}

}
