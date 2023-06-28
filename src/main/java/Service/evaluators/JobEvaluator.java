package Service.evaluators;

import DAO.CustomerDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;

public class JobEvaluator extends EvaluatorParent implements EvaluatorInterface<String> {

	@Override
	public String getTarget(CreditScoringDTO creditScoringDTO) {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(creditScoringDTO.getCustomerId());

		return customerDTO.getJobCode();
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		String jobCode = getTarget(creditScoringDTO);

		switch (jobCode) {
		case "000":
			score = 50;
			break;
		case "001":
			score = 40;
			break;
		case "002":
			score = 90;
			break;
		case "003":
			score = 60;
			break;
		case "004":
			score = 60;
			break;
		case "005":
			score = 90;
			break;
		case "006":
			score = 100;
			break;
		case "007":
			score = 50;
			break;
		case "100":
			score = 0;
			break;
		}

		return (int) (score * getWeightForJob());
	}
}
