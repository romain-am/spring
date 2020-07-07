package main.com.dragonsoft.onlineapp.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import main.com.dragonsoft.interceptor.HandlerInterceptor;

@Configuration
@ComponentScan("main.com.dragonsoft")
@EnableAsync
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/resources/fonts/").setCachePeriod(31556926);
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/resources/images/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/resources/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/resources/vendor/").setCachePeriod(31556926);
	}

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/resources/messages");
		messageSource.setCacheSeconds(10); //reload messages every 10 seconds
		return messageSource;
	}

	//Files upload configuration
	@Bean(name="filterMultipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setMaxUploadSize(20000000);
		commonsMultipartResolver.setResolveLazily(false);
		return commonsMultipartResolver;
	}

	//Repository populator
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() throws IOException {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();

		//Get all json files generated from the testing
		Resource dbpopulationjsonResourcePath = new ClassPathResource("/resources/db_population_json");
		File dbpopulationjsonFolder = dbpopulationjsonResourcePath.getFile();
		String[] jsonFiles = dbpopulationjsonFolder.list();

		if(jsonFiles.length > 0) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("The content of the files in the db_population_json folder are going to be inserted in the database ..");
			System.out.println("Do you want to proceed ? Y/N");
			String line = scanner.nextLine();

			if(line.equals("Y")) {
				List<ClassPathResource> jsonResources = new ArrayList<>();
				for(int i = 0; i < jsonFiles.length ; i++ ) {
					jsonResources.add(new ClassPathResource("/resources/db_population_json/"+jsonFiles[i]));
				}

				Resource[] resources = jsonResources.toArray(new ClassPathResource[0]);
				factory.setResources(resources);
			}
		}
		return factory;
	} 

}
