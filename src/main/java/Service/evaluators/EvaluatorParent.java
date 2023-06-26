package Service.evaluators;

public class EvaluatorParent {
	private final static double WEIGHT_FOR_CB_SCORE = 0.1;
	private final static double WEIGHT_FOR_PROFESSION = 0.1;
	private final static double WEIGHT_FOR_INCOME = 0.1;
	private final static double WEIGHT_FOR_AGE = 0.1;
	private final static double WEIGHT_FOR_REPAYMENT_HISTORY = 0.1;
	private final static double WEIGHT_FOR_LOAN_BALANCE = 0.1;
	private final static double WEIGHT_FOR_DEPOSIT_HISTORY = 0.1;
	private final static double WEIGHT_FOR_PROPERTY = 0.1;
	private final static double WEIGHT_FOR_GUARANTEE = 0.1;
	private final static double WEIGHT_FOR_GUARANTOR = 0.1;

	protected double getWeightForCBScore() {
		return WEIGHT_FOR_CB_SCORE;
	}

	protected double getWeightForProfession() {
		return WEIGHT_FOR_PROFESSION;
	}

	protected double getWeightForIncome() {
		return WEIGHT_FOR_INCOME;
	}

	protected double getWeightForAge() {
		return WEIGHT_FOR_AGE;
	}

	protected double getWeightForRepaymentHistory() {
		return WEIGHT_FOR_REPAYMENT_HISTORY;
	}

	protected double getWeightForLoanBalance() {
		return WEIGHT_FOR_LOAN_BALANCE;
	}

	protected double getWeightForDepsoitHistory() {
		return WEIGHT_FOR_DEPOSIT_HISTORY;
	}

	protected double getWeightForProperty() {
		return WEIGHT_FOR_PROPERTY;
	}

	protected double getWeightForGuarantee() {
		return WEIGHT_FOR_GUARANTEE;
	}

	protected double getWeightForGuarantor() {
		return WEIGHT_FOR_GUARANTOR;
	}
}
