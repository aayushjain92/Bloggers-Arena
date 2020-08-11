package com.neu.edu.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neu.edu.dao.CommentDAO;
import com.neu.edu.dao.PostDAO;
import com.neu.edu.dao.UserDAO;
import com.neu.edu.model.Comment;
import com.neu.edu.model.Post;
import com.neu.edu.model.User;
import com.neu.edu.validator.CreatePostValidator;

@Controller
@RequestMapping()
public class PostController {
	private final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private UserDAO userdao;
	@Autowired
	private CreatePostValidator postvalidator;
	@Autowired
	private PostDAO postdao;
	@Autowired
	private CommentDAO commentdao;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(postvalidator);
	}

	@RequestMapping(value = "/createpost.htm", method = RequestMethod.GET)
	public String createpostform(ModelMap model, Post post) {
		post.setTitle(" Enter a title here"); // if object has values, these would populate the form view automatically
		post.setBody("Enter the content"); // if an existing object populates the form fields, it is called
		// form-backing-object
		model.addAttribute("post", post);
		return "createpost";
	}

	@RequestMapping(value = "/createpost.htm", method = RequestMethod.POST)
	public String createpostsuccess(@Validated @ModelAttribute("post") Post post, BindingResult bindingResult, ModelMap model,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "createpost"; // the are validation errors, go to the form view
		}
		// no errors, so go to the success view

		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println(post);
			System.out.println(user);
			postdao.register(post, user);
			String email = user.getEmail();
			session.removeAttribute("user");
			session.setAttribute("user", userdao.reloaduser(email));
			return "createpostsuccess";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/editpost.htm", method = RequestMethod.GET)
	public String editPost(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");
			User user = userdao.reloaduser(u.getEmail());
			session.removeAttribute("user");
			session.setAttribute("user", user);
			model.addAttribute("posts", u.getPosts());
			//return "redirect:/editpost.htm";
			return "editpost";
		} else {
			return "login";
		}

	}
	
	
	// delete post
	@RequestMapping(value = "/{id}/deletepost.htm", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") String id, Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		logger.debug("deletePost() : {}", id);

		postdao.delete(Long.parseLong(id));
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");
			User user = userdao.reloaduser(u.getEmail());
			session.removeAttribute("user");
			session.setAttribute("user", user);
			model.addAttribute("posts", user.getPosts());
		}
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Post is deleted!");
		
		return "redirect:/editpost.htm";

	}
	
	//view post
	@RequestMapping(value = "/{id}/viewpost.htm", method = RequestMethod.GET)
	public String viewPost(@PathVariable("id") String id, Model model) {
		
		Post post = postdao.reloadpost(Long.parseLong(id));
		model.addAttribute("post", post);
		List<Comment> comments = commentdao.getCommentbyPostId(Long.parseLong(id));
		model.addAttribute("comments", comments);
		
		
//		  List<Comment> comments= post.getComments();
		  
		  if (post.getComments() != null) { comments =
		  commentdao.getCommentbyPostId(Long.parseLong(id));
		  model.addAttribute("comments", comments); } else {
		  model.addAttribute("comments", null); }
		
		 
		return "viewpost";
		
	}
	

}
