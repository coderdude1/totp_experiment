package com.dood.web.config;

import com.dood.web.security.TwoFactorAuthenticationFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.ServletContext;

/**
 * Created by This should load the security chain (seems to function the same as a filter?
 */
@Order(value = 1)
public class MessageSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
//        super.afterSpringSecurityFilterChain(servletContext);
        insertFilters(servletContext, new TwoFactorAuthenticationFilter());
    }
}
