package com.async.asyncdemo.converter;

import org.springframework.stereotype.Component;

import com.async.asyncdemo.domain.JokesDomainResponse;
import com.async.asyncdemo.model.JokesResponse;

@Component
public class JokesDomainToModelConverter {

	public JokesResponse convert(JokesDomainResponse domain) {
		return JokesResponse.map(domain);
	}
	
}
