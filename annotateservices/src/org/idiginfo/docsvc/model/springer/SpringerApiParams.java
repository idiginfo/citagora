package org.idiginfo.docsvc.model.springer;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.BaseApiParams;

public class SpringerApiParams extends BaseApiParams {

	public static final String API_URL = "http://api.springer.com/";
	public static final String API_KEY = "yprt5a5cy4pgj3788ewfj7wz";

	// Content Categories
	String[] contentCategories = { "Article", "Abstract", "Author",
			"Affiliation" };
	String[] constraints = {
			// equals constraints
			"doi", "subject", "keyword", "pub", "year", "country", "isbn",
			"issn", "date", "openaccess", "type", "imagetype",
			// contains constraints
			"", "title", "imagetype", "orgname", "journal", "book", "name",
			// display constraints
			"sort" };

	// Constraint values
	// doi: limit to a single Digital Object Identifier M,O,I
	// http://api.springer.com/images/xml?q=doi:10.1007/s00392-007-0618-5&api_key=...
	// subject: limit to the specified subject collection M,O,I
	// http://api.springer.com/openaccess/app?q=subject:Biochemistry&api_key=...
	// keyword: limit to articles tagged with a keyword M,O,I
	// http://api.springer.com/images/xml?q=keyword:%22nonlinear%20optimization%22&api_key=...
	// pub: limit to articles from a particular publication M,O
	// http://api.springer.com/metadata/pam?q=pub:Extremes&api_key=...
	// year: limit to articles/chapters published in a particular year M,O,I
	// http://api.springer.com/images/xml?q=year:2007&api_key=APIKEYHERE
	// country: limit to articles authored in a particular country M,O
	// http://api.springer.com/openaccess/app?q=country:%22New%20Zealand%22&api_key=...
	// isbn: limit to a single International Standard Book Number M,O,I
	// http://api.springer.com/metadata/json?q=isbn:978-0-387-79148-7&api_key=...
	// issn: limit to a single International Standard Serial Number M,O,I
	// http://api.springer.com/images/xml?q=issn:1861-0692&api_key=...
	// date: limit to documents published on a particular date {yyyy-mm-dd}
	// M,O,I
	// http://api.springer.com/openaccess/app?q=date:2010-03-01&api_key=...
	// openaccess: include only open access content: {true, false} I
	// http://api.springer.com/images/xml?q=openaccess:true&api_key=...
	// type: limit to either Book or Journal content {Book, Journal} M,O,I
	// http://api.springer.com/metadata/json?q=type:Book&api_key=...
	// imagetype: limit to images of a particular type: {Image, Table, Video} I
	// http://api.springer.com/images/jsonp?q=imagetype:Table&api_key=...
	// "empty" A word or phrase that appears among the constraints but is not
	// preceded by a constraint value will be treated as the argument of the
	// "empty constraint". Requests of this type will locate documents that
	// contain the specified word or phrase in any element. M,O,I
	// http://api.springer.com/images/json?q=%22user%20experience%22&api_key=...
	// title: locate documents containing a word or phrase in the
	// "article/chapter title" element M,O,I
	// http://api.springer.com/metadata/pam?q=title:%22game%20theory%22&api_key=...
	// orgname: locate documents containing a word or phrase in the
	// "organization name" element M,O
	// http://api.springer.com/metadata/json?q=orgname:Paris&api_key=...
	// journal: locate documents containing a word or phrase in the
	// "journal title" element M,O
	// http://api.springer.com/openaccess/app?q=journal:informatics&api_key=...
	// book: locate documents containing a word or phrase in the "book title"
	// element M,O
	// http://api.springer.com/metadata/pam?q=book:%22social%20network%22&api_key=...
	// name: locate documents containing a word or phrase in the "author name"
	// element M,O
	// http://api.springer.com/openaccess/app?q=name:Salvador&api_key=...

	// Any constraint can be negated by surrounding the constraint:argument pair
	// with "( )" and then preceeding it with the minus or hyphen symbol. This
	// operation will remove any results that meet the negated criteria from the
	// response.

	public SpringerApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		this.apiUser = apiParams.getApiUser();
		setId(apiParams.getId());
		setDate(apiParams.getDate());
		setSearchTerms(apiParams.getSearchTerms());
	}

	public SpringerApiParams() {
	}
}
