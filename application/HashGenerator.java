package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
	static String algo="MD5";
	static byte [] hash;
	public static byte [] generateHash(String password,byte [] salt) {
		try {
			MessageDigest dgst=MessageDigest.getInstance(algo);
			dgst.reset();
			dgst.update(salt);
		    hash=dgst.digest(password.getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}

}
