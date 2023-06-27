package DAO;

import java.awt.font.NumericShaper.Range;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.eclipse.jdt.internal.compiler.IDebugRequestor;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import DTO.LoanContractDTO;
import DTO.RepaymentMethodDTO;
import util.DatabaseUtil;
import util.LoanUtil;

public class LoanContractDAO {

	// insert a new loan contract
	public int insertLoanContract(LoanContractDTO loanContract, int l_id, int c_id, int e_id, int numberOfYears) {
	    String SQL = "INSERT INTO loanContracts (l_id, c_id, e_id, start_date, maturity_date, "
	            + "payment_method, loan_amount, balance, payment_date, "
	            + "late_payment_date, delinquent_amount, guarantor_id, interest_rate) " 
	            + "VALUES (?, ?, ?, NOW(), DATE_ADD(NOW(), INTERVAL ? YEAR), ?, ?, ?, ?, ?, ?, ?, ?)";

	    if(loanContract.getGuarantorId() == -1) {
	    	//...
	    }
	    
	    try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	        pstmt.setInt(1, l_id);
	        pstmt.setInt(2, c_id);
	        pstmt.setInt(3, e_id);
	        pstmt.setInt(4, numberOfYears);
	        pstmt.setString(5, loanContract.getPaymentMethod());
	        pstmt.setLong(6, loanContract.getLoanAmount());
	        pstmt.setLong(7, loanContract.getBalance());
	        pstmt.setInt(8, loanContract.getPaymentDate());
	        pstmt.setDate(9, loanContract.getLatePaymentDate());
	        pstmt.setLong(10, loanContract.getDelinquentAmount());
	        pstmt.setInt(11, loanContract.getGuarantorId());
	        pstmt.setDouble(12, loanContract.getInterestRate());
	        int rowsAffected = pstmt.executeUpdate();

	        // Set the muturity_date value in the loanContract object as Timestamp
	        /*if (rowsAffected > 0) {
	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    Timestamp muturityDate = generatedKeys.getTimestamp(1);
	                    loanContract.setMuturityDate(muturityDate);
	                }
	            }
	        }*/

	        return rowsAffected;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // Database operation failed
	}

