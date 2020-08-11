package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.CommentDAO;
import com.neu.edu.dao.PostDAO;
import com.neu.edu.model.Post;
import com.neu.edu.model.User;

@Controller
public class CommentController {
	
	
	@Autowired
	CommentDAO commentdao = null;
	@Autowired
	PostDAO postdao = null;
	
	

	@RequestMapping(value = "/{id}/addcomment.htm", method = RequestMethod.GET)
	public String addComment(@PathVariable("id") String id, Model model, HttpServletRequest req) {
		String commentText = req.getParameter("comment");
		
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user"); 
			Post post = postdao.getPostbyId(Long.parseLong(id));
			commentdao.saveComment(post, u, commentText);
			model.addAttribute("post", post);
			
			return "viewpost";
		} else {
			return "login";
		}
		
	}

}
