package com.namnd.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.namnd.course_details.CourseDetails;
import org.namnd.course_details.DeleteCourseDetailRequest;
import org.namnd.course_details.DeleteCourseDetailResponse;
import org.namnd.course_details.GetAllCourseDetailsResponse;
import org.namnd.course_details.GetCourseDetailsRequest;
import org.namnd.course_details.GetCourseDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.namnd.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.namnd.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.namnd.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.namnd.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://www.namnd.org/course-details", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if (course == null)
			throw new CourseNotFoundException("Course Not Found id " + request.getId());
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://www.namnd.org/course-details", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest() {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://www.namnd.org/course-details", localPart = "DeleteCourseDetailRequest")
	@ResponsePayload
	public DeleteCourseDetailResponse processDeleteCourseDetailRequest(
			@RequestPayload DeleteCourseDetailRequest request) {
		DeleteCourseDetailResponse response = new DeleteCourseDetailResponse();
		Status status = service.deleteById(request.getId());
		response.setStatus(mapStatus(status));
		return response;
	}

	private org.namnd.course_details.Status mapStatus(Status status) {
		if (status == Status.SUCCESS) {
			return org.namnd.course_details.Status.SUCCESS;
		}
		return org.namnd.course_details.Status.FAILURE;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails courseDetail = mapCourse(course);
			response.getCourseDetails().add(courseDetail);
		}
		return response;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
}
