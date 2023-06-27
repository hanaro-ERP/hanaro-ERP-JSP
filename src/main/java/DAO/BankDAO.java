package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.BankDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;
import util.DatabaseUtil;

public class BankDAO {

	// insert a new bank
	public int insertBank(BankDTO bank) {
		String SQL = "INSERT INTO banks (b_id, b_name, location, phone_no) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, bank.getBankId());
			pstmt.setString(2, bank.getBankName());
			pstmt.setString(3, bank.getLocation());
			pstmt.setString(4, bank.getPhoneNumber());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a bank by bankId
	public BankDTO getBankByBankId(int bankId) {
		BankDTO bank = new BankDTO();
		String SQL = "SELECT * FROM banks WHERE b_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, bankId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillBankDTOFromResultSet(bank, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bank;
	}

	// Fill a BankDTO from a ResultSet
	private void fillBankDTOFromResultSet(BankDTO bank, ResultSet rs) throws SQLException {
		bank.setBankId(rs.getInt("b_id"));
		bank.setBankName(rs.getString("b_name"));
		bank.setLocation(rs.getString("location"));
		bank.setPhoneNumber(rs.getString("phone_no"));
	}

	// Update a bank
	public int updateBank(BankDTO bank) {
		String SQL = "UPDATE banks SET b_name = ?, location = ?, phone_no = ? WHERE b_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, bank.getBankName());
			pstmt.setString(2, bank.getLocation());
			pstmt.setString(3, bank.getPhoneNumber());
			pstmt.setInt(4, bank.getBankId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a bank
	public int deleteBank(int bankId) {
		String SQL = "DELETE FROM banks WHERE b_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, bankId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all banks
	public List<BankDTO> getBanks() {
		String SQL = "SELECT * FROM banks";
		List<BankDTO> banks = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BankDTO bank = new BankDTO();
					fillBankDTOFromResultSet(bank, rs);
					banks.add(bank);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return banks;
	}
	
	public int getBankCount(BankDTO bankDTO) {
		int cnt = 0;
		StringBuilder queryBuilder = new StringBuilder("SELECT count(*) AS cnt FROM banks ");
		queryBuilder.append("WHERE 1=1 ");

		if (bankDTO.getBankName() != null) {
			queryBuilder.append("AND b_name LIKE ?");
		}
		if (bankDTO.getCity() != null) 
			queryBuilder.append("AND location LIKE ?");

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;

			if (bankDTO.getBankName() != null) {
				pstmt.setString(parameterIndex++, bankDTO.getBankName() + "%");
			}
			if (bankDTO.getCity() != null) {
				if(bankDTO.getDistrict() != null) {
					pstmt.setString(parameterIndex++, bankDTO.getLocation());
				}
				else {
					pstmt.setString(parameterIndex++, bankDTO.getCity() + "%");
				}
			}
			
			System.out.println(pstmt.toString());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cnt = rs.getInt("cnt");
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<BankDTO> getBankListByDTO(BankDTO bankDTO, int page) {
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM banks ");
		queryBuilder.append("WHERE 1=1 ");

		if (bankDTO.getBankName() != null) {
			queryBuilder.append("AND b_name LIKE ?");
		}
		if (bankDTO.getCity() != null) {
			queryBuilder.append("AND location LIKE ?");
		}
		queryBuilder.append(" LIMIT 20 OFFSET ?");
		
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
				int parameterIndex = 1;

				if (bankDTO.getBankName() != null) {
					pstmt.setString(parameterIndex++, bankDTO.getBankName() + "%");
				}
				if (bankDTO.getCity() != null) {
					if(bankDTO.getDistrict() != null) {
						pstmt.setString(parameterIndex++, bankDTO.getLocation());
					}
					else {
						pstmt.setString(parameterIndex++, bankDTO.getCity() + "%");
					}
				}
				pstmt.setInt(parameterIndex++, (page-1)*20);
		    
				List<BankDTO> findBankList = new ArrayList<>();
				try (ResultSet rs = pstmt.executeQuery()) {				
					while (rs.next()) {
						BankDTO bank = new BankDTO();
						
						bank.setBankId(rs.getInt("b_id"));
						bank.setBankName(rs.getString("b_name"));
						bank.setLocation(rs.getString("location"));
						bank.setPhoneNumber(rs.getString("phone_no"));
						findBankList.add(bank);
		            }
					return findBankList;
					
		        } catch (Exception e) {
					e.printStackTrace();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public BankDTO getBankNameByBankID(EmployeeDTO employeeDTO) {
		String SQL = "SELECT b_name FROM banks WHERE b_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeDTO.getBankId());
			BankDTO bank = new BankDTO();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					bank.setBankName(rs.getString("b_name"));
				}
				return bank;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
