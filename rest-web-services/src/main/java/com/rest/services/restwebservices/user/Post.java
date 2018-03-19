package com.rest.services.restwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	private String description;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Post(Integer id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [Id=" + id + ", description=" + description + "]";
	}

}
