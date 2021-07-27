package com.nokia.config;

import com.nokia.common.filter.HttpHostFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

//@Configuration
public class HttpHostFilterConfig {

    @Bean
    public FilterRegistrationBean httpHostFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new HttpHostFilter());
        registration.addUrlPatterns("/*");
        registration.setName("HttpHostFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);
        return registration;
    }
}
