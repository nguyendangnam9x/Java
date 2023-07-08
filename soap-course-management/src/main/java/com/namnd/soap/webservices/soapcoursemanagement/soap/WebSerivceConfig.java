package com.namnd.soap.webservices.soapcoursemanagement.soap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

// Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebSerivceConfig extends WsConfigurerAdapter {
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformSchemaLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/wsdl1/*");
	}

	@Bean(name = "courses1")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://www.namnd.org/course-details");
		// End point url
		definition.setLocationUri("http://localhost:8080/wsdl1");
		definition.setSchema(schema);

		// Fix for adding soapAction to the dynamic generated wsdl
		Properties soapActions = new Properties();
		soapActions.setProperty("GetAllCourseDetails", "https://namnd.com/GetAllCourseDetails");
		soapActions.setProperty("GetCourseDetails", "https://namnd.com/GetCourseDetails");
		soapActions.setProperty("DeleteCourseDetail", "https://namnd.com/DeleteCourseDetail");
		definition.setSoapActions(soapActions);

		return definition;
	}
	
	@Bean
	public Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
		securityInterceptor.setSecurementActions("UsernameToken");
		securityInterceptor.setSecurementUsername("user1");
		securityInterceptor.setSecurementPassword("password");
		return securityInterceptor;
	}
	
//	@Bean
//	public XwsSecurityInterceptor securityInterceptor() {
//		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
//		securityInterceptor.setCallbackHandler(callbackHandler());
//		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
//		return securityInterceptor;
//	}
//	
//	@Bean
//	public SimplePasswordValidationCallbackHandler callbackHandler() {
//		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
//		handler.setUsersMap(Collections.singletonMap("user", "password"));
//		return handler;
//	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

	@Bean
	XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
//	CommonsXsdSchemaCollection Using for multiple schema files
//	@Bean
//	public CommonsXsdSchemaCollection xsdSchemaCollection() {
//		CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(
//				new Resource[] { new ClassPathResource("Schemas/UWLR_Autorisatie_v2p2.xsd"),
//						new ClassPathResource("Schemas/SOAP_Envelope.xsd"),
//						new ClassPathResource("Schemas/EDEXML.types.vast.xsd"),
//						new ClassPathResource("Schemas/EDEXML.types.variabel.xsd"),
//						new ClassPathResource("Schemas/EDEXML.elementen.xsd"),
//						new ClassPathResource("Schemas/UWLR_Leerlinggegevens_v2p2.xsd") });
//		xsds.setInline(true);
//		return xsds;
//	}
}
