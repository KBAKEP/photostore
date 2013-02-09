package by.cources.photostore.web.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.cources.photostore.dal.CrudDao;
import by.cources.photostore.exception.DalException;
import by.cources.photostore.model.Role;
import by.cources.photostore.model.User;



@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	MessageSource messageSource;
		
	@Autowired
	@Qualifier("crudDaoBean")
	private CrudDao crudDao;

	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		try {
			List<User> users = crudDao.list(User.class);
			model.addAttribute("users", users);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("list()");
		return "users/list";
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		try {
			crudDao.delete(User.class, id);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		return "forward:/users";
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model Model) {
		User user = new User();
		try {
			user = crudDao.find(User.class, id);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		Model.addAttribute("user", user);
		return "users/show";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(/*@Valid*/ User user, Model model,
			BindingResult bindingResult, Locale locale) {
		LOGGER.info("Updating users");
/*
		if (bindingResult.hasErrors()) {
			model.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"user_save_fail", new Object[] {}, locale)));
			model.addAttribute("user", user);
			return "users/update";
		}
*/ 
		try {
			user.setGrantedAuthority(crudDao.find(Role.class, 1L));
			crudDao.merge(user);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		return "redirect:/users/" + user.getId().toString();
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("user", crudDao.find(User.class, id));
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		return "users/update";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(/*@Valid*/ User user, Model model,
			BindingResult bindingResult, Locale locale) {
		LOGGER.info("Creating user");
/*		
		if (bindingResult.hasErrors()) {
			model.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"user_save_fail", new Object[] {}, locale)));
			model.addAttribute("user", user);
			return "users/create";
		}
*/
		
		try {
			user.setGrantedAuthority(crudDao.find(Role.class, 1L));
			user = crudDao.merge(user);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("before getId()");
		LOGGER.info("redirect and getId()" + "redirect:/users/"
				+ user.getId().toString());
		return "redirect:/users/" + user.getId().toString();
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "users/create";
	}

}
