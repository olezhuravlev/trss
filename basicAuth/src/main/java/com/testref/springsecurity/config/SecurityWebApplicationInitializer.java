package com.testref.springsecurity.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class being inherited from AbstractSecurityWebApplicationInitializer does the trick - informs the application
 * to start using Spring Security.
 * Otherwise it can be declared it web.xml.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
