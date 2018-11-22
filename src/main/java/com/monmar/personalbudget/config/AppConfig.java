package com.monmar.personalbudget.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.monmar.personalbudget")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

//    @Bean
	public DataSource dataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

//Settings from persistence-mysql.properties

//        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
//        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
//
//        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//        securityDataSource.setUser(env.getProperty("jdbc.user"));
//        securityDataSource.setPassword(env.getProperty("jdbc.password"));

		String herokuDbUrl = System.getenv("HEROKU_DB");
		final String USER_NAME = herokuDbUrl.split(":")[0];
		final String PASSWORD = herokuDbUrl.split(":")[1];
		final String DB_URL = "jdbc:mysql://" + herokuDbUrl.split(":")[2];

		logger.info("==========>>>>>conecting with jdbc.url=" + DB_URL);
		logger.info("=============>>>>>>jdbc.user=" + USER_NAME);

		securityDataSource.setJdbcUrl(DB_URL);
		securityDataSource.setUser(USER_NAME);
		securityDataSource.setPassword(PASSWORD);

		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

//    Heroku
//    @Bean
//    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//        
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        basicDataSource.setInitialSize(getIntProperty("connection.pool.initialPoolSize"));
//        basicDataSource.setMaxIdle(getIntProperty("connection.pool.maxIdleTime"));
//        basicDataSource.setMaxActive(getIntProperty("connection.pool.maxPoolSize"));
//
//        return basicDataSource;
//    }

	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

	private Properties getHibernateProperties() {

		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		props.setProperty("hibernate.connection.useUnicode", env.getProperty("hibernate.connection.useUnicode"));
		props.setProperty("hibernate.connection.characterEncoding",
				env.getProperty("hibernate.connection.characterEncoding"));
		return props;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	// Heroku
//    @Bean
//    @Autowired
//    public LocalSessionFactoryBean sessionFactory(BasicDataSource dataSource) {
//
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
//        sessionFactory.setHibernateProperties(getHibernateProperties());
//
//        return sessionFactory;
//    }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {

		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

		Properties mapping = new Properties();
		mapping.put("com.monmar.exchangeratenbpapi.exception.ExchangeRateNotFoundException", "error-404-code");
		mapping.put("com.monmar.exchangeratenbpapi.exception.ExchangeRateBadRequestException", "error-400-code");
		resolver.setExceptionMappings(mapping);

		return resolver;
	}

}
