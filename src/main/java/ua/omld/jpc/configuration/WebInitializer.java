package ua.omld.jpc.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Oleksii Kostetskyi
 */
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(BasicConfiguration.class);
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		servlet.setApplicationContext(context);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DispatcherServlet.class.getName(), servlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");
	}
}