package com.namnd.rest.webservices.restfullwebservices.filtering;

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

	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(
				new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value3", "value4", "value5")
			);
	}
	
	@GetMapping("/filtering-dinamic")
	public MappingJacksonValue filteringdinamic() {
		SomeBean someBean = new  SomeBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("filter3", "filter1");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeFilterHere", filter);
		
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
}
