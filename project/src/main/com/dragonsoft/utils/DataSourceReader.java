package main.com.dragonsoft.utils;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import main.com.dragonsoft.xsd.persistence.Persistence;
import main.com.dragonsoft.xsd.persistence.Persistence.PersistenceUnit.Properties.Property;

public class DataSourceReader {
	private String jdbc_url;
	private String jdbc_user;
	private String jdbc_password;
	private String jdbc_driver;
	
	public void generateValues(String xmlPath) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Persistence.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File(xmlPath);
        Persistence config = (Persistence) unmarshaller.unmarshal(xml);
        
        List<Property> dataProps = config.getPersistenceUnit().get(0).getProperties().getProperty();
        dataProps.forEach((prop) -> {
        	switch(prop.getName()) {
        	  case "javax.persistence.jdbc.url":
        		  setJdbc_url(prop.getValue());
        	    break;
        	  case "javax.persistence.jdbc.user":
        		  setJdbc_user(prop.getValue());
        	    break;
        	  case "javax.persistence.jdbc.password":
        		  setJdbc_password(prop.getValue());
        		  break;
        	  case "javax.persistence.jdbc.driver":
        		  setJdbc_driver(prop.getValue());
        		  break;
        		  
        	}

        	
        });
	}
	
	//Getters and Setters
	public String getJdbc_url() {
		return jdbc_url;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	public String getJdbc_user() {
		return jdbc_user;
	}

	public void setJdbc_user(String jdbc_user) {
		this.jdbc_user = jdbc_user;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}

	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}
	

}
