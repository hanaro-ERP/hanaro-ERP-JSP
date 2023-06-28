package Service.evaluators;

import DAO.CustomerDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;

public class AgeEvaluator extends EvaluatorParent implements EvaluatorInterface<Integer> {

	@Override
	public Integer getTarget(CreditScoringDTO creditScoringDTO) {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(creditScoringDTO.getCustomerId());

		return customerDTO.getAge();
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		int age = getTarget(creditScoringDTO);

		if (age < 18) {
			score = 0;
		} else if (age < 25) {
			score = 20;
		} else if (age < 30) {
			score = 40;
		} else if (age < 40) {
			score = 80;
		} else if (age < 50) {
			score = 100;
		} else if (age < 60) {
			score = 80;
		} else if (age < 70) {
			score = 40;
		} else if (age < 80) {
			score = 20;
		} else {
			score = 0;
		}

		return (int) (score * getWeightForAge());
	}

}
