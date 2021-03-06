package org.idiginfo.docsvc.controller.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

public class TestModelCitagora {

	CitagoraFactory factory = CitagoraFactory.getFactory();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestModelCitagora tester = new TestModelCitagora();
		tester.run(args);

	}

	private void run(String[] args) {
		Container doc = null;

		String requestDoi = "doi:10.1007/s11276-008-0131-4";

		doc = getSpringerDocument(requestDoi);
		String rdf = writeCitagoraRdf(doc, null, -1);
		FileWriter out;
		try {
			out = new FileWriter("c:/dev/api samples/springer1.rdf");
			out.write(rdf);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// CitagoraObject doc2 = factory.findCitagoraObject(52);
		// System.out.println(doc2.getClass().getName());
	}

	void printInfo(String objName, String objUri) {
		System.out.println("+++Object " + objName + " : " + objUri);
	}

	Container getSpringerDocument(String requestDoi) {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		DocService service = serviceFactory
				.createService(ServiceFactory.COLLECTION_SPRINGER);
		factory.openTransaction();
		ApiParams params = serviceFactory
				.createApiParams(ServiceFactory.COLLECTION_SPRINGER);
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId(requestDoi);
		Document document = service.getDocument(params);
		System.out.println(document.getId());

		MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
		Container container = documentMapper.createContainer(null, document);
		factory.commitTransaction();
		return container;
	}

	public String writeCitagoraRdf(UriObject document, String version, int level) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.add(document, level - 1);
		Model model = mapper.getModel();
		StringWriter out = new StringWriter();
		model.write(out, version);
		return out.toString();
	}
}