	// Update a loan contract
	public int updateLoanContract(LoanContractDTO loanContract) {
		String SQL = "UPDATE loanContracts SET l_id = ?, c_id = ?, e_id = ?, start_date = ?,"
				+ " maturity_date = ?, payment_method = ?, balance = ?, payment_date = ?,"
				+ " delinquent_amount = ?, guarantor_id = ?, interest_rate = ? WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanId());
			pstmt.setInt(2, loanContract.getCustomerId());
			pstmt.setInt(3, loanContract.getEmployeeId());
			pstmt.setTimestamp(4, loanContract.getStartDate());
			pstmt.setTimestamp(5, loanContract.getMaturityDate());
			pstmt.setString(6, loanContract.getPaymentMethod());
			pstmt.setLong(7, loanContract.getBalance());
			pstmt.setInt(8, loanContract.getPaymentDate());
			pstmt.setLong(9, loanContract.getDelinquentAmount());
			pstmt.setInt(10, loanContract.getGuarantorId());
			pstmt.setDouble(11, loanContract.getInterestRate());
			pstmt.setInt(12, loanContract.getLoanContractId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan contract
	public int deleteLoanContract(int loanContractId) {
		String SQL = "DELETE FROM loanContracts WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContractId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Fill a LoanContractDTO from a ResultSet
	private void fillLoanContractDTOFromResultSet(LoanContractDTO loanContract, ResultSet rs) throws SQLException {
		loanContract.setLoanContractId(rs.getInt("lc_id"));
		loanContract.setLoanId(rs.getInt("l_id"));
		loanContract.setCustomerId(rs.getInt("c_id"));
		loanContract.setEmployeeId(rs.getInt("e_id"));
		loanContract.setStartDate(rs.getTimestamp("start_date"));
		loanContract.setMaturityDate(rs.getTimestamp("maturity_date"));
		loanContract.setPaymentMethod(rs.getString("payment_method"));
		loanContract.setGracePeriod(rs.getInt("grace_period"));
		loanContract.setLoanAmount(rs.getLong("loan_amount"));
		loanContract.setBalance(rs.getLong("balance"));
		loanContract.setPaymentDate(rs.getInt("payment_date"));
		loanContract.setLatePaymentDate(rs.getDate("late_payment_date"));	
		loanContract.setLatePaymentDate(rs.getDate("late_payment_date"));
		loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
		loanContract.setGuarantorId(rs.getInt("guarantor_id"));
		loanContract.setInterestRate(rs.getLong("interest_rate"));
		loanContract.setLoanType(rs.getString("loan_type"));
		loanContract.setLoanName(rs.getString("loan_name"));
		loanContract.setEmployeeName(rs.getString("e_name"));
		loanContract.setCustomerName(rs.getString("c_name"));
		loanContract.setGuarantorName(rs.getString("guarantor_name"));
	}
	
	
	private void fillLoanContractDTOFromTable(LoanContractDTO loanContract, ResultSet rs) throws SQLException {
		loanContract.setLoanContractId(rs.getInt("lc_id"));
		loanContract.setLoanId(rs.getInt("l_id"));
		loanContract.setCustomerId(rs.getInt("c_id"));
		loanContract.setEmployeeId(rs.getInt("e_id"));
		loanContract.setStartDate(rs.getTimestamp("start_date"));
		loanContract.setMaturityDate(rs.getTimestamp("maturity_date"));
		loanContract.setPaymentMethod(rs.getString("payment_method"));
		loanContract.setGracePeriod(rs.getInt("grace_period"));
		loanContract.setLoanAmount(rs.getLong("loan_amount"));
		loanContract.setBalance(rs.getLong("balance"));
		loanContract.setPaymentDate(rs.getInt("payment_date"));
		loanContract.setLatePaymentDate(rs.getDate("late_payment_date"));	
		loanContract.setLatePaymentDate(rs.getDate("late_payment_date"));
		loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
		loanContract.setGuarantorId(rs.getInt("guarantor_id"));
		loanContract.setInterestRate(rs.getLong("interest_rate"));
	}

	// 모든 데이터 가져오기
	public List<LoanContractDTO> getLoanContracts() {
		String SQL = "SELECT * FROM loanContracts";
		List<LoanContractDTO> loanContracts = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanContractDTO loanContract = new LoanContractDTO();
					fillLoanContractDTOFromResultSet(loanContract, rs);
					loanContracts.add(loanContract);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanContracts;
	}

	// loanContractId 에 따라 데이터 가져오기
	public List<LoanContractDTO> getLoanContractByLoanContractId(int loanContractId) {
		List<LoanContractDTO> loanContractDTOList = new ArrayList<>();
		String SQL = "SELECT * FROM loanContracts WHERE lc_id = ?";
		
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContractId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanContractDTOList;
	}
	
	public List<LoanContractDTO> getLoanContractByDTO(LoanContractDTO loanContractDTO) {		
		StringBuilder queryBuilder = new StringBuilder("SELECT lc.*, l.loan_type, l.loan_name, e.e_name, c.c_name, c2.c_name as guarantor_name"
				+ " FROM loanContracts lc");
		queryBuilder.append(" JOIN loans l ON lc.l_id = l.l_id");
		queryBuilder.append(" JOIN customers c ON lc.c_id = c.c_id");
		queryBuilder.append(" JOIN employees e ON c.e_id = e.e_id");
		queryBuilder.append(" JOIN customers c2 ON lc.guarantor_id = c2.c_id");
		queryBuilder.append(" WHERE 1=1");
		
		if (loanContractDTO.getLoanName() != null) {
			queryBuilder.append(" AND l.loan_name LIKE ?");
		}
		if (loanContractDTO.getLoanType() != null) {
			queryBuilder.append(" AND l.loan_type LIKE ?");
		}
		if (loanContractDTO.getCustomerName() != null) {
			queryBuilder.append(" AND c.c_name LIKE ?");
		}
		if (loanContractDTO.getEmployeeName() != null) {
			queryBuilder.append(" AND e.e_name LIKE ?");
		}
		if (loanContractDTO.getStartDate() != null ) {
			queryBuilder.append(" AND lc.start_date >= ? AND lc.start_date < ?");
		}
		if (loanContractDTO.getMaturityDate() != null ) {
			queryBuilder.append(" AND lc.maturity_date >= ? AND lc.maturity_date < ?");
		}
		if (loanContractDTO.getBalanceRange()[0] != 0 || loanContractDTO.getBalanceRange()[1] != 0 ) {
			if (loanContractDTO.getBalanceRange()[0] >= 10000) {
				queryBuilder.append(" AND lc.balance >= ?");					
			}
			else {
				queryBuilder.append(" AND lc.balance >= ? AND lc.balance < ?");				
			}
		}
		if (loanContractDTO.getLatePaymentPeriod() > -1) {
			queryBuilder.append(" AND lc.late_payment_date < ?");
		}
		
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {

			int parameterIndex = 1;

			if (loanContractDTO.getLoanName() != null) {
	            pstmt.setString(parameterIndex++, "%"+ loanContractDTO.getLoanName()+ "%");
	        }
	        if (loanContractDTO.getLoanType() != null) {
	            pstmt.setString(parameterIndex++,  loanContractDTO.getLoanType() );
	        }
	        if (loanContractDTO.getCustomerName() != null) {
	            pstmt.setString(parameterIndex++,  loanContractDTO.getCustomerName() );
	        }
	        if (loanContractDTO.getEmployeeName() != null) {
	            pstmt.setString(parameterIndex++,  loanContractDTO.getEmployeeName() );
	        } 
	        if (loanContractDTO.getStartDate() != null) {
	            pstmt.setTimestamp(parameterIndex++, loanContractDTO.getStartDate());
	            
	            Timestamp nextDay = new Timestamp(loanContractDTO.getStartDate().getTime() + 24 * 60 * 60 * 1000);
	            pstmt.setTimestamp(parameterIndex++, nextDay);
	        }
	        if (loanContractDTO.getMaturityDate() != null) {
	            pstmt.setTimestamp(parameterIndex++, loanContractDTO.getMaturityDate());
	            
	            Timestamp nextDay = new Timestamp(loanContractDTO.getMaturityDate().getTime() + 24 * 60 * 60 * 1000);
	            pstmt.setTimestamp(parameterIndex++, nextDay);
	        }
			if (loanContractDTO.getBalanceRange()[0] != 0 || loanContractDTO.getBalanceRange()[1] != 0) {	
				if (loanContractDTO.getBalanceRange()[0] >= 10000) {
					pstmt.setInt(parameterIndex++, loanContractDTO.getBalanceRange()[0] * 10000);				
				}
				else {
					pstmt.setInt(parameterIndex++, loanContractDTO.getBalanceRange()[0] * 10000);
					pstmt.setInt(parameterIndex++, loanContractDTO.getBalanceRange()[1] * 10000);		
				}
			}	
			if (loanContractDTO.getLatePaymentPeriod() > -1) {
				LocalDate currentDate = LocalDate.now();

				LocalDate latePaymentLocalDate = currentDate.minusDays(loanContractDTO.getLatePaymentPeriod());
				Date latePaymentDate = new Date(latePaymentLocalDate.toEpochDay() * 24 * 60 * 60 * 1000);
				pstmt.setDate(parameterIndex++, latePaymentDate);
			}


			System.out.println(pstmt.toString());
			
			LoanUtil loanUtil = new LoanUtil();
			List<LoanContractDTO> loanContractDTOList = new ArrayList<>();			

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanContractDTO loanContracts = new LoanContractDTO();
					fillLoanContractDTOFromResultSet(loanContracts, rs);

					String balanceString = loanUtil.convertMoneyUnit(loanContracts.getBalance());
					loanContracts.setBalanceString(balanceString);

					String delinquentAmountString = loanUtil.convertMoneyUnit(loanContracts.getDelinquentAmount());
					loanContracts.setDelinquentAmountString(delinquentAmountString);
					
					String maturityDateString = loanContracts.getMaturityDate().toString().substring(0,10);
					loanContracts.setMaturityDateString(maturityDateString);
					
					loanContractDTOList.add(loanContracts);
				}
				return loanContractDTOList;
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RepaymentMethodDTO> getRepaymentMethod(String[] id) {

		System.out.println("dao getRepaymentMethod ");
		System.out.println("DAO id ="+ id[0]);
		
		StringBuilder queryBuilder = new StringBuilder("SELECT lc.*, c.identification, c.c_id"
				+ " FROM loanContracts lc");
		queryBuilder.append(" JOIN customers c ON c.c_id = lc.c_id");
		queryBuilder.append(" WHERE 1=1");

		String idString = "";
		
		if (id != null){
			idString = id[0] + "-" + id[1];

			System.out.println("idString ="+idString);
			queryBuilder.append(" AND c.identification LIKE ?");
		}
		
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			
			System.out.println("DAO pstmt ="+pstmt);
			
			if (id != null){
				System.out.println("id not null");
	            pstmt.setString(1, "%"+idString+"%");
	        }

			System.out.println(pstmt.toString());
			
			LoanContractDTO loanContractDTO = new LoanContractDTO();
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {

					System.out.println("rs");
					
					fillLoanContractDTOFromTable(loanContractDTO, rs);
				}
				
				String repaymentMethodString = loanContractDTO.getPaymentMethod();				
				List<RepaymentMethodDTO> repaymentMethodDTOList = new ArrayList<>();
				
				if (loanContractDTO.getStartDate() == null || loanContractDTO.getMaturityDate() == null) {
					  throw new IllegalArgumentException("Start date or maturity date is null.");
					}
				
				Timestamp startDate = loanContractDTO.getStartDate();
				Timestamp maturityDate = loanContractDTO.getMaturityDate();

				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(startDate);
				int startYear = startCalendar.get(Calendar.YEAR);
				int startMonth = startCalendar.get(Calendar.MONTH);

				Calendar maturityCalendar = Calendar.getInstance();
				maturityCalendar.setTime(maturityDate);
				int maturityYear = maturityCalendar.get(Calendar.YEAR);
				int maturityMonth = maturityCalendar.get(Calendar.MONTH);
				
				int yearDifference = maturityYear - startYear;
				int monthDifference = maturityMonth - startMonth;

				long totalMonthsDifference = yearDifference * 12 + monthDifference;	// 기간
				long loanAmount = loanContractDTO.getLoanAmount();	// 대출 원금
				double interestRate = loanContractDTO.getInterestRate() * 0.01;	// 대출 금리
				int gracePeriod = loanContractDTO.getGracePeriod();	// 거치 기간
				
				long repaymentAmount = 0;	// 상환금 = 상환한 원금 + 이자
				long principalPayment = 0;	// 납입 원금
				long interest = 0;	// 이자
				long cumulativePrincipalPayment = 0;	// 납입원금누계
				long balance = loanAmount;	// 남은 대출 원금
				
				// 상환 방법에 따라 다르게 계산
				if (repaymentMethodString.contains("만기")){	// 원금만기일시상환
					interest = (long) (loanAmount * interestRate * totalMonthsDifference);	// 이자
					principalPayment = 0;	// 납입 원금
					cumulativePrincipalPayment = 0; 	// 납입원금누계
					balance = loanAmount; // 남은 대출 원금
					for (int month = 1; month < totalMonthsDifference; month++) {
						RepaymentMethodDTO repaymentMethodDTO = new RepaymentMethodDTO();						
						repaymentMethodDTO.setTimes(month);
						
						repaymentAmount += interest;	// 상환금
						
						repaymentMethodDTO.setMethod(repaymentMethodString);
						repaymentMethodDTO.setRepaymentAmount(repaymentAmount);
						repaymentMethodDTO.setPrincipalPayment(principalPayment);
						repaymentMethodDTO.setInterest(interest);
						repaymentMethodDTO.setCumulativePrincipalPayment(cumulativePrincipalPayment);
						repaymentMethodDTO.setBalance(balance);
						
						repaymentMethodDTOList.add(repaymentMethodDTO);
					}
					RepaymentMethodDTO repaymentMethodDTO = new RepaymentMethodDTO();						
					
					principalPayment = loanAmount;	// 납입 원금
					cumulativePrincipalPayment = loanAmount; 	// 납입원금누계
					balance = 0; // 남은 대출 원금
					repaymentAmount += principalPayment + interest;	

					repaymentMethodDTOList.add(repaymentMethodDTO);
				}
				
				else if (repaymentMethodString.contains("원금균등")){ // 원금균등상환

					principalPayment = loanAmount / totalMonthsDifference;	// 납입 원금
					
					for (int month = 1; month <= totalMonthsDifference; month++) {
						RepaymentMethodDTO repaymentMethodDTO = new RepaymentMethodDTO();
						repaymentMethodDTO.setTimes(month);
						
						// 거치 기간 있는 경우
						if (gracePeriod > 0) {
							interest = (long) (loanAmount * interestRate * ((12*(totalMonthsDifference)/12)-(gracePeriod*12)+1) / 24);
							// 계산 해야 함
						}
						// 거치 기간 없는 경우
						else {
							interest = (long) (balance * interestRate);	// 이자
							repaymentAmount = interest + principalPayment;	// 상환금 = 이자 + 납입 원금
							cumulativePrincipalPayment += principalPayment;	// 납입원금누계
							balance -= principalPayment;	// 남은 대출 원금
						}

						repaymentMethodDTO.setMethod(repaymentMethodString);
						repaymentMethodDTO.setRepaymentAmount(repaymentAmount);
						repaymentMethodDTO.setPrincipalPayment(principalPayment);
						repaymentMethodDTO.setInterest(interest);
						repaymentMethodDTO.setCumulativePrincipalPayment(cumulativePrincipalPayment);
						repaymentMethodDTO.setBalance(balance);

						repaymentMethodDTOList.add(repaymentMethodDTO);
					}					
				}
				else {	// 원리금균등상환
					
					for (int month = 1; month <= totalMonthsDifference; month++) {
						RepaymentMethodDTO repaymentMethodDTO = new RepaymentMethodDTO();
						repaymentMethodDTO.setTimes(month);
						// 거치 기간 있는 경우
						if (gracePeriod > 0) {
							// 계산 해야 함
						}
						else {
							double rate = Math.pow((1+interestRate), totalMonthsDifference);
							repaymentAmount = (long) (loanAmount * interestRate * (rate)/((rate)-1));
							interest = (long) (balance * interestRate);
							principalPayment = repaymentAmount - interest;
							cumulativePrincipalPayment += principalPayment;
							
							if (cumulativePrincipalPayment > loanAmount) {
								long amountDifference = cumulativePrincipalPayment - loanAmount;
								cumulativePrincipalPayment = loanAmount;
								principalPayment -= amountDifference;
							}
							balance -= principalPayment;
						}
						
						repaymentMethodDTO.setMethod(repaymentMethodString);
						repaymentMethodDTO.setRepaymentAmount(repaymentAmount);
						repaymentMethodDTO.setPrincipalPayment(principalPayment);
						repaymentMethodDTO.setInterest(interest);
						repaymentMethodDTO.setCumulativePrincipalPayment(cumulativePrincipalPayment);
						repaymentMethodDTO.setBalance(balance);
						
						repaymentMethodDTOList.add(repaymentMethodDTO);
					}
				}
				
				return repaymentMethodDTOList;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
