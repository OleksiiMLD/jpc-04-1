package ua.omld.jpc.configuration;

import com.sun.xml.ws.transport.http.servlet.WSSpringServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
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
		servletContext.addListener(new ContextLoaderListener(context));

		WSSpringServlet wsSpringServlet = new WSSpringServlet();
		ServletRegistration.Dynamic wsDispatcher = servletContext.addServlet("ws-servlet", wsSpringServlet);
		wsDispatcher.setLoadOnStartup(1);
		wsDispatcher.addMapping("/ws/*");

		DispatcherServlet restServlet = new DispatcherServlet();
		restServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		restServlet.setApplicationContext(context);
		ServletRegistration.Dynamic restDispatcher = servletContext.addServlet("rest-servlet", restServlet);
		restDispatcher.setLoadOnStartup(1);
		restDispatcher.addMapping("/*");
	}
}