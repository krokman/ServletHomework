package util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashPasswordUtil {
	final static Logger logger = Logger.getLogger(HashPasswordUtil.class);

	public static String getHashPassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("wrong algorithm data", e);
		}
		return generatedPassword;
	}

	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[4];
		random.nextBytes(salt);
		return new String(salt);
	}
}
