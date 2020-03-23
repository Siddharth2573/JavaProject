package application;

import java.security.SecureRandom;

public class SaltGenerator {
    static byte [] salt;
	public static byte [] getSalt() {
		salt=new byte[10];
		SecureRandom generator=new SecureRandom();
		generator.nextBytes(salt);
		return salt;
	}

}
