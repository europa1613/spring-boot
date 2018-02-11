package com.rest.services.restwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();

	private static int userCounter = 3;
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCounter);
		}
		users.add(user);

		return user;
	}

	public User findUserById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

}
