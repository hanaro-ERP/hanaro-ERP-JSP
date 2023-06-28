package Service.evaluators;

import java.util.List;

import DAO.TransactionDAO;
import DTO.CreditScoringDTO;
import DTO.TransactionDTO;

public class DepositHistoryEvaluator extends EvaluatorParent implements EvaluatorInterface<List> {

	@Override
	public List<TransactionDTO> getTarget(CreditScoringDTO creditScoringDTO) {
		TransactionDAO transactionDAO = new TransactionDAO();

		return transactionDAO.getThreeMonthsTransactionListByCustomerId(creditScoringDTO);
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		List<TransactionDTO> transactions = getTarget(creditScoringDTO);

		int numberOfDeposit = 0;
		int numberOfWithdraw = 0;
		for (TransactionDTO transaction : transactions) {
			if (transaction.getTransactionType().equals("입금")) {
				numberOfDeposit = numberOfDeposit + 1;
			} else {
				numberOfWithdraw = numberOfWithdraw + 1;
			}
		}

		int difference = numberOfDeposit - numberOfWithdraw;
		if (difference >= 100) {
			score = 0;
		} else if (difference >= 80) {
			score = 20;
		} else if (difference >= 60) {
			score = 40;
		} else if (difference >= 40) {
			score = 60;
		} else if (difference >= 20) {
			score = 80;
		} else {
			score = 100;
		}

		return (int) (score * getWeightForDepositHistory());
	}

}
