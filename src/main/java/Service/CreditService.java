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

import util.EncryptUtil;
import util.KeyUtil;

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

	public static void main(String[] args) throws Exception {
		CreditScoringDTO creditScoringDTO = new CreditScoringDTO();
		creditScoringDTO.setCustomerId(300);
		creditScoringDTO.setLoanAmount(10000000);
		creditScoringDTO.setLoanDuration(60);
		CreditService creditService = new CreditService();
		creditService.setCreditScore(creditScoringDTO);
		System.out.println(creditService.getCreditScore());

		EncryptUtil encryptUtil = new EncryptUtil();
		String string = "111111-1111111";
		String encrypted = encryptUtil.encrypt(string);
		String decrypted = encryptUtil.decrypt(encrypted);

		System.out.println(encrypted);
		System.out.println(decrypted);

		System.out.println("Key: " + KeyUtil.loadKey(
				"/Users/oxboxx/Documents/Projects/Goddess/hanaro-ERP-JSP/src/main/webapp/WEB-INF/key/key.pem"));
		System.out.println("IV: " + KeyUtil
				.loadKey("/Users/oxboxx/Documents/Projects/Goddess/hanaro-ERP-JSP/src/main/webapp/WEB-INF/key/iv.pem"));
		System.out.println("Pepper: " + KeyUtil.loadKey(
				"/Users/oxboxx/Documents/Projects/Goddess/hanaro-ERP-JSP/src/main/webapp/WEB-INF/key/pepper.pem"));
	}
}