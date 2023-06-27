package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "rkddkwl123!";
	private static final String URL = "jdbc:mysql://hanaroerpprojectinstance.cueixcjf2n4d.ap-northeast-2.rds.amazonaws.com:3306/hanaroErpDatabase";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getListQuery(String type, String[] selects) {
		StringBuilder query = new StringBuilder(" AND (");

		boolean isFirst = true;
		for (String grade : selects) {
			if (isFirst) {
				isFirst = false;
			} else {
				query.append(" OR "); // 첫 번째 조건이 아니면 OR 연산자 추가
			}
			if (grade.equals("")) {
				query.append("1=1");
				break;
			} else {
				String temp = String.format("%s = \'%s\'", type, grade);
				query.append(temp);
			}
		}
		query.append(")");
		return query.toString();
	}
	
	public String getListRangeQuery(String type, String[] selects) {
		StringBuilder query = new StringBuilder(" AND (");

		boolean isFirst = true;
		for (String grade : selects) {
			if (isFirst) {
				isFirst = false;
			} else {
				query.append(" OR "); // 첫 번째 조건이 아니면 OR 연산자 추가
			}
			
			if (type.equals("income")) {
				if (grade.equals("")) {
					query.append("1=1");
					break;
				} else if (grade.equals("over100")) {
					String temp = String.format("income >= \'%s\'", "1000000000");
					query.append(temp);
				} else {
					String temp = String.format("income <= \'%s\'", grade + "0000000");
					query.append(temp);
				}
			} else {
				if (grade.equals("")) {
					query.append("1=1");
					break;
				} else if (grade.equals("over10")) {
					String temp = String.format("min_%s >= \'%s\'", type, "10");
					query.append(temp);
				} else if (grade.equals("over100")) {
					String temp = String.format("min_%s >= \'%s\'", type, "1000000000");
					query.append(temp);
				} else {
					if (type.equals("amount"))
						grade = grade + "0000000";
					String temp = String.format("min_%s <= \'%s\' AND max_%s >= \'%s\'", type, grade, type, grade);
					query.append(temp);
				}
			}
			
			
		}
		query.append(")");
		return query.toString();
	}
	
	public String getAgeQuery(String[] selectedAges) {
		StringBuilder query = new StringBuilder(" AND (");

		boolean isFirst = true;
		for (String age : selectedAges) {
			if (isFirst) {
				isFirst = false;
			} else {
				query.append(" OR "); // 첫 번째 조건이 아니면 OR 연산자 추가
			}
			if (age.equals("") && selectedAges.length < 2) {
				query.append("1=1");
				break;
			} else if (age.equals("10대")) {
				query.append("(c.age < 20)");
			} else if (age.equals("20대")) {
				query.append("(c.age >= 20 AND c.age < 30)");
			} else if (age.equals("30대")) {
				query.append("(c.age >= 30 AND c.age < 40)");
			} else if (age.equals("40대")) {
				query.append("(c.age >= 40 AND c.age < 50)");
			} else if (age.equals("50대")) {
				query.append("(c.age >= 50 AND c.age < 60)");
			} else if (age.equals("60대")) {
				query.append("(c.age >= 60 AND c.age < 70)");
			} else if (age.equals("70대")) {
				query.append("(c.age >= 70 AND c.age < 80)");
			} else if (age.equals("80대 이상")) {
				query.append("(c.age >= 80)");
			} else {
				String temp1 = selectedAges[0];
				String temp2 = selectedAges[1];

				if (temp1 == "") {
					temp1 = "0";
				}
				if (temp2 == "") {
					temp2 = "999";
				}

				String temp = String.format("(c.age >= %s and c.age <= %s)", temp1, temp2);
				query.append(temp);
				break;
			}
		}
		query.append(")");
		return query.toString();
	}
}