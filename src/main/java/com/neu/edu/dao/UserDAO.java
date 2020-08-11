package com.neu.edu.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.UserException;
import com.neu.edu.model.User;



public class UserDAO extends DAO{
	
	public UserDAO() {
	}

	//Register User
	public User register(User u) throws UserException{
		User user = null;
		try {
			begin();
			System.out.println("inside DAO");
			
			user = new User();
			user.setFname(u.getFname());
			user.setLname(u.getLname());	
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			getSession().save(user);
			commit();
		} catch (HibernateException e) {
			rollback();
		} finally {
	          
	      }
		return user;
	}

	
	//get username, password
	public User get(String email, String password){
			User user = null;
		try {
			begin();
			Query q = getSession().createQuery("from User where email = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);			
			user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
		} finally {
	         
	      }
		return user;
	}
	
	
		public User reloaduser(String email){
				User user = null;
			try {
				begin();
				Query q = getSession().createQuery("from User where email = :email");
				q.setString("email", email);
				user = (User) q.uniqueResult();
				commit();
				return user;
			} catch (HibernateException e) {
				rollback();
			} finally {
		         
		      }
			return user;
		}
		
		
	
}
	
	
	

