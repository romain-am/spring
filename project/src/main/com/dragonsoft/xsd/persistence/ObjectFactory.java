//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2020.06.08 � 06:07:59 PM CEST 
//


package main.com.dragonsoft.xsd.persistence;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jcp.xmlns.xml.ns.persistence package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jcp.xmlns.xml.ns.persistence
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Persistence }
     * 
     */
    public Persistence createPersistence() {
        return new Persistence();
    }

    /**
     * Create an instance of {@link Persistence.PersistenceUnit }
     * 
     */
    public Persistence.PersistenceUnit createPersistencePersistenceUnit() {
        return new Persistence.PersistenceUnit();
    }

    /**
     * Create an instance of {@link Persistence.PersistenceUnit.Properties }
     * 
     */
    public Persistence.PersistenceUnit.Properties createPersistencePersistenceUnitProperties() {
        return new Persistence.PersistenceUnit.Properties();
    }

    /**
     * Create an instance of {@link Persistence.PersistenceUnit.Properties.Property }
     * 
     */
    public Persistence.PersistenceUnit.Properties.Property createPersistencePersistenceUnitPropertiesProperty() {
        return new Persistence.PersistenceUnit.Properties.Property();
    }

}
