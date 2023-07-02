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
		WEIGHTS.put("JOB", 0.4);
		WEIGHTS.put("LOAN_BALANCE", 0.05);
		WEIGHTS.put("PROPERTY", 0.3);
	}

	public boolean isValid() {
		double sum = WEIGHTS.values().stream().mapToDouble(Double::doubleValue).sum();

		return ((int) sum) == 1;
	}

	protected double getWeightForField(String field) {
		return WEIGHTS.get(field);
	}
}
