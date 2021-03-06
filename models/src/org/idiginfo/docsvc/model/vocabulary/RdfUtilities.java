package org.idiginfo.docsvc.model.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RdfUtilities {

    /**
     * Acquire the resource, or create it if null
     * 
     * @param model
     *            the current model
     * @param uri
     *            of the resource
     * @return the resource
     */
    public static Resource getOrAddResource(Model model, String uri) {
	Resource resource = model.getResource(uri);
	if (resource == null)
	    resource = model.createResource(uri);
	return resource;
    }

    /**
     * Add Statement with object of type String to the RDF Model
     * <p>
     * String parameter of null or empty string is ignored
     * 
     * @param model
     *            the RDF model
     * @param r
     *            the subject of the RDF triple
     * @param p
     *            the predicate of the RDF triple
     * @param value
     *            object (of type String) of the RDF triple
     */
    public static void addProperty(Model model, Resource r, Property p,
	    String value) {
	if (p == null || value == null || value.equals(""))
	    return;
	model.add(r, p, (String) value);
    }

    /**
     * Add Statement with object of type Resource to the RDF Model
     * <p>
     * Resource parameter of null is ignored
     * 
     * @param model
     *            the RDF model
     * @param resource
     *            the subject of the RDF triple
     * @param relationship
     *            the predicate of the RDF triple
     * @param value
     *            object (of type Resource) of the RDF triple
     */
    public static void addProperty(Model model, Resource resource,
	    Property relationship, Resource value) {
	if (value == null || relationship == null)
	    return;
	model.add(resource, relationship, value);
    }

    /**
     * Return Resource instance with given URI for this Model
     * 
     * @param model
     *            the RDF model
     * @param uri
     *            of the resource
     */
    public static Resource getResource(Model model, String uri) {
	return model.getResource(uri);
    }

    public static String getProperties(Resource resource) {
	StringBuffer out = new StringBuffer();
	out.append(resource.getURI()).append(": ");
	StmtIterator properties = resource.listProperties();
	while (properties.hasNext()) {
	    Statement stmt = properties.next();
	    Property predicate = stmt.getPredicate();
	    out.append(predicate.getLocalName()).append(" ");
	}
	return out.toString();
    }
}
