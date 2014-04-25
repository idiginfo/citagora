package org.idiginfo.docsvc.controller.request;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
//import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.HtmlDocumentWriter;
import org.idiginfo.docsvc.view.JsonWriter;
import org.idiginfo.docsvc.view.RdfWriter;

/**
 * Class to process content request via Persistence or Service API
 * 
 * @author griccardi
 * 
 */

public class RequestProcessor {

	public class Result {
		public String body;

		public String mimeType;

		public Status statusCode;

		public int numPersisted = 0;

		Result() {
		}

		Result(Status statusCode, String body) {
			this(statusCode, body, MediaType.TEXT_HTML);
		}

		Result(Status statusCode, String body, String mimeType) {
			this.statusCode = statusCode;
			this.body = body;
			this.mimeType = mimeType;
		}
	}

	// output writers
	RdfWriter rdfWriter = new RdfWriter();
	// persistence mapper
	MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();

	public RequestProcessor() {
	}

	/**
	 * Validate params and identify the required service Then call
	 * getObjects(service,params)
	 * 
	 * @param params
	 * @return
	 */
	public Object getObjects(ApiParams params) {
		String collection = params.getCollection();
		if (collection == null) {
			return new Result(Status.BAD_REQUEST,
					"collection must be specified");
		}
		if (collection.equals(ServiceFactory.COLLECTION_CITAGORA)) {
			// access objects already in the repository
			return getCitagoraObjects(params);
		} else {
			DocService service = ServiceFactory.getFactory().getSharedService(
					collection);
			if (service == null) {
				return new Result(Status.BAD_REQUEST, "source "
						+ collection + " is unknown");
			}
			return getObjects(service, params);
		}
	}

	CitagoraFactory factory = CitagoraFactory.getFactory();

	private Object getCitagoraObjects(ApiParams params) {
		String doi = params.getDoi();
		String collection = params.getCollection();
		if (doi != null) {
			System.out.print(" doi: " + doi);
			Reference ref = factory.findReferenceBySourceDoi(collection, doi);
			if (ref != null) {
				// get the containers for the document
				List<Container> containers = ref.getContainers();
				System.out.println(" found");
				return containers;
			}
			System.out.println(" not found");
			return null;
		}
		String uri = params.getId();
		if (uri != null || !"".equals(uri)) {
			System.out.print(" uri: " + uri);
			CitagoraObject obj = factory.findCitagoraObjectByURI(uri);
			if (obj != null) {
				System.out.println(" found");
				List<CitagoraObject> objs = new Vector<CitagoraObject>();
				objs.add(obj);
				return objs;
			}
			System.out.println(" not found ");
			return null;
		}
		System.out.println("no id in params");
		return null;
	}

	/**
	 * Extract method from params and call appropriate service method
	 * 
	 * @param service
	 * @param params
	 * @return
	 */
	public Object getObjects(DocService service, ApiParams params) {
		if (params == null)
			return null;
		Result result = new Result();
		Document document = null;
		List<? extends Document> documents = null;

		Object objects = null;
		if (params.getMethod() == null) {
			result.statusCode = Status.NOT_FOUND;
			result.body = "'method' parameter must be supplied";
			return result;
		}
		if (params.getMethod().equals(DocServicesParams.METHOD_GET_DOCUMENTS)) {
			documents = service.getDocuments(params);
			objects = documents;
		} else if (params.getMethod().equals(
				DocServicesParams.METHOD_GET_DOCUMENT)) {
			document = service.getDocument(params);
			objects = document;
		} else if (params.getMethod().equals(
				DocServicesParams.METHOD_GET_ANNOTATIONS)) {
			document = service.getAnnotations(params);
			objects = document;
		} else {
			result.statusCode = Status.NOT_FOUND;
			result.body = "'method' parameter value '" + params.getMethod()
					+ "' not allowed";
			return result;
		}
		// persist the results, if requested
		if (params.getPersist()) {
			if (document != null) {
				result.numPersisted = persist(document);
			} else if (documents != null) {
				result.numPersisted = persist(documents);
			}
		}
		return objects;
	}

	/**
	 * Get the requested objects Create the proper view of the objects based on
	 * requested format Attach the status to the view and return as Result
	 * 
	 * @param params
	 * @return
	 */
	public Result processRequest(ApiParams params) {
		Object objects = getObjects(params);
		String body = null;
		String format = params.getFormat();
		String collection = params.getSource();
		if (DocServicesParams.FORMAT_JSON.equals(format)) {
			JsonWriter jsonWriter = new JsonWriter();
			body = jsonWriter.write(objects);
			return new Result(Status.OK, body, "application/json");
		} else if (DocServicesParams.FORMAT_XLS.equals(format)) {

		} else if (DocServicesParams.FORMAT_RDF.equals(format)) {
			RdfWriter rdfWriter = new RdfWriter();
			body = rdfWriter.write(objects);
			return new Result(Status.OK, body, "application/rdf+xml");
		} else {
			body = HtmlDocumentWriter.toHtml(params, objects);
			if (body == null)
				return new Result(Status.INTERNAL_SERVER_ERROR,
						"no body returned");
			return new Result(Status.OK, body);
		}
		return null;
	}

	/**
	 * Use the ContainerMapper to make the Document persistent
	 * 
	 * @param document
	 * @return
	 */
	public int persist(Document document) {
		Container container = documentMapper.createContainer(null, document);
		if (container != null)
			return 1;
		return 0;
	}

	/**
	 * Make all of the documents in the set persistent
	 * 
	 * @param documents
	 * @return
	 */
	public int persist(List<? extends Document> documents) {
		if (documents == null)
			return 0;
		int numPersisted = 0;
		for (Document document : documents) {
			numPersisted += persist(document);
		}
		return numPersisted;
	}
}
