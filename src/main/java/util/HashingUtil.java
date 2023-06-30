package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashingUtil {
	private static final String PEPPER = "pepper"; // 임시용 하드코
	private static final int SALT_LENGTH = 16;

	public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
		String saltedPassword = salt + password + PEPPER;
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes());
		return Base64.getEncoder().encodeToString(hashedBytes);
	}

	public static String generateSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] saltBytes = new byte[SALT_LENGTH];
		secureRandom.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}
}
