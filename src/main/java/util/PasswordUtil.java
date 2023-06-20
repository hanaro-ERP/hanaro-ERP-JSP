package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
	private static final int SALT_LENGTH = 16;

	public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
		// 임시용 하드코딩
		String pepper = "pepper";

		String saltedPassword = salt + password + pepper;
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
