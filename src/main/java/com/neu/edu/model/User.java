package com.neu.edu.model;

import javax.persistence.*;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String password;

    @Column(length = 100)
    private String fname;
    
    @Column(length = 100)
    private String lname;
    
    @Column(length = 100)
    private String email;


	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Post> posts;

	 @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	 private List<Comment> comments;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fullName) {
        this.fname = fullName;
    }

    public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
    
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
    
    public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + "]";
	}

	


    
}
