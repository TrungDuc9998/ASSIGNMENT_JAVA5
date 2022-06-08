package ASSIGNMENT_JAVA5.utils;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {
	public static String hash(String origin) {
		
		String salt=BCrypt.gensalt();
		return BCrypt.hashpw(origin, salt);
	}
	
	public static boolean verify(String origin,String encrypted) {
		return BCrypt.checkpw(origin, encrypted);
	}

}
