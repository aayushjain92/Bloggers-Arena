package com.neu.edu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.model.Post;

public class CreatePostValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return Post.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.title", "Title is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "empty.body", "Content is Required");
		
		//add additional custom validations below
	}
}
