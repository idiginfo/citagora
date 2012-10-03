package org.idiginfo.docsvc.view.rdf.vocabulary;

import org.idiginfo.docsvc.model.citagora.UriObject;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Vocabulary definitions for http://citagora.org/terms
 * 
 * @author
 */
public class Citagora {

    private static Model m_model = ModelFactory.createDefaultModel();

    public static final String NS = "http://citagora.org/terms#";

    public static String getURI() {
	return NS;
    }

    public static final Resource NAMESPACE = m_model.createResource(NS);

    public static final Property hasComment = m_model.createProperty(NS
	    + "hasComment");

    public static final Property hasTag = m_model.createProperty(NS + "hasTag");

    public static final Property hasReply = m_model.createProperty(NS
	    + "hasReply");
    public static final Property hasRatingType = m_model.createProperty(NS
	    + "hasRatingType");

    // types
    public static final Resource documentType = m_model.createResource(NS + "documentType");
    public static final Resource tagType = m_model.createResource(NS + "tagType");
    public static final Resource ratingType = m_model.createResource(NS + "ratingType");
    public static final Resource commentType = m_model.createResource(NS + "comment");
    public static final Resource agentType = m_model.createResource(NS + "agent");
    
}
