/**
 * Package for implementation of the Citagora data model, 
 * using the interfaces of package org.idiginfo.docsvc.model.citagora
 */
/**
 * @author griccardi
 * 
 * This package provides implementations of the Citagora data model objects that are used for
 * A. persistence in the database using JPA. Annotations form javax.persistence are provided to control
 * 	 the persistence
 * B. serialization in json using the gson package. Annotations and 'transient' keyword are provided for
 * 	the json serialization
 * 	For relationships, the 'transient' keyword is applied to one side of each bi-directional relationship
 * 	to avoid circularities in serialization
 */
package org.idiginfo.docsvc.jpa.citagora;