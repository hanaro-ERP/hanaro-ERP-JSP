package Service.evaluators;

import java.util.List;

import DTO.CreditScoringDTO;
import DTO.LoanRepaymentDTO;

public class RepaymentHistoryEvaluator extends EvaluatorParent implements EvaluatorInterface<List> {

	@Override
	public List<LoanRepaymentDTO> getTarget(CreditScoringDTO creditScoringDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForRepaymentHistory());
	}

}
