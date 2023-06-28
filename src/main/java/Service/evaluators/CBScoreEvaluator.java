package Service.evaluators;

import DAO.CustomerDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;

public class CBScoreEvaluator extends EvaluatorParent implements EvaluatorInterface<String> {

	@Override
	public String getTarget(CreditScoringDTO creditScoringDTO) {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(creditScoringDTO.getCustomerId());

		return customerDTO.getCredit();
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		String CBScore = getTarget(creditScoringDTO);
		int grade = Character.getNumericValue(CBScore.charAt(0));

		switch (grade) {
		case 1:
			score = 100;
			break;
		case 2:
			score = 60;
			break;
		case 3:
			score = 30;
			break;
		case 4:
			score = 10;
			break;
		case 5:
			score = 0;
			break;
		}

		return (int) (score * getWeightForCBScore());
	}

}
