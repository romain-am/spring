//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2020.06.08 � 06:07:59 PM CEST 
//


package main.com.dragonsoft.xsd.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="persistence-unit" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="provider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="jta-data-source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="non-jta-data-source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="mapping-file" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="jar-file" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="class" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="exclude-unlisted-classes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="shared-cache-mode" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-caching-type" minOccurs="0"/>
 *                   &lt;element name="validation-mode" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-validation-mode-type" minOccurs="0"/>
 *                   &lt;element name="properties" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="property" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="transaction-type" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-transaction-type" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://xmlns.jcp.org/xml/ns/persistence}versionType" fixed="2.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "persistenceUnit"
})
@XmlRootElement(name = "persistence")
public class Persistence {

    @XmlElement(name = "persistence-unit", required = true)
    protected List<Persistence.PersistenceUnit> persistenceUnit;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the persistenceUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the persistenceUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersistenceUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Persistence.PersistenceUnit }
     * 
     * 
     */
    public List<Persistence.PersistenceUnit> getPersistenceUnit() {
        if (persistenceUnit == null) {
            persistenceUnit = new ArrayList<Persistence.PersistenceUnit>();
        }
        return this.persistenceUnit;
    }

    /**
     * Obtient la valeur de la propri�t� version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "2.1";
        } else {
            return version;
        }
    }

    /**
     * D�finit la valeur de la propri�t� version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }


    /**
     * 
     * 
     *                 Configuration of a persistence unit.
     * 
     *               
     * 
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="provider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="jta-data-source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="non-jta-data-source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="mapping-file" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="jar-file" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="class" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="exclude-unlisted-classes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="shared-cache-mode" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-caching-type" minOccurs="0"/>
     *         &lt;element name="validation-mode" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-validation-mode-type" minOccurs="0"/>
     *         &lt;element name="properties" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="property" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="transaction-type" type="{http://xmlns.jcp.org/xml/ns/persistence}persistence-unit-transaction-type" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "description",
        "provider",
        "jtaDataSource",
        "nonJtaDataSource",
        "mappingFile",
        "jarFile",
        "clazz",
        "excludeUnlistedClasses",
        "sharedCacheMode",
        "validationMode",
        "properties"
    })
    public static class PersistenceUnit {

        protected String description;
        protected String provider;
        @XmlElement(name = "jta-data-source")
        protected String jtaDataSource;
        @XmlElement(name = "non-jta-data-source")
        protected String nonJtaDataSource;
        @XmlElement(name = "mapping-file")
        protected List<String> mappingFile;
        @XmlElement(name = "jar-file")
        protected List<String> jarFile;
        @XmlElement(name = "class")
        protected List<String> clazz;
        @XmlElement(name = "exclude-unlisted-classes", defaultValue = "true")
        protected Boolean excludeUnlistedClasses;
        @XmlElement(name = "shared-cache-mode")
        @XmlSchemaType(name = "token")
        protected PersistenceUnitCachingType sharedCacheMode;
        @XmlElement(name = "validation-mode")
        @XmlSchemaType(name = "token")
        protected PersistenceUnitValidationModeType validationMode;
        protected Persistence.PersistenceUnit.Properties properties;
        @XmlAttribute(name = "name", required = true)
        protected String name;
        @XmlAttribute(name = "transaction-type")
        protected PersistenceUnitTransactionType transactionType;

        /**
         * Obtient la valeur de la propri�t� description.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * D�finit la valeur de la propri�t� description.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Obtient la valeur de la propri�t� provider.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProvider() {
            return provider;
        }

        /**
         * D�finit la valeur de la propri�t� provider.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProvider(String value) {
            this.provider = value;
        }

        /**
         * Obtient la valeur de la propri�t� jtaDataSource.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJtaDataSource() {
            return jtaDataSource;
        }

        /**
         * D�finit la valeur de la propri�t� jtaDataSource.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJtaDataSource(String value) {
            this.jtaDataSource = value;
        }

        /**
         * Obtient la valeur de la propri�t� nonJtaDataSource.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNonJtaDataSource() {
            return nonJtaDataSource;
        }

        /**
         * D�finit la valeur de la propri�t� nonJtaDataSource.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNonJtaDataSource(String value) {
            this.nonJtaDataSource = value;
        }

        /**
         * Gets the value of the mappingFile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mappingFile property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMappingFile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getMappingFile() {
            if (mappingFile == null) {
                mappingFile = new ArrayList<String>();
            }
            return this.mappingFile;
        }

        /**
         * Gets the value of the jarFile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the jarFile property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getJarFile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getJarFile() {
            if (jarFile == null) {
                jarFile = new ArrayList<String>();
            }
            return this.jarFile;
        }

        /**
         * Gets the value of the clazz property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the clazz property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClazz().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getClazz() {
            if (clazz == null) {
                clazz = new ArrayList<String>();
            }
            return this.clazz;
        }

        /**
         * Obtient la valeur de la propri�t� excludeUnlistedClasses.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isExcludeUnlistedClasses() {
            return excludeUnlistedClasses;
        }

        /**
         * D�finit la valeur de la propri�t� excludeUnlistedClasses.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setExcludeUnlistedClasses(Boolean value) {
            this.excludeUnlistedClasses = value;
        }

        /**
         * Obtient la valeur de la propri�t� sharedCacheMode.
         * 
         * @return
         *     possible object is
         *     {@link PersistenceUnitCachingType }
         *     
         */
        public PersistenceUnitCachingType getSharedCacheMode() {
            return sharedCacheMode;
        }

        /**
         * D�finit la valeur de la propri�t� sharedCacheMode.
         * 
         * @param value
         *     allowed object is
         *     {@link PersistenceUnitCachingType }
         *     
         */
        public void setSharedCacheMode(PersistenceUnitCachingType value) {
            this.sharedCacheMode = value;
        }

        /**
         * Obtient la valeur de la propri�t� validationMode.
         * 
         * @return
         *     possible object is
         *     {@link PersistenceUnitValidationModeType }
         *     
         */
        public PersistenceUnitValidationModeType getValidationMode() {
            return validationMode;
        }

        /**
         * D�finit la valeur de la propri�t� validationMode.
         * 
         * @param value
         *     allowed object is
         *     {@link PersistenceUnitValidationModeType }
         *     
         */
        public void setValidationMode(PersistenceUnitValidationModeType value) {
            this.validationMode = value;
        }

        /**
         * Obtient la valeur de la propri�t� properties.
         * 
         * @return
         *     possible object is
         *     {@link Persistence.PersistenceUnit.Properties }
         *     
         */
        public Persistence.PersistenceUnit.Properties getProperties() {
            return properties;
        }

        /**
         * D�finit la valeur de la propri�t� properties.
         * 
         * @param value
         *     allowed object is
         *     {@link Persistence.PersistenceUnit.Properties }
         *     
         */
        public void setProperties(Persistence.PersistenceUnit.Properties value) {
            this.properties = value;
        }

        /**
         * Obtient la valeur de la propri�t� name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * D�finit la valeur de la propri�t� name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Obtient la valeur de la propri�t� transactionType.
         * 
         * @return
         *     possible object is
         *     {@link PersistenceUnitTransactionType }
         *     
         */
        public PersistenceUnitTransactionType getTransactionType() {
            return transactionType;
        }

        /**
         * D�finit la valeur de la propri�t� transactionType.
         * 
         * @param value
         *     allowed object is
         *     {@link PersistenceUnitTransactionType }
         *     
         */
        public void setTransactionType(PersistenceUnitTransactionType value) {
            this.transactionType = value;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="property" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "property"
        })
        public static class Properties {

            protected List<Persistence.PersistenceUnit.Properties.Property> property;

            /**
             * Gets the value of the property property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the property property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getProperty().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Persistence.PersistenceUnit.Properties.Property }
             * 
             * 
             */
            public List<Persistence.PersistenceUnit.Properties.Property> getProperty() {
                if (property == null) {
                    property = new ArrayList<Persistence.PersistenceUnit.Properties.Property>();
                }
                return this.property;
            }


            /**
             * <p>Classe Java pour anonymous complex type.
             * 
             * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Property {

                @XmlAttribute(name = "name", required = true)
                protected String name;
                @XmlAttribute(name = "value", required = true)
                protected String value;

                /**
                 * Obtient la valeur de la propri�t� name.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * D�finit la valeur de la propri�t� name.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Obtient la valeur de la propri�t� value.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * D�finit la valeur de la propri�t� value.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

            }

        }

    }

}
