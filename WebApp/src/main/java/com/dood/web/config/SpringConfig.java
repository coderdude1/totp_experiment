package com.dood.web.config;

import com.dood.app.config.CoreConfig;
import org.springframework.context.annotation.*;

/**
 * http://www.baeldung.com/2011/10/20/bootstraping-a-web-application-with-spring-3-1-and-java-based-configuration-part-1/
 */

@Configuration
@Import({WebConfig.class, CoreConfig.class})
//@ImportResource("classpath:/spring/applicationContext.xml")//uthis will be changed to java config
@ImportResource("classpath:/spring/applicationContext.xml")//uthis will be changed to java config
@ComponentScan( basePackages = "com.dood.controller" )
//@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class SpringConfig {

}
