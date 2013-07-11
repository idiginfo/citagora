package org.idiginfo.docsvc.model.apisvc;

import java.lang.reflect.Field;

import com.google.api.client.http.GenericUrl;

/**
 * Interface to implement the ApiParams object
 * 
 * @author griccardi
 * 
 */

public class BaseApiParams implements ApiParams {
	public String method;
	public String collection; // the source collection
	public String format;
	public String id;
	public String date;
	public String apiUser;
	public String owner;
	public String keyword;
	// From A.nnotate API
	public String apiRequestTime;
	// From Springer API
	public String constraint;
	public String doi;
	public String subject;
	public String pub;
	public String year;
	public String country;
	public String isbn;
	public String issn;
	public String openaccess;
	public String type;
	public String imageType;
	public String title;
	public String orgname;
	public String journal;
	public String book;
	public String name;
	public String sort;
	public String withNotes;
	public String withMeta;
	public boolean persist = false;
	public Integer numResults;
	public Integer firstResult;

	/**
	 * Use reflection methods to set fields of an ApiParam object from an HTTP
	 * parameter map
	 * 
	 * @param toUrl
	 * @param queryParams
	 */
	public void mapFields(GenericUrl toUrl) {
		Class<? extends GenericUrl> toClass = toUrl.getClass();
		Class<? extends BaseApiParams> fromClass = getClass();
		Class<BaseApiParams> baseFromClass = BaseApiParams.class;
		// iterate through all parameters and set fields as possible
		Field[] fieldSet = toClass.getDeclaredFields();
		for (int i = 0; i < fieldSet.length; i++) {
			try {
				Field toField = fieldSet[i];
				toField.setAccessible(true);
				String key = toField.getName();
				// use reflection to set field
				Field fromField;
				try {
					fromField = fromClass.getDeclaredField(key);
				} catch (NoSuchFieldException e) {
					// didn't find field in API params class, look in
					// BaseApiParams
					fromField = baseFromClass.getDeclaredField(key);
				}
				fromField.setAccessible(true);
				toField.set(toUrl, fromField.get(this));
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				// System.out.println("param missing " + e.getMessage());
				// e.printStackTrace();// no such field or not allowed
			}
		}
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

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String getCollection() {
		return collection;
	}

	@Override
	public void setCollection(String collection) {
		this.collection = collection;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String code) {
		this.id = code;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String getApiUser() {
		return apiUser;
	}

	@Override
	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String getKeyword() {
		return keyword;
	}

	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String getSearchTerms() {
		return keyword;
	}

	@Override
	public void setSearchTerms(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String getConstraint() {
		return constraint;
	}

	@Override
	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public String getDoi() {
		return doi;
	}

	@Override
	public void setDoi(String doi) {
		this.doi = doi;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getPub() {
		return pub;
	}

	@Override
	public void setPub(String pub) {
		this.pub = pub;
	}

	@Override
	public String getYear() {
		return year;
	}

	@Override
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getIsbn() {
		return isbn;
	}

	@Override
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String getIssn() {
		return issn;
	}

	@Override
	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String getOpenaccess() {
		return openaccess;
	}

	@Override
	public void setOpenaccess(String openaccess) {
		this.openaccess = openaccess;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getImageType() {
		return imageType;
	}

	@Override
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getOrgname() {
		return orgname;
	}

	@Override
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Override
	public String getJournal() {
		return journal;
	}

	@Override
	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Override
	public String getBook() {
		return book;
	}

	@Override
	public void setBook(String book) {
		this.book = book;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSort() {
		return sort;
	}

	@Override
	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String getApiRequestTime() {
		return apiRequestTime;
	}

	@Override
	public void setApiRequestTime(String apiRequestTime) {
		this.apiRequestTime = apiRequestTime;
	}

	@Override
	public boolean getPersist() {
		return persist;
	}

	@Override
	public void setPersist(boolean persist) {
		this.persist = persist;
	}

	@Override
	public Integer getNumResults() {
		return numResults;
	}

	@Override
	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	@Override
	public Integer getFirstResult() {
		return firstResult;
	}

	@Override
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
}
