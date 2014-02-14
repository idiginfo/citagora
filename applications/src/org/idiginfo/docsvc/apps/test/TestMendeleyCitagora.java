package org.idiginfo.docsvc.apps.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Class to test harvested Mendeley
 * 
 * @author ssflager
 * 
 */

public class TestMendeleyCitagora {

	CitagoraFactory factory = CitagoraFactory.getFactory();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestMendeleyCitagora tester = new TestMendeleyCitagora();
		tester.run(args);

	}

	private void run(String[] args) {
		Container doc = null;

		String requestDoi = "10.1598/JAAL48.5.1";
		String requestKeyword = "suicide";

		doc = getMendeleyDocument(requestKeyword);
		String rdf = writeCitagoraRdf(doc, null, -1);
		FileWriter out;
		try {
			out = new FileWriter("c:/dev/api samples/Mendeley1.rdf");
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

	Container getMendeleyDocument(String requestParam) {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		DocService service = serviceFactory
				.createService(ServiceFactory.COLLECTION_MENDELEY);
		factory.openTransaction();
		ApiParams params = serviceFactory
				.createApiParams(ServiceFactory.COLLECTION_MENDELEY);
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		// params.setId(requestDoi);
		params.setKeyword(requestParam);
		// Document document = service.getDocument(params);
		// System.out.println(document.getId());
		List<? extends Document> documents = service.getDocuments(params);

		/*
		 * MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
		 * Container container = documentMapper .createContainer(null,
		 * document); factory.commitTransaction(); return container;
		 */
		return null;
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
