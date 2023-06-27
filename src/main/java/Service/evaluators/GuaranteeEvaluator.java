package Service.evaluators;

import DTO.CreditScoringDTO;

public class GuaranteeEvaluator extends EvaluatorParent implements EvaluatorInterface<CreditScoringDTO> {

	@Override
	public CreditScoringDTO getTarget(CreditScoringDTO creditScoringDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForGuarantee());
	}

}
