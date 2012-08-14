package org.idiginfo.docservices.rdf.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class RdfUtilities {

	static Model myModel = ModelFactory.createDefaultModel();

	public static void addProperty(Resource r, Property p, String value) {
		addProperty(myModel, r, p, value);
	}

	public static void addProperty(Model model, Resource r, Property p,
			String value) {
		if (value == null || value.equals(""))
			return;
		model.add(r, p, value);
	}

	public static Model getModel() {
		return myModel;
	}

	public static void setModel(Model myModel) {
		RdfUtilities.myModel = myModel;
	}
}
