package com.dood.app.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Main java config for the core stuff.  Eventually will have this suck in properties
 * to populate the hibernate and other values, figure out how to sdwitch between
 * h2 and postgresql, flyway, JNDI tomcat datasource thingy, conn pooling, etc
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.dood.app.entities", "com.dood.app.daos"})
//@ComponentScan(basePackages = {"com.dood.app", "com.dood.app"})
@EnableTransactionManagement
//@Import({x.class, y.class})
public class CoreConfig {
    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        //do I need this since I've set up the annotation scans?
        em.setPackagesToScan(new String[] { "com.dood.app.entities" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/WebDev");
        dataSource.setUsername("webdev");
        dataSource.setPassword("x");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }

    @Bean(name = "flyway")
    public Flyway createFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setLocations("sql");//todo add java based migration dir
        return flyway;
    }

 /*
       <bean id="flyway" class="org.flywaydb.core.Flyway" init-method="migrate">
        <property name="dataSource" ref="dbDataSource"/>
        <property name="locations" value="sql" />
    </bean>
     */
}
