package Service.evaluators;

import java.util.HashMap;
import java.util.Map;

public class EvaluatorParent {
	private static final Map<String, Double> WEIGHTS = new HashMap<>();

	static {
		WEIGHTS.put("AGE", 0.05);
		WEIGHTS.put("CB_SCORE", 0.05);
		WEIGHTS.put("DEPOSIT_HISTORY", 0.05);
		WEIGHTS.put("GUARANTOR", 0.05);
		WEIGHTS.put("INCOME", 0.05);
		WEIGHTS.put("JOB", 0.3);
		WEIGHTS.put("LOAN_BALANCE", 0.05);
		WEIGHTS.put("LOAN_SCALE", 0.05);
		WEIGHTS.put("PROPERTY", 0.3);
		WEIGHTS.put("REPAYMENT_HISTORY", 0.05);
	}

	public boolean isValid() {
		double sum = WEIGHTS.values().stream().mapToDouble(Double::doubleValue).sum();

		return ((int) sum) == 1;
	}

	protected double getWeightForField(String field) {
		return WEIGHTS.get(field);
	}

	protected double getWeightForAge() {
		return getWeightForField("AGE");
	}

	protected double getWeightForCBScore() {
		return getWeightForField("CB_SCORE");
	}

	protected double getWeightForDepositHistory() {
		return getWeightForField("DEPOSIT_HISTORY");
	}

	protected double getWeightForGuarantor() {
		return getWeightForField("GUARANTOR");
	}

	protected double getWeightForIncome() {
		return getWeightForField("INCOME");
	}

	protected double getWeightForJob() {
		return getWeightForField("JOB");
	}

	protected double getWeightForLoanBalance() {
		return getWeightForField("LOAN_BALANCE");
	}

	protected double getWeightForLoanScale() {
		return getWeightForField("LOAN_SCALE");
	}

	protected double getWeightForProperty() {
		return getWeightForField("PROPERTY");
	}

	protected double getWeightForRepaymentHistory() {
		return getWeightForField("REPAYMENT_HISTORY");
	}

}
