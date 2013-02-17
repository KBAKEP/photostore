package by.cources.photostore.web.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.cources.photostore.model.User;

@Component
public class UserValidator implements Validator{
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Login must not be empty.");
		String login = user.getLogin();
		if ((login.length()) > 16) {
			errors.rejectValue("login", "login.tooLong", "Login must not more than 16 characters.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
		if (!(user.getPassword()).equals(user
				.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
		}
		
		if( !EmailValidator.getInstance().isValid( user.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Email address is not valid.");
		}
	}
}

