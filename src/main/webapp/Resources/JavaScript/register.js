$(document).ready(function(){
	var usernameError = $("#usernameError");
	var passwordError = $("#passwordError");
	var confirmationError = $("#confirmationError");
	var emailError = $("#emailError");
	var nameError = $("#nameError");
	var genderError = $("#genderError");
	var phoneError = $("#phoneError");
	var zipcodeError = $("#zipcodeError");
	
	var usernameInput = $("#usernameInput");
	var passwordInput = $("#passwordInput");
	var confirmationInput = $("#confirmationInput");
	var emailInput = $("#emailInput");
	var nameInput = $("#nameInput");
	var genderInput = $("#genderInput");
	var phoneInput = $("#phoneInput");
	var zipcodeInput = $("#zipcodeInput");
	
	var usernameErrorTextLength1 = "The username must have at least 2 characters"
	var usernameErrorTextLength2 = "The username must not have more than 15 characters"
	var usernameErrorTextRegExp = "The username must have only letters and digits"
	var passwordErrorTextLength1 = "The password must have at least 6 characters"
	var passwordErrorTextLength2 = "The password must not have more than 25 characters"
	var passwordErrorTextRegExp = "The password must have at least a digit and a letter"
	var confirmationErrorTextEqual = "The confirmation does not match the password"
	var emailErrorTextRegExp = "The email format must be xxx@yyy.zz"	
	var nameErrorTextRegExp = "The name must contain only letters and must not have more than 255 letters"	
	var phoneErrorTextRegExp = "The phone must contain only digits and it's format must be XXXXXXXX"	
	var zipcodeErrorTextRegExp = "The zipcode must contain only digits and it's format must be XXXXX"	
		
	function showError(errorElement, errorText)
	{
		errorElement.text(errorText);
		errorElement.show();
	}
	
	function hideError(errorElement)
	{
		errorElement.text("");
		errorElement.hide();
	}
	
	function checkUsername()
	{
		var usernameValue = usernameInput.val();
		var usernameRegex = new RegExp('^\d*[a-zA-Z][a-zA-Z\d]*$')
		
		if(usernameValue.length < 2) {
			showError(usernameError, usernameErrorTextLength1)
		} else
		if(usernameValue.length > 15) {
			showError(usernameError, usernameErrorTextLength2)
		} else if(!usernameRegex.test(usernameValue)) {
			showError(usernameError, usernameErrorTextRegExp)
		} else {
			hideError(usernameError)
		}
	}
	
	usernameInput.keyup(function(){
		checkUsername();
	});

	function checkPassword()
	{
		var passwordValue = passwordInput.val();
		var passwordRegex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$');
		
		if(passwordValue.length < 6) {
			showError(passwordError, passwordErrorTextLength1)
		} else if(passwordValue.length > 25) {
			showError(passwordError, passwordErrorTextLength2)
		} else if(!passwordRegex.test(passwordValue)) {
			showError(passwordError, passwordErrorTextRegExp)
		} else {
			hideError(usernameError)
		}
	}
	
	passwordInput.keyup(function(){
		checkPassword()
		checkConfirmation()
	});

	function checkConfirmation()
	{
		if(passwordInput.val() === confirmationInput.val())
		{
			hideError(confirmationError);
		}
		else
		{
			showError(confirmationError, confirmationErrorTextEqual);
		}
	}
	
	confirmationInput.keyup(function(){
		checkConfirmation()
	});
	
	function checkEmail()
	{
		var emailValue = emailInput.val();
		var emailRegex = new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');
		
		if(!emailRegex.test(emailValue)) {
			showError(emailError, emailErrorTextRegExp)
		} else {
			hideError(emailError);
		}
	}
	
	emailInput.keyup(function(){
		checkEmail();
	});
	
	function checkName()
	{
		var nameValue = nameInput.val();
		var nameRegex = new RegExp('^[a-zA-Z]*$');
		
		if(!nameRegex.test(nameValue)) {
			showError(nameError, nameErrorTextRegExp)
		} else {
			hideError(nameError);
		}
	}
	
	nameInput.keyup(function(){
		checkName();
	});
	
	function checkPhone()
	{
		var phoneValue = phoneInput.val();
		var phoneRegex = new RegExp('^[0-9]{8}$');
		
		if(!phoneRegex.test(phoneValue)) {
			showError(phoneError, phoneErrorTextRegExp)
		} else {
			hideError(phoneError);
		}
	}
	
	phoneInput.keyup(function(){
		checkPhone();
	});

	function checkZipcode()
	{
		var zipcodeValue = zipcodeInput.val();
		var zipcodeRegex = new RegExp('^[0-9]{5}$');
		
		if(!zipcodeRegex.test(zipcodeValue)) {
			showError(zipcodeError, zipcodeErrorTextRegExp)
		} else {
			hideError(zipcodeError);
		}
	}
	
	zipcodeInput.keyup(function(){
		checkZipcode();
	});
	
	function hideErrors()
	{
		usernameError.hide()
		passwordError.hide()
		confirmationError.hide()
		emailError.hide()
		nameError.hide()
		genderError.hide()
		phoneError.hide()
		zipcodeError.hide()
	}
	
	hideErrors();
})
