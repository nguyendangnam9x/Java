package com.namnd.rest.webservices.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	@GetMapping(path = "/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Dang Nam Version1");
	}
	
	@GetMapping(path = "/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Dang", "Name V2"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionParameter() {
		return new PersonV1("Parameter version 1");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionParameter() {
		return new PersonV2(new Name("Parameter version 2", "Name V2"));
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionHeader() {
		return new PersonV1("X header 1");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionHeader() {
		return new PersonV2(new Name("X header 2", "X Header Name V2"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vn.namnd.app-v1+json")
	public PersonV1 getFirstVersionAcceptHeader() {
		return new PersonV1("AcceptHeader v1");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vn.namnd.app-v2+json")
	public PersonV2 getSecondVersionAcceptHeader() {
		return new PersonV2(new Name("AcceptHeader 2", "AcceptHeader V2"));
	}
}
