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
import com.neu.edu.exception.UserException;
import com.neu.edu.model.User;
import com.neu.edu.validator.UserValidator;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserDAO userdao;
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String getLoginForm(ModelMap model, User user) {
		user.setFname("First Name"); // if object has values, these would populate the form view automatically
		user.setLname("Last Name"); // if an existing object populates the form fields, it is called
									// form-backing-object
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	protected String userLogin(HttpServletRequest request, ModelMap model) throws Exception {
		System.out.print("User Logged In");
		HttpSession session = (HttpSession) request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User u = null;
		try {
			
			u = userdao.get(email, password);
			if (u == null) {
				System.out.println("email/Password does not exist");
				model.addAttribute("errorMessage", "email/Password does not exist");
				return "error";
			} else if (!(u.getEmail().equals(email) || (u.getPassword().equals(password)))) {
				System.out.println("email/Password does not exist");
				model.addAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			session.setAttribute("user", u);
			//return "home";
			return "redirect:/";

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String getLogoutForm(ModelMap model, User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!=null)
		session.removeAttribute("user");
		
		session.invalidate();
		return "redirect:/";
	}

}
