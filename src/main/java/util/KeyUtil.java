package util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class KeyUtil {
	public static String loadKey(String fileAbsolutePath) throws Exception {
		byte[] encodedKey = Files.readAllBytes(Paths.get(fileAbsolutePath));
		return new String(Base64.getDecoder().decode(encodedKey));
	}
}