package backend;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

public class Validator {
	
	public static final String USER_NAME_PATTERN =  "^[0-9]*[a-zA-Z][a-zA-Z0-9]*$";
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	public static final String ZIP_PATTERN = "^[0-9]{5}$";
	
	public static final String PHONE_PATTERN = "^[0-9]{10}$";

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";
			
	
	public static boolean isValidPostalCode(String zip) {
		RegularExpression regex = new RegularExpression(Validator.ZIP_PATTERN);
		return zip != null && !zip.isEmpty() && regex.matches(zip);
	}

	public static boolean isValidMail(String email) {
		RegularExpression regex = new RegularExpression(Validator.EMAIL_PATTERN);
		return !email.isEmpty() && regex.matches(email);
	}

	public static boolean isValidPhoneNumber(String phone) {
		RegularExpression regex = new RegularExpression(Validator.PHONE_PATTERN);
		return !phone.isEmpty() && regex.matches(phone);
	}

	public static boolean isValidUsername(String username) {
		RegularExpression regex = new RegularExpression(USER_NAME_PATTERN);
		
		return username != null && username.length() >= 2 && 
				username.length() <= 15 && regex.matches(username);
	}
	
	public static boolean isValidPassword(String password) {
		//RegularExpression regex = new RegularExpression(PASSWORD_PATTERN);
		return password != null && password.length() >= 6 &&
				password.length() <= 25; // && regex.matches(password);
	}
}
