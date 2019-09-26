package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.concurrent.TimeUnit;
//Good article about caching resourses in browser
//https://www.baeldung.com/cachable-static-assets-with-spring-mvc
//https://www.mscharhag.com/spring/resource-versioning-with-spring-mvc
//http://qaru.site/questions/209978/why-does-browser-still-sends-request-for-cache-control-public-with-max-age
//etag !!!
@Configuration
@ComponentScan("com.company")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    //add static resourse css for example
//    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/style/**")
                .addResourceLocations("resources/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));


        registry.addResourceHandler("/js/**")
                .addResourceLocations("resources/js/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    //https://www.mscharhag.com/spring/resource-versioning-with-spring-mvc
//    @Bean
//    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
//        return new ResourceUrlEncodingFilter();
//    }


    //https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html
    //https://habr.com/ru/post/435062/   - russian edition
    //https://www.concretepage.com/spring-4/spring-mvc-thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        // NOTE 'order' and 'viewNames' are optional
//        viewResolver.setOrder(1);
//        viewResolver.setViewNames(new String[] {".html", ".xhtml"});
        return viewResolver;
    }
}
