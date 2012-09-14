package org.idiginfo.docsvc.view.rdf.vocabulary;

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
    public static final String documentType = NS + "documentType";
    public static final String tagType = NS + "tagType";
    public static final String ratingType = NS + "ratingType";
    public static final String commentType = NS + "comment";
    public static final String agentType = NS + "agent";
}
