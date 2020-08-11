package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.model.Comment;
import com.neu.edu.model.Post;
import com.neu.edu.model.User;



public class PostDAO extends DAO{
	
	public PostDAO() {
	}

	
	public Post register(Post p, User u) {
		Post post = null;
		try {
			begin();
			post = new Post();
			post.setTitle(p.getTitle());
			post.setBody(p.getBody());	
			post.setAuthor(u);
			getSession().save(post);
			commit();
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
		return post;
	}
	
	
	public List<Post> get(){
		List<Post> posts = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Post.class);				
			posts =  crit.list();
			commit();
			return posts;
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
		return posts;
	}
	
	public Post reloadpost(Long id){
		Post post = null;
	try {
		begin();
		Query q = getSession().createQuery("from Post where id = :id");
		q.setLong("id", id);
		post = (Post) q.uniqueResult();
		commit();
		System.out.println(post.getComments());
		return post;
	} catch (HibernateException e) {
		rollback();
	} finally {
         
      }
	return post;
}
	
	
	public Post getPostbyId(Long id){
		Post post = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Post.class);
			crit.add(Restrictions.eq("id", id));
			post =  (Post) crit.uniqueResult();
			System.out.println(post.getComments());
			commit();
			return post;
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
		return post;
	}
	
	/*
		public List<Post> get(User u){
			List<Post> posts = null;
			try {
				begin();
				Criteria postCrit = getSession().createCriteria(Post.class);
				Criteria userCrit = postCrit.createCriteria("user");
				Criterion exp = Restrictions.eq("email",u.getEmail());
				userCrit.add(exp);
				posts = postCrit.list();
				commit();
				return posts;
			} catch (HibernateException e) {
				rollback();
				
			}
			return posts;
		}
	*/

	public void delete(Long id) {
		// TODO Auto-generated method stub
		try {
			begin();
			Criteria postCrit = getSession().createCriteria(Post.class);
			postCrit.add(Restrictions.eq("id",id));
			Post post = (Post) postCrit.uniqueResult();
			getSession().delete(post);
			commit();
			
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
	}

	
}
	
	
	

