package Service.evaluators;

import java.util.List;

import DAO.TransactionDAO;
import DTO.CreditScoringDTO;
import DTO.TransactionDTO;

public class IncomeEvaluator extends EvaluatorParent implements EvaluatorInterface<List<TransactionDTO>> {
	private final int MAX_INCOME_STANDARD = 30000000;

	@Override
	public List<TransactionDTO> getTarget(CreditScoringDTO creditScoringDTO) {
		TransactionDAO transactionDAO = new TransactionDAO();

		return transactionDAO.getThreeMonthsTransactionListByCustomerId(creditScoringDTO);
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		List<TransactionDTO> transactions = getTarget(creditScoringDTO);

		long income = 0;
		for (TransactionDTO transaction : transactions) {
			if (transaction.getTransactionType().equals("입금")) {
				income = income + transaction.getTransactionAmount();
			}
		}
		long monthlyIncome = income / 3;

		if (monthlyIncome >= MAX_INCOME_STANDARD) {
			score = 100;
		} else if (monthlyIncome >= MAX_INCOME_STANDARD / 3) {
			score = 80;
		} else if (monthlyIncome >= MAX_INCOME_STANDARD / 5) {
			score = 60;
		} else if (monthlyIncome >= MAX_INCOME_STANDARD / 10) {
			score = 40;
		} else if (monthlyIncome >= MAX_INCOME_STANDARD / 15) {
			score = 20;
		} else {
			score = 0;
		}

		return (int) (score * getWeightForField("INCOME"));
	}

}