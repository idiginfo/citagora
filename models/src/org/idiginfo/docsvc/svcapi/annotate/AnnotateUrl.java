package org.idiginfo.docsvc.svcapi.annotate;

import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class AnnotateUrl extends GenericUrl {

	public AnnotateUrl(String function) {
		this(function, AnnotateApiParams.VALID_FOR);
	}

	public AnnotateUrl(String function, long validFor) {
		this(function, validFor, AnnotateApiParams.API_USER);
	}

	public AnnotateUrl(String function, long validFor, String annotateUser) {
		super(AnnotateApiParams.API_URL + function);
		this.function = function;
		this.validFor = validFor;
		this.apiAnnotateUser = annotateUser;
	}

	public AnnotateUrl(String function, AnnotateApiParams params) {
		this(function);
		mapParams(params);
		this.params = params;
	}

	// Sample request url
	// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=dRqqvf6lpIG0vFRXwwuykW6uk2Q%3D&api-requesttime=1343245507&api-user=casey.mclaughlin@cci.fsu.edu

	protected long validFor;
	protected String function;
	protected AnnotateApiParams params = null;

	@Key("api-user")
	protected String apiUser = AnnotateApiParams.API_USER;
	@Key("api-requesttime")
	protected String apiRequestTime;
	@Key("api-auth")
	protected String apiAuth;
	@Key("api-annotateuser")
	protected String apiAnnotateUser = AnnotateApiParams.API_USER;
	@Key("d")
	protected String date;
	@Key("c")
	protected String code;
	@Key("allusers")
	String allusers; // set to 1 to return activity for all users in account
	@Key("from")
	protected String fromMonth; // YYYY_MM (optional)
	@Key("to")
	protected String toMonth;
	@Key("withnotes")
	protected String withNotes;
	@Key("withmeta")
	protected String withMeta;
	@Key("p")
	protected String parentFolder;
	@Key("subset")
	protected String subset;// '0': all pages; '1': just those with notes
	@Key("exportFormat")
	protected String exportFormat; // margin (optional - added Sep 2010):
	@Key("docurl")
	protected String docurl; // http URL to fetch .docx file
	@Key("docname")
	protected String docname; // Filename of .docx file (or .xml word2003)
	@Key("docxmltype")
	protected String docxmltype; // optional: if uploading word2003 .xml

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {
		setApiParams();
	}

	/**
	 * Get the current time as requestTime Create the api-auth value using a
	 * base64 encoded HMAC / SHA1 hash of a string. The string to be encoded is
	 * specified by the A.nnotate API reference
	 * http://a.nnotate.com/api-reference.html
	 * 
	 * @return true if the process worked properly
	 */
	public boolean setApiParams() {
		try {
			long requestTime = (new Date()).getTime() / 1000 + validFor;
			apiRequestTime = Long.toString(requestTime);
			String stringToSign = function + "\n" + apiUser + "\n"
					+ apiRequestTime + "\n" + apiAnnotateUser;
			Mac mac = Mac.getInstance("HmacSHA1");
			byte[] keyBytes = AnnotateApiParams.API_KEY.getBytes("UTF8");
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
			mac.init(signingKey);
			byte[] signBytes = mac.doFinal(stringToSign.getBytes("UTF8"));
			byte[] encodedSign = Base64.encodeBase64(signBytes);
			String signature = new String(encodedSign).replaceAll("\n", "");
			apiAuth = signature;
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static final String ANNOTATE_ERROR_STRING = "ERR";

	public static boolean isError(String content) {
		if (content.startsWith(ANNOTATE_ERROR_STRING)) {
			return true;
		}
		return false;
	}

	private void mapParams(AnnotateApiParams params) {
		if (params == null) return;
		this.apiUser = params.getApiUser();
		this.apiRequestTime = params.getApiRequestTime();
		this.apiAuth = params.apiAuth;
		this.apiAnnotateUser = params.getOwner();
		this.date = params.getDate();
		this.code = params.getId();
		this.allusers = params.allusers;
		this.fromMonth = params.fromMonth;
		this.toMonth = params.toMonth;
		this.withNotes = params.withNotes;
		this.withMeta = params.withMeta;
		this.parentFolder = params.parentFolder;
		this.subset = params.subset;
		this.exportFormat = params.exportFormat;
		this.docurl = params.docurl;
		this.docname = params.docname;
		this.docxmltype = params.docxmltype;
	}

	public long getValidFor() {
		return validFor;
	}

	public void setValidFor(long validFor) {
		this.validFor = validFor;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

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

	public String getApiAnnotateUser() {
		return apiAnnotateUser;
	}

	public void setApiAnnotateUser(String apiAnnotateUser) {
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

	public String getWithMeta() {
		return withMeta;
	}

	public void setWithMeta(String withMeta) {
		this.withMeta = withMeta;
	}

	public String getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}

}
