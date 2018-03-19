package com.rest.services.restwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	/*
	 * @GetMapping("/filtering") public SomeBean getSomeBean() { return new
	 * SomeBean("value1", "value2", "value3"); }
	 */

	// field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}

	/*
	 * @GetMapping("/filtering-list") public List<SomeBean> getSomeBeans() { return
	 * Arrays.asList(new SomeBean("value1", "value2", "value3"), new
	 * SomeBean("value1", "value2", "value3")); }
	 */

	@GetMapping("/filtering-list")
	public List<SomeBean> getSomeBeans() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value1", "value2", "value3"));
	}
}
