package com.dood.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan//scan stuff from Web project here
public class WebConfig extends WebMvcConfigurerAdapter {
    public static final MediaType JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    /**
     * @param configurer Servlet handler config
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * add resource handlers for /resources/** and /static/**, and cache static resources
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(31556926);
    }

//    /**
//     * @param interceptorRegistry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
//        interceptorRegistry.addInterceptor(localeChangeInterceptor());
//    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.useJaf(false)
                .ignoreAcceptHeader(false)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * ReloadableResourceBundleMessageSource allows reloading of resource bundle contents without a server restart.
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:ValidationMessages", "classpath:login_locale");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");

        // # -1 : never reload, 0 always reload - seconds.
        messageSource.setCacheSeconds(600);
        return messageSource;
    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("locale");
//        return localeChangeInterceptor;
//    }
//
//    @Bean(name = "localeResolver")
//    public LocaleResolver sessionLocaleResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(new Locale("en", "US"));
//        return sessionLocaleResolver;
//    }
//    @Bean(name = "localeConfigurerFilterBean")
//    public LocaleConfigurerFilter createLocaleConfigurerFilter() {
//        return new LocaleConfigurerFilter();
//    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
