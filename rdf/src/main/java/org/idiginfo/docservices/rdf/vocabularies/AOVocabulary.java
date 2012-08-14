package org.idiginfo.docservices.rdf.vocabularies;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Vocabulary definitions from
 * http://annotation-ontology.googlecode.com/svn/trunk/annotation.owl
 * 
 * @author Auto-generated by schemagen on 13 Aug 2012 19:17
 */
public class AOVocabulary {
	/**
	 * <p>
	 * The RDF model that holds the vocabulary terms
	 * </p>
	 */
	private static Model m_model = ModelFactory.createDefaultModel();

	/**
	 * <p>
	 * The namespace of the vocabulary as a string
	 * </p>
	 */
	public static final String NS = "http://purl.org/ao/";

	/**
	 * <p>
	 * The namespace of the vocabulary as a string
	 * </p>
	 * 
	 * @see #NS
	 */
	public static String getURI() {
		return NS;
	}

	/**
	 * <p>
	 * The namespace of the vocabulary as a resource
	 * </p>
	 */
	public static final Resource NAMESPACE = m_model.createResource(NS);

}
