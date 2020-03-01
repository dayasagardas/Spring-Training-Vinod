package co.vinod.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import co.vinod.cfg.AppConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("Inside AppInitializer.onStartup()...");
		AnnotationConfigWebApplicationContext ctx;
		ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class); // register at least one config file
		
		DispatcherServlet ds = new DispatcherServlet(ctx);
		Dynamic s1 = servletContext.addServlet("ds", ds);
		
		s1.setLoadOnStartup(1);
		s1.addMapping("/");
		
		// 1. Create an instance of DispatcherServlet and add to the servletContext
		//    DispatcherServlet is also going to be mapped to url pattern "/"
		// 2. Create a Spring container and register with DispatcherServlet
		
	}

}
