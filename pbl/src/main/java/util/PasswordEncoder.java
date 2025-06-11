package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordEncoder {

    public static String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(10, rawPassword.toCharArray());
    }

    public static  boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
    
    public static void main(String[] args) {
		String origin = "평문";
		String encoded = encode(origin);
		String encoded2 = encode(origin);
		String encoded3 = encode(origin);
		String encoded4 = encode(origin);
		
		System.out.println(encoded);
		System.out.println(encoded2);
		System.out.println(encoded3);
		System.out.println(encoded4);
		
		String[] encodes = {"$2a$10$yTUGS9h1K6awI/bayt9Y6OXmtvSO4hHHZ2vXtgYVsxHPPYY5sX/Je"
				,"$2a$10$h49UHCVMTxkO8j94ObxARexrxpAbHxICI7X7qoc9yCzl0CO4CNi.y"
				,"$2a$10$a4Gu31OCxxoZIHKkCFh0Seg94CU20Qx9MugIwHvAJgMRoD2gshdzK"
				,"$2a$10$SLzoTb8MBo0WNTJmwPIQTe3NaQpMbC46vX53MnNBJB9S9VH5WtZoy"};
		
		System.out.println(matches("평문", encodes[0]));
		System.out.println(matches("암호문", encodes[0]));
	}
}
