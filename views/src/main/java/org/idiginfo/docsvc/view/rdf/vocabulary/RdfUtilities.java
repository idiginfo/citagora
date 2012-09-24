package org.idiginfo.docsvc.view.rdf.vocabulary;

import java.util.Date;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RdfUtilities {

    public static Resource getOrAddResource(Model model, String uri) {
	Resource resource = model.getResource(uri);
	if (resource == null)
	    resource = model.createResource(uri);
	return resource;
    }

    public static void addProperty(Model model, Resource r, Property p,
	    String value) {
	if (value == null || value.equals(""))
	    return;
	model.add(r, p, (String) value);
    }

    public static void addProperty(Model model, Resource resource,
	    Property relationship, Resource value) {
	if (value == null)
	    return;
	model.add(resource, relationship, value);
    }

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
