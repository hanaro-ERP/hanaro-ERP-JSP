package Service;

import DTO.CreditScoringDTO;
import Service.evaluators.AgeEvaluator;
import Service.evaluators.CBScoreEvaluator;
import Service.evaluators.DepositHistoryEvaluator;
import Service.evaluators.EvaluatorParent;
import Service.evaluators.GuaranteeEvaluator;
import Service.evaluators.GuarantorEvaluator;
import Service.evaluators.IncomeEvaluator;
import Service.evaluators.LoanBalanceEvaluator;
import Service.evaluators.ProfessionEvaluator;
import Service.evaluators.PropertyEvaluator;
import Service.evaluators.RepaymentHistoryEvaluator;

public class CreditService {
	private String creditScore;

	public CreditService() {
		EvaluatorParent evaluatorParent = new EvaluatorParent();
		if (!evaluatorParent.isValid() == false) {
			throw new IllegalStateException("신용평가 시스템이 정상적으로 동작하지 않습니다. 관리자에게 문의하세요.");
		}
	}

	private String convertToGrade(int score) {
		int grade = Math.min(999, (1000 - score) / 100 + 1);

		return String.valueOf(grade) + "등급";
	}

	private String calculateSum(CreditScoringDTO creditScoringDTO) {
		int score = 0;
		score += new AgeEvaluator().calculateScore(creditScoringDTO);
		score += new CBScoreEvaluator().calculateScore(creditScoringDTO);
		score += new DepositHistoryEvaluator().calculateScore(creditScoringDTO);
		score += new GuaranteeEvaluator().calculateScore(creditScoringDTO);
		score += new GuarantorEvaluator().calculateScore(creditScoringDTO);
		score += new IncomeEvaluator().calculateScore(creditScoringDTO);
		score += new LoanBalanceEvaluator().calculateScore(creditScoringDTO);
		score += new ProfessionEvaluator().calculateScore(creditScoringDTO);
		score += new PropertyEvaluator().calculateScore(creditScoringDTO);
		score += new RepaymentHistoryEvaluator().calculateScore(creditScoringDTO);

		return convertToGrade(score);
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(CreditScoringDTO creditScoringDTO) {
		this.creditScore = calculateSum(creditScoringDTO);
	}

}