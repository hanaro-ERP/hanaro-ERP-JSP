package Service.evaluators;

import DAO.CustomerDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;

public class GuarantorEvaluator extends EvaluatorParent implements EvaluatorInterface<CustomerDTO> {

	@Override
	public CustomerDTO getTarget(CreditScoringDTO creditScoringDTO) {
		CustomerDAO customerDAO = new CustomerDAO();

		return customerDAO.getCustomerByCustomerId(creditScoringDTO.getGuarantorId());
	}

	@Override
	public int calculateScore(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		CustomerDTO guarantor = getTarget(creditScoringDTO);
		String grade = guarantor.getCredit();

		if (grade.equals("1등급")) {
			score = 100;
		} else if (grade.equals("2등급")) {
			score = 80;
		} else if (grade.equals("2등급")) {
			score = 60;
		} else if (grade.equals("2등급")) {
			score = 40;
		} else if (grade.equals("2등급")) {
			score = 20;
		} else if (grade.equals("2등급")) {
			score = 0;
		}

		return (int) (score * getWeightForGuarantor());
	}

}
