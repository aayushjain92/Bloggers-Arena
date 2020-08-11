package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.UserDAO;
import com.neu.edu.model.User;
import com.neu.edu.validator.UserValidator;

@Controller
@RequestMapping("/registrationform.htm")
public class UserRegistrationController {
	@Autowired
	private UserValidator uservalidator;
	@Autowired
	private UserDAO userdao;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(uservalidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, User user) {
		user.setFname("First Name"); // if object has values, these would populate the form view automatically
		user.setLname("Last Name"); // if an existing object populates the form fields, it is called
									// form-backing-object
		model.addAttribute("user", user);
		return "registrationform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model, HttpServletRequest request) 
			throws Exception {
		if (bindingResult.hasErrors()) {
			return "registrationform";  //the are validation errors, go to the form view
		}
		//no errors, so go to the success view
		System.out.println(user);
		userdao.register(user);
		
		HttpSession session = request.getSession();
        if(session !=null) {
            session.getAttribute("user");
            String userName = (String) session.getAttribute("username");            
        }
        else {
        	session.setAttribute("user", user);
        }
		
        return "redirect:/";
	}

}
