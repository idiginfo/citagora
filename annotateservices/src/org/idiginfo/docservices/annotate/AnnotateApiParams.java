package org.idiginfo.docservices.annotate;

import org.idiginfo.docservices.model.ApiParams;

/**
 * Class that helps manage HTTP parameters for the a.nnotate service
 * 
 * @author griccardi
 * 
 */
public class AnnotateApiParams implements ApiParams {

	public static final String API_URL = "http://annotate.msrc.fsu.edu/php/";
	public static final String API_KEY = "giqfrstIk9b6CddDL3ogGTUac6Lr3II9";
	public static final String API_USER = "casey.mclaughlin@cci.fsu.edu";
	public static final long VALID_FOR = 0;

	protected String apiUser = AnnotateApiParams.API_USER;
	protected String apiRequestTime;
	protected String apiAuth;
	protected String apiAnnotateUser = AnnotateApiParams.API_USER;
	protected String date;
	protected String code;
	protected String allusers; // set to 1 to return activity for all users in
								// account
	protected String fromMonth; // YYYY_MM (optional)
	protected String toMonth;
	protected String withNotes;
	protected String withMeta;
	protected String parentFolder;
	protected String subset;// '0': all pages; '1': just those with notes
	protected String exportFormat; // margin (optional - added Sep 2010):
	protected String docurl; // http URL to fetch .docx file
	protected String docname; // Filename of .docx file (or .xml word2003)
	protected String docxmltype; // optional: if uploading word2003 .xml

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getApiRequestTime() {
		return apiRequestTime;
	}

	public void setApiRequestTime(String apiRequestTime) {
		this.apiRequestTime = apiRequestTime;
	}

	public String getApiAuth() {
		return apiAuth;
	}

	public void setApiAuth(String apiAuth) {
		this.apiAuth = apiAuth;
	}

	public String getOwner() {
		return apiAnnotateUser;
	}

	public void setOwner(String apiAnnotateUser) {
		this.apiAnnotateUser = apiAnnotateUser;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAllusers() {
		return allusers;
	}

	public void setAllusers(String allusers) {
		this.allusers = allusers;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getWithNotes() {
		return withNotes;
	}

	public void setWithNotes(String withNotes) {
		this.withNotes = withNotes;
	}

	public void setWithNotes(boolean withNotes) {
		if (withNotes) this.withNotes = "1";
		else
			this.withNotes = null;
	}

	public String getWithMeta() {
		return withMeta;
	}

	public void setWithMeta(String withMeta) {
		this.withMeta = withMeta;
	}

	public void setWithMeta(boolean withMeta) {
		if (withMeta) this.withMeta = "1";
		else
			this.withMeta = null;
	}

	public String getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}

	public String getSubset() {
		return subset;
	}

	public void setSubset(String subset) {
		this.subset = subset;
	}

	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}

	public String getDocurl() {
		return docurl;
	}

	public void setDocurl(String docurl) {
		this.docurl = docurl;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDocxmltype() {
		return docxmltype;
	}

	public void setDocxmltype(String docxmltype) {
		this.docxmltype = docxmltype;
	}

	@Override
	public String getSearchTerms() {
		// TODO Auto-generated method stub
		return null;
	}

}
