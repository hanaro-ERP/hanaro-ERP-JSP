package Service.evaluators;

import DAO.AccountDAO;
import DTO.CreditScoringDTO;

public class PropertyEvaluator extends EvaluatorParent implements EvaluatorInterface<Long> {
	private final long MAX_PROPERTY_STANDARD = 1000000000L;

	@Override
	public Long getTarget(CreditScoringDTO creditScoringDTO) {
		AccountDAO accountDAO = new AccountDAO();

		return accountDAO.getTotalBalance(creditScoringDTO);
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		long totalBalance = getTarget(creditScoringDTO);

		if (totalBalance >= MAX_PROPERTY_STANDARD) {
			score = 100;
		} else if (totalBalance >= MAX_PROPERTY_STANDARD / 2) {
			score = 80;
		} else if (totalBalance >= MAX_PROPERTY_STANDARD / 5) {
			score = 60;
		} else if (totalBalance >= MAX_PROPERTY_STANDARD / 10) {
			score = 40;
		} else if (totalBalance >= MAX_PROPERTY_STANDARD / 100) {
			score = 20;
		} else {
			score = 0;
		}

		return (int) (score * getWeightForProperty());
	}

}
