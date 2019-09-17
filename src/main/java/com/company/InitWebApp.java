package com.company;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
//http://spring-projects.ru/guides/handling-form-submission/
//this with springboot
public class InitWebApp implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext=new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet=new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic registration =servletContext.addServlet("dispatcher",dispatcherServlet);
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
    }
}
