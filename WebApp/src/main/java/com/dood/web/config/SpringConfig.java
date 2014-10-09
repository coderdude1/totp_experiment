package com.dood.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * http://www.baeldung.com/2011/10/20/bootstraping-a-web-application-with-spring-3-1-and-java-based-configuration-part-1/
 */

@Configuration
//@ImportResource( { "classpath*:/rest_config.xml" } )//used to import existing xml configs
@ComponentScan( basePackages = "com.dood.controller" )
//@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class SpringConfig {

}
