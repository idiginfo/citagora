package org.idiginfo.docsvc.svcapi.msrc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

public class MsrcDocument implements Document {
	String id;
	String title;
	String pmid;
	String year;
	String journalTitle;
	String isbn;
	String volume;
	String issue;
	String pages;
	String publicationDate;
	String language;
	@SerializedName("abstract")
	String abstractBody;
	String url;
	String citationKey;
	String doi;
	String authorAddress;
	List<String> authorList;
	List<String> keywords;
	List<String> meshTerms;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAbstractBody() {
		return abstractBody;
	}

	public void setAbstractBody(String abstractBody) {
		this.abstractBody = abstractBody;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCitationKey() {
		return citationKey;
	}

	public void setCitationKey(String citationKey) {
		this.citationKey = citationKey;
	}

	public String getDoi() {
		if (doi != null && doi.startsWith("10.")) {
			return doi;
		}
		// not a real doi, ignore it
		setDoi(null);
		return null;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getAuthorAddress() {
		return authorAddress;
	}

	public void setAuthorAddress(String authorAddress) {
		this.authorAddress = authorAddress;
	}

	public List<String> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<String> authorList) {
		this.authorList = authorList;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getMeshTerms() {
		return meshTerms;
	}

	public void setMeshTerms(List<String> meshTerms) {
		this.meshTerms = meshTerms;
	}

	@Override
	public Annotation getAnnotation(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthors() {
		if (authorList == null)
			return null;
		return StringUtils.join(authorList, "; ");
	}

	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDateObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGUID() {
		return id;
	}

	@Override
	public String getIssn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumAnnotations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationName() {
		return journalTitle;
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSource() {
		return MsrcApiParams.SOURCE;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPageStart() {
		if (pages == null)
			return null;
		int separator = pages.indexOf('-');
		String pageStart = pages;
		if (separator >= 0) {
			pageStart = pages.substring(0, separator);
		}
		try {
			return Integer.valueOf(pageStart);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public Integer getPageEnd() {
		if (pages == null)
			return null;
		int separator = pages.indexOf('-');
		if (separator < 0)
			return null;
		String pageEnd = pages.substring(separator + 1);
		try {
			return Integer.valueOf(pageEnd);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssued() {
		return publicationDate;
	}

	@Override
	public String getAbstractText() {
		// TODO Auto-generated method stub
		return null;
	}

	static DateFormat issuedFormat = new SimpleDateFormat("MMM yyyy");
	static DateFormat yearFormat = new SimpleDateFormat("yyyy");

	@Override
	public Date getIssuedDate() {
		if (publicationDate == null) {
			return null;
		}
		try {
			return issuedFormat.parse(publicationDate);
		} catch (ParseException e) {
			// System.out.println("problem parsing publicationDate: '"
			// + publicationDate + "' for object " + id);
		}
		// strip off "SPR" or "FALL" etc.
		try {
			Matcher matcher = Pattern.compile("\\d\\d\\d\\d").matcher(
					publicationDate);
			matcher.find();
			publicationDate = matcher.group();
		} catch (Exception e) {
			if (year != null)
				publicationDate = year;
		}
		try {// parse as year
			return yearFormat.parse(publicationDate);
		} catch (ParseException e) {
			System.out.println("problem parsing publicationDate: '"
					+ publicationDate + "' for object " + id);
		}
		return null;
	}

	@Override
	public String getAggregationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getarXivId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoverDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEdition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String geteIssn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItemNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPMId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSeriesTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
