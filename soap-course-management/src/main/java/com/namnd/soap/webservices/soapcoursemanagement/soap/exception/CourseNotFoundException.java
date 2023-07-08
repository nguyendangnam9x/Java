package com.namnd.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{dangnam.com}001_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -347211677709016651L;

	public CourseNotFoundException(String message) {
		super(message);
	}
	
}
