package com.example.jpain10steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jpain10steps.entity.User;
import com.example.jpain10steps.service.UserDAOService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	@Autowired
	private UserDAOService userDaoServie;

	@Override
	public void run(String... args) throws Exception {

		User user = new User("Jack", "Admin");
		long id = userDaoServie.insert(user);
		log.info("New user is created: "+ user);
	}

}
