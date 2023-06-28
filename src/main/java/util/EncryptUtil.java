package util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
	private static final String ALGORITHM = "AES";
	private static final String MODE_PADDING = "AES/CBC/PKCS5Padding";
	private static final int KEY_SIZE = 32; // 256 bit key
	private static final int IV_SIZE = 16; // 128 bit IV

	private static final String HARD_CODED_KEY = "01234567890123456789012345678901";
	private static final String HARD_CODED_IV = "0123456789012345";

	private SecretKeySpec keySpec;
	private IvParameterSpec ivParamSpec;

	public EncryptUtil() {
		this.keySpec = new SecretKeySpec(HARD_CODED_KEY.getBytes(), ALGORITHM);
		this.ivParamSpec = new IvParameterSpec(HARD_CODED_IV.getBytes());
	}

	private SecretKeySpec generateKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] key = new byte[KEY_SIZE];
		secureRandom.nextBytes(key);
		return new SecretKeySpec(key, ALGORITHM);
	}

	private IvParameterSpec generateIV() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] iv = new byte[IV_SIZE];
		secureRandom.nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	private Cipher getCipher(int encryptMode) throws Exception {
		Cipher cipher = Cipher.getInstance(MODE_PADDING);
		cipher.init(encryptMode, this.keySpec, this.ivParamSpec);
		return cipher;
	}

	public String encrypt(String text) throws Exception {
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
		byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String cipherText) throws Exception {
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
		byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
		byte[] decrypted = cipher.doFinal(decodedBytes);
		return new String(decrypted, "UTF-8");
	}
}
