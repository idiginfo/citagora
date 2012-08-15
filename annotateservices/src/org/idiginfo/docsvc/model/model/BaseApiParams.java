package org.idiginfo.docsvc.model.model;

public class BaseApiParams implements ApiParams {
	protected String method;
	protected String collection; // the source collection
	protected String format;
	protected String id;
	protected String date;
	protected String apiUser;
	protected String owner;
	protected String keyword;
	// From A.nnotate API
	protected String apiRequestTime;
	// From Springer API
	protected String constraint;
	protected String doi;
	protected String subject;
	protected String pub;
	protected String year;
	protected String country;
	protected String isbn;
	protected String issn;
	protected String openaccess;
	protected String type;
	protected String imageType;
	protected String title;
	protected String orgname;
	protected String journal;
	protected String book;
	protected String name;
	protected String sort;

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
}
