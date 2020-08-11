package com.neu.edu.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.model.User;

public class UserValidator implements Validator
{
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public UserValidator() {
		// TODO Auto-generated constructor stub
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return User.class.equals(clazz);
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		//basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "empty.invalid.fname", "First Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "empty.invalid.lname", "Last Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "Email Required");

		if(!valid(user.getEmail())){
			errors.rejectValue("email", "pattern.invalid.email", "invalid email address");
		}
		
		
		//add additional custom validations below
	}
	
	public boolean valid(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();

	}
	
}
