package main.com.dragonsoft.onlineapp.config;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.bind.JAXBException;

import org.springframework.content.jpa.config.EnableJpaStores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.utils.DataSourceReader;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"main.com.dragonsoft.users","main.com.dragonsoft.credentials", "main.com.dragonsoft.clients"})
@EnableJpaStores(basePackages = {"main.com.dragonsoft.content"})
public class JpaConfig {
	
	@Bean
	public DataSource dataSource() throws IOException, JAXBException {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    
	    Resource resource = new ClassPathResource("/META-INF/persistence.xml");
	    String xmlPath = resource.getFile().getAbsolutePath();
	    
	    DataSourceReader reader = new DataSourceReader();
	    reader.generateValues(xmlPath);
	 
	    dataSource.setDriverClassName(reader.getJdbc_driver());
	    dataSource.setUsername(reader.getJdbc_user());
	    dataSource.setPassword(reader.getJdbc_password());
	    dataSource.setUrl(
	      reader.getJdbc_url()+"?emulateLocators=true"); 
	    
	    reader = null;
	     
	    return dataSource;
	}
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("Database");
		
		return factoryBean;
	} 
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}
	
    @Bean
    public UserService userService() {
      return new UserService();
    }
}
