package com.async.asyncdemo.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient<T, V> {

	@Autowired
	private RestTemplate restTemplate;

	public V execute(String url, HttpMethod method, T data, Class<V> responseType)
			throws ResourceAccessException, Exception {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<T> entity = new HttpEntity<T>(data, headers);
		ResponseEntity<V> response = restTemplate.exchange(url, method, entity, responseType);
		return response.getBody();
	}
}