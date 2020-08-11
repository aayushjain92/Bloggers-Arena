package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.edu.model.Comment;
import com.neu.edu.model.Post;
import com.neu.edu.model.User;



public class CommentDAO extends DAO{
	@Autowired
	PostDAO postdao;
	
	public CommentDAO() {
	}

	
	public void saveComment(Post p, User u, String commentText) {
		Comment comment = null;
		try {
			begin();
			comment = new Comment(u,p);
			comment.setCommentText(commentText);
			//comment.setUser(u);
			//comment.setPost(p);
			getSession().save(comment);
			commit();
		} catch (HibernateException e) {
			rollback();
		} finally {
	        
	      }
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentbyPostId(Long id){
		List<Comment> comments = null;
		try {
			begin();
			//Query q = getSession().createQuery("from Comment where post_id = :id");
			Criteria postcrit = getSession().createCriteria(Post.class);
			Criteria crit = postcrit.createCriteria("comments");
			postcrit.add(Restrictions.eq("id", id));
			//q.setLong("id", postid);
			//comments = (List<Comment>) q.list();
			comments = (List<Comment>)crit.list();
			comments = crit.list();
			commit();
			return comments;
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
		return comments;
	
	}
	
}
	
	
	

