package Bean;
import java.sql.Date;
import java.sql.Timestamp;

public class LoanContract {
	int lc_id, l_id, c_id, e_id;
	Timestamp muturity_date;
	String payment_method;
	int balance;
	Date payment_date, delinquency_start;
	int delinquent_day, delinquent_amount;
	int guarantor;
	boolean has_collateral;
	int collateral_value;
	
	
	public int getLc_id() {
		return lc_id;
	}
	public void setLc_id(int lc_id) {
		this.lc_id = lc_id;
	}
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public Timestamp getMuturity_date() {
		return muturity_date;
	}
	public void setMuturity_date(Timestamp muturity_date) {
		this.muturity_date = muturity_date;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public Date getDelinquency_start() {
		return delinquency_start;
	}
	public void setDelinquency_start(Date delinquency_start) {
		this.delinquency_start = delinquency_start;
	}
	public int getDelinquent_day() {
		return delinquent_day;
	}
	public void setDelinquent_day(int delinquent_day) {
		this.delinquent_day = delinquent_day;
	}
	public int getDelinquent_amount() {
		return delinquent_amount;
	}
	public void setDelinquent_amount(int delinquent_amount) {
		this.delinquent_amount = delinquent_amount;
	}
	public int getGuarantor() {
		return guarantor;
	}
	public void setGuarantor(int guarantor) {
		this.guarantor = guarantor;
	}
	public boolean isHas_collateral() {
		return has_collateral;
	}
	public void setHas_collateral(boolean has_collateral) {
		this.has_collateral = has_collateral;
	}
	public int getCollateral_value() {
		return collateral_value;
	}
	public void setCollateral_value(int collateral_value) {
		this.collateral_value = collateral_value;
	}
}
