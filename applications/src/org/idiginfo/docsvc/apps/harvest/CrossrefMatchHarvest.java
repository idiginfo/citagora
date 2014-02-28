package org.idiginfo.docsvc.apps.harvest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.MatchResult;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Reference;

/**
 * Class to Harvest content via Crossref source
 * 
 * @author griccardi
 * 
 */

public class CrossrefMatchHarvest {

	static final int NUM_RESULTS_PER_HARVEST = 5;
	static final int FIRST_RESULT = 1;
	private static final int FIRST_PAGE = 0;
	CitagoraFactory factory;
	ServiceFactory serviceFactory;
	DocService service;

	public CrossrefMatchHarvest() {
		CitagoraFactory.setPersistence("local");
		factory = CitagoraFactory.getFactory();
		factory.init();
		serviceFactory = ServiceFactory.getFactory();
		service = serviceFactory
				.createService(ServiceFactory.COLLECTION_CROSSREF);
	}

	private static void run(String[] args) {
		CrossrefMatchHarvest harvester = new CrossrefMatchHarvest();
		String filePrefix = ParameterConstants.Crossref.FILE_PREFIX;
		File dir = new File(ParameterConstants.Crossref.FILE_DIR);
		@SuppressWarnings("unused")
		boolean res = false;
		if(! dir.exists()){
			res = dir.mkdir();
		}
		int numPerFile = NUM_RESULTS_PER_HARVEST;
		if (args != null && args.length > 0) {
			filePrefix = args[0];
		}
		if (args != null && args.length > 1) {
			numPerFile = Integer.parseInt(args[1]);
		}
		harvester.harvestMatches(filePrefix, numPerFile);
	}

	void harvestMatches(String filePrefix, int numPerFile) {
		int numMissing = factory.getNumMissingDois();
		int totalPages = (int) Math.ceil(numMissing / numPerFile) + 1;
		System.out.println("Total results: " + numMissing + " pages: "
				+ totalPages + " per file: " + numPerFile);
		for (int pageNum = FIRST_PAGE; pageNum < totalPages; pageNum++) {
			int firstResult = pageNum * numPerFile;

			String doiFileName = filePrefix + String.format("%04d", pageNum)
					+ ".txt";
			String noDoiFileName = filePrefix + "no"
					+ String.format("%04d", pageNum) + ".txt";
			String[] results = harvestMatches(firstResult, numPerFile);
			writeFile(doiFileName, results[0]);
			writeFile(noDoiFileName, results[1]);
		}
	}

	void writeFile(String fileName, String contents) {
		try {
			File dir = new File(ParameterConstants.Crossref.FILE_DIR);
			@SuppressWarnings("unused")
			boolean res = false;
			if(! dir.exists()){
				res = dir.mkdir();
			}
			FileWriter out = new FileWriter(fileName);
			out.write(contents);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String[] harvestMatches(int firstResult, int numResults) {
		List<Reference> missingDois = factory.getMissingDois(firstResult,
				numResults);
		List<String> refStrings = new Vector<String>();
		for (Reference ref : missingDois) {
			String refString = getReferenceString(ref);
//			if(refString.startsWith("null")) continue;
			refStrings.add(refString);
		}
		List<? extends MatchResult> matches = getMatches(refStrings);
		for (MatchResult match : matches) {
			printResponse(match);
		}
		String[] matchResults = reportMatches(missingDois, matches);
		return matchResults;
	}

	private String[] reportMatches(List<Reference> missingDois,
			List<? extends MatchResult> matches) {
		int size = missingDois.size();
		if (size != matches.size()) {
			System.err.println("problem with lists: missingDois: " + size
					+ " matches: " + matches.size());
		}
		StringBuffer dois = new StringBuffer("MSRC URI\tDOI\tScore\n");
		StringBuffer noDois = new StringBuffer(
				"MSRC URI\tRef String\tReason\tScore\n");

		for (int i = 0; i < size; i++) {
			Reference ref = missingDois.get(i);
			MatchResult match = matches.get(i);
			if (match.getMatch()) {
				// doi found
				dois.append(ref.getUri()).append("\t");
				dois.append(match.getDoi()).append("\t");
				dois.append(match.getScore()).append("\n");
			} else {
				// doi not found
				noDois.append(ref.getUri()).append("\t");
				noDois.append(match.getText()).append("\t");
				noDois.append(match.getReason()).append("\t");
				noDois.append(match.getScore()).append("\n");
			}
		}
		String[] results = { dois.toString(), noDois.toString() };
		return results;
	}

	List<? extends MatchResult> getMatches(List<String> references) {
		String[] refStrings = references.toArray(new String[0]);
		List<? extends MatchResult> record = service.getMatch(refStrings);
		return record;
	}

	static DateFormat dateFormat = new SimpleDateFormat("MMM. YYYY");

	/**
	 * Create a crossref reference string example" "M. Henrion, D. J. Mortlock,
	 * D. J. Hand, and A. Gandy, \"A Bayesian approach to star-galaxy
	 * classification,\" Monthly Notices of the Royal Astronomical Society, vol.
	 * 412, no. 4, pp. 2286-2302, Apr. 2011."
	 * 
	 * @param ref
	 * @return
	 */
	String getReferenceString(Reference ref) {
		StringBuffer out = new StringBuffer();
		out.append(ref.getAuthorString());
		out.append(", \"").append(ref.getTitle()).append(",\" ");
		Reference journal = ref.isPartOf();
		if (journal != null) {// add journal info
			out.append(journal.getTitle()).append(", ");
		}
		String volume = ref.getVolume();
		if (volume != null) {
			out.append("vol. ").append(volume).append(", ");
		}
		String issue = ref.getIssue();
		if (issue != null) {
			out.append("no. ").append(issue).append(", ");
		}
		String pages = ref.getPages();
		if (pages != null) {
			out.append("pp. ").append(pages).append(", ");
		} else {
			Integer pageStart = ref.getPageStart();
			Integer pageEnd = ref.getPageEnd();
			if (pageStart != null && pageEnd != null) {
				out.append("pp. ").append(pageStart).append("-")
						.append(pageEnd).append(", ");
			}
		}
		Date datePublished = ref.getIssued();
		if (datePublished != null) {
			String date = dateFormat.format(datePublished);
			out.append(date);
		}
		out.append(".");
		return out.toString();
	}

	void printResponse(MatchResult result) {
		System.out.println("<match>");
		System.out.println("\tmatch: " + result.getMatch());
		System.out.println("\tdoi: " + result.getDoi());
		System.out.println("\tscore: " + result.getScore());
		System.out.println("\ttext: " + result.getText());
		System.out.println("\treason: " + result.getReason());
		System.out.println("</match>");
	}

	public static void main(String[] args) {
		run(args);
		return;
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
