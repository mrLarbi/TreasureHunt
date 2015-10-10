usernameErrorInvalidText = "The username must contain only letters, numbers and '_'"

$(document).ready(function(){
	var usernameError = $("#usernameError");
	var passwordError = $("#passwordError");
	var confirmationError = $("#confirmationError");
	var emailError = $("#emailError");
	var nameError = $("#nameError");
	var genderError = $("#genderError");
	var phoneError = $("#phoneError");
	var zipcodeError = $("#zipcodeError");

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

	function checkUsername()
	{
		
	}

	function checkPassword()
	{
		
	}

	function checkConfirmation()
	{
		if(passwordError.value === confirmationError.value)
		{
			confirmationError.hide();
		}
	}

	function checkEmail()
	{
		
	}

	function checkName()
	{
		
	}

	function checkEmail()
	{
		
	}

	function checkGender()
	{
		
	}

	function checkPhone()
	{
		
	}

	function checkZipcode()
	{
		
	}
	
	function yolo()
	{
		
	}
	
	hideErrors()
})
