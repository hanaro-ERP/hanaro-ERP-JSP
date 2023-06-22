package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import DTO.LoanContractDTO;
import DTO.LoanDTO;
import util.DatabaseUtil;

public class LoanContractDAO {

	// insert a new loan contract
	public int insertLoanContract(LoanContractDTO loanContract) {
		String SQL = "INSERT INTO loancontract (lc_id, l_id, c_id, e_id, start_date, muturity_date, payment_method, balance, payment_date, "
				+ "delinquent_amount, guarantor_id, interest_rate) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanContractId());
			pstmt.setInt(2, loanContract.getLoanId());
			pstmt.setInt(3, loanContract.getCustomerId());
			pstmt.setInt(4, loanContract.getEmployeeId());
			pstmt.setTimestamp(5, loanContract.getStartDate());
			pstmt.setTimestamp(6, loanContract.getMuturityDate());
			pstmt.setString(7, loanContract.getPaymentMethod());
			pstmt.setLong(8, loanContract.getBalance());
			pstmt.setDate(9, loanContract.getPaymentDate());
			pstmt.setLong(10, loanContract.getDelinquentAmount());
			pstmt.setInt(11, loanContract.getGuarantorId());
			pstmt.setLong(12, loanContract.getInterestRate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Update a loan contract
	public int updateLoanContract(LoanContractDTO loanContract) {
		String SQL = "UPDATE loanContract SET l_id = ?, c_id = ?, e_id = ?, start_date = ?,"
				+ " muturity_date = ?, payment_method = ?, balance = ?, payment_date = ?,"
				+ " delinquent_amount = ?, guarantor_id = ?, interest_rate = ? WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanId());
			pstmt.setInt(2, loanContract.getCustomerId());
			pstmt.setInt(3, loanContract.getEmployeeId());
			pstmt.setTimestamp(4, loanContract.getStartDate());
			pstmt.setTimestamp(5, loanContract.getMuturityDate());
			pstmt.setString(6, loanContract.getPaymentMethod());
			pstmt.setLong(7, loanContract.getBalance());
			pstmt.setDate(8, loanContract.getPaymentDate());
			pstmt.setLong(9, loanContract.getDelinquentAmount());
			pstmt.setInt(10, loanContract.getGuarantorId());
			pstmt.setLong(11, loanContract.getInterestRate());
			pstmt.setInt(12, loanContract.getLoanContractId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan contract
	public int deleteLoanContract(int loanContractId) {
		String SQL = "DELETE FROM loanContract WHERE lc_id = ?";
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
		loanContract.setMuturityDate(rs.getTimestamp("muturity_date"));
		loanContract.setPaymentMethod(rs.getString("payment_method"));
		loanContract.setBalance(rs.getLong("balance"));
		loanContract.setPaymentDate(rs.getDate("payment_date"));
		loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
		loanContract.setGuarantorId(rs.getInt("guarantor_id"));
		loanContract.setInterestRate(rs.getLong("interest_rate"));
		
		loanContract.setLoanType(rs.getString("loan_type"));
		loanContract.setLoanName(rs.getString("loan_name"));
		loanContract.setEmployeeName(rs.getString("e_name"));
		loanContract.setCustomerName(rs.getString("c_name"));
		loanContract.setGuarantorName(rs.getString("guarantor_name"));
	}

	// 모든 데이터 가져오기
	public List<LoanContractDTO> getLoanContracts() {
		String SQL = "SELECT * FROM loanContract";
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
		String SQL = "SELECT * FROM loanContract WHERE lc_id = ?";
		
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
				+ " FROM loanContract lc");
		queryBuilder.append(" JOIN loans l ON lc.l_id = l.l_id");
		queryBuilder.append(" JOIN customers c ON lc.e_id = c.e_id AND lc.c_id = c.c_id");
		queryBuilder.append(" JOIN employees e ON c.e_id = e.e_id");
		queryBuilder.append(" JOIN loanRepayments lr ON lr.lc_id = lc.lc_id");
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
//	 대출 잔액, 연체 넣어주기
		if (loanContractDTO.getStartDate() != null ) {
			queryBuilder.append(" AND lc.start_date >= ? AND lc.start_date < ?");
		}
		if (loanContractDTO.getMuturityDate() != null ) {
			queryBuilder.append(" AND lc.muturity_date >= ? AND lc.muturity_date < ?");
		}
		
		
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			System.out.println("!!! dao - getLoanContractByDTO TRY 1");
			
			int parameterIndex = 1;

			if (loanContractDTO.getLoanName() != null) {
	            pstmt.setString(parameterIndex++, loanContractDTO.getLoanName());
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
	        if (loanContractDTO.getMuturityDate() != null) {
	            pstmt.setTimestamp(parameterIndex++, loanContractDTO.getMuturityDate());
	            
	            Timestamp nextDay = new Timestamp(loanContractDTO.getMuturityDate().getTime() + 24 * 60 * 60 * 1000);
	            pstmt.setTimestamp(parameterIndex++, nextDay);
	        }
	       
//			대출 잔액, 연체 넣어주기
	        

			System.out.println("!!! dao - pstmt= "+ pstmt);

			List<LoanContractDTO> loanContractDTOList = new ArrayList<>();
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					LoanContractDTO loanContracts = new LoanContractDTO();
					
					fillLoanContractDTOFromResultSet(loanContracts, rs);
					loanContractDTOList.add(loanContracts);
					}

				return loanContractDTOList;
			}
			catch (Exception e) {
				System.out.println("!!! DAO try1 오류 " + e);
				e.printStackTrace();
			} 
		}
		catch (Exception e) {
			System.out.println("!!! DAO try2 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}
}
