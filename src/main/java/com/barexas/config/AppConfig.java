package com.barexas.config;

import com.barexas.service.user.UserService;
import com.barexas.service.user.UserServiceDBImpl;
import com.barexas.service.user.UserServiceJsonImpl;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource(value = "file:${lardi.conf}", ignoreResourceNotFound = true)
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public UserService userService(){
        UserService service;
        if (env.containsProperty("filestorage.way")){
            UserServiceJsonImpl.setJsonDirectory(env.getProperty("filestorage.way"));
        }
        if (Boolean.parseBoolean(env.getProperty("useDB"))){
            service =  new UserServiceDBImpl();
        } else {
            service = new UserServiceJsonImpl(new GsonBuilder().create());
        }
        return service;
    }
}
