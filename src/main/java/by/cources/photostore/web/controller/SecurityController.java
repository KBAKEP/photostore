package by.cources.photostore.web.controller;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import by.cources.photostore.web.form.Message;




@RequestMapping("/security")
@Controller
public class SecurityController {

	private static final Logger LOGGER = Logger.getLogger(SecurityController.class);
	
	@Autowired
	private MessageSource messageSource;	
	
	@RequestMapping("/loginfail")
	public String loginFail(Model model, Locale locale) {
		LOGGER.info("Login failed detected");
		model.addAttribute("message", new Message("error", messageSource.getMessage("message_login_fail", new Object[]{}, locale))); 
		return "users/list";
	}
	
}
