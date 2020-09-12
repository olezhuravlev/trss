package com.testref;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.testref.springsecurity.config.ApplicationConfig;
import com.testref.springsecurity.config.SpringSecurityConfig;
import com.testref.springsecurity.config.WebApplicationConfig;

/**
 * Setups Spring MVC deployment configuration (instead of web.xml).
 * Declares dispatcher servlets.
 */
public class SpringMvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class, SpringSecurityConfig.class };
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebApplicationConfig.class };
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
