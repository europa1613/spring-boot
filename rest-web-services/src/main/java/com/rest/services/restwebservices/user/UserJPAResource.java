package com.rest.services.restwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserDAOService service;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		service.findAll();
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		service.findUserById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> resource = new Resource<>(user.get());

		ControllerLinkBuilder linkToUsers = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkToUsers.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void removeUser(@PathVariable int id) {
		/* User user = */ userRepository.deleteById(id);
		;

		service.deleteUserById(id);
		/*
		 * if (user == null) { throw new UserNotFoundException("id-" + id); } return
		 * user;
		 */
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/jpa/greeting-i18n")
	public String greeting(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
