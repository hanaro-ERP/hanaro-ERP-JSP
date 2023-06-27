package Service.evaluators;

import DTO.CreditScoringDTO;

public class AgeEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		int age = 0;
		return age;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForAge());
	}

}
