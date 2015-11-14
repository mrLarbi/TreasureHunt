package backend;


import java.util.regex.Pattern;

public class Validator {
	
	public static final String USER_NAME_PATTERN =  "^[0-9]*[a-zA-Z][a-zA-Z0-9]*$";
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	public static final String ZIP_PATTERN = "^[0-9]{5}$";
	
	public static final String PHONE_PATTERN = "^[0-9]{10}$";

	//private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";

	public static final String NUMBER_PATTERN = "^([0-9]+)$";



	public static boolean isValidPostalCode(String zip) {
		if(zip == null || zip.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(Validator.ZIP_PATTERN);
		return pattern.matcher(zip).matches();
	}

	public static boolean isValidMail(String email) {
		if(email == null ) {
			return false;
		}

		Pattern pattern = Pattern.compile(Validator.EMAIL_PATTERN);
		return pattern.matcher(email).matches();
	}

	public static boolean isValidPhoneNumber(String phone) {
		if(phone == null || phone.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(Validator.PHONE_PATTERN);
		return pattern.matcher(phone).matches();
	}

	public static boolean isValidUsername(String username) {
		if(username == null || username.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(Validator.USER_NAME_PATTERN);
		return pattern.matcher(username).matches();
	}
	
	public static boolean isValidPassword(String password) {
		return password != null && password.length() >= 8 &&
				password.length() <= 25;
	}

	public static boolean isNumberFormat(String number) {
		if (number == null) {
			return false;
		}

		Pattern pattern = Pattern.compile(NUMBER_PATTERN);
		return pattern.matcher(number).matches();
	}
}
