package Service.evaluators;

import java.util.List;

import DTO.CreditScoringDTO;
import DTO.TransactionDTO;

public class DepositHistoryEvaluator extends EvaluatorParent implements EvaluatorInterface<List> {

	@Override
	public List<TransactionDTO> getTarget(CreditScoringDTO creditScoringDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;

		return (int) (score * getWeightForDepositHistory());
	}

}
