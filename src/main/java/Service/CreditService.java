package Service;

import DTO.CreditScoringDTO;
import Service.evaluators.AgeEvaluator;
import Service.evaluators.CBScoreEvaluator;
import Service.evaluators.DepositHistoryEvaluator;
import Service.evaluators.EvaluatorParent;
import Service.evaluators.GuarantorEvaluator;
import Service.evaluators.IncomeEvaluator;
import Service.evaluators.JobEvaluator;
import Service.evaluators.LoanBalanceEvaluator;
import Service.evaluators.PropertyEvaluator;

public class CreditService {
	private String creditScore;

	public CreditService() {
		EvaluatorParent evaluatorParent = new EvaluatorParent();
		if (evaluatorParent.isValid() == false) {
			throw new IllegalStateException("신용평가 시스템이 정상적으로 동작하지 않습니다. 관리자에게 문의하세요.");
		}
	}

	private String convertToGrade(int score) {
		int grade = Math.min(99, (100 - score) / 10 + 1);

		return String.valueOf(grade) + "등급";
	}

	private String calculateSum(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		score += new AgeEvaluator().calculateScore(creditScoringDTO);
		score += new CBScoreEvaluator().calculateScore(creditScoringDTO);
		score += new DepositHistoryEvaluator().calculateScore(creditScoringDTO);
		score += new GuarantorEvaluator().calculateScore(creditScoringDTO);
		score += new IncomeEvaluator().calculateScore(creditScoringDTO);
		score += new LoanBalanceEvaluator().calculateScore(creditScoringDTO);
		score += new JobEvaluator().calculateScore(creditScoringDTO);
		score += new PropertyEvaluator().calculateScore(creditScoringDTO);

		return convertToGrade(score);
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(CreditScoringDTO creditScoringDTO) {
		this.creditScore = calculateSum(creditScoringDTO);
	}

	public static void main(String[] args) {
		CreditScoringDTO creditScoringDTO = new CreditScoringDTO();
		creditScoringDTO.setCustomerId(300);
		creditScoringDTO.setLoanAmount(10000000);
		creditScoringDTO.setLoanDuration(60);
		CreditService creditService = new CreditService();
		creditService.setCreditScore(creditScoringDTO);
		System.out.println(creditService.getCreditScore());
	}
}