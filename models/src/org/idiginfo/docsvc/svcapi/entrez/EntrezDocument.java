package org.idiginfo.docsvc.svcapi.entrez;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.medline.Abstract;
import org.idiginfo.medline.Article;
import org.idiginfo.medline.Author;
import org.idiginfo.medline.AuthorList;
import org.idiginfo.medline.CollectiveName;
import org.idiginfo.medline.ELocationID;
import org.idiginfo.medline.EndPage;
import org.idiginfo.medline.ISSN;
import org.idiginfo.medline.Journal;
import org.idiginfo.medline.Keyword;
import org.idiginfo.medline.KeywordList;
import org.idiginfo.medline.Language;
import org.idiginfo.medline.MedlineCitation;
import org.idiginfo.medline.MedlinePgn;
import org.idiginfo.medline.MeshHeading;
import org.idiginfo.medline.MeshHeadingList;
import org.idiginfo.medline.Month;
import org.idiginfo.medline.PMID;
import org.idiginfo.medline.Pagination;
import org.idiginfo.medline.PubDate;
import org.idiginfo.medline.QualifierName;
import org.idiginfo.medline.StartPage;
import org.idiginfo.medline.Year;

/**
 * @author griccardi
 *
 */
/**
 * @author griccardi
 *
 */
/**
 * @author griccardi
 *
 */
public class EntrezDocument implements Document {

    MedlineCitation citation;
    Article article = null;
    Abstract abstrct = null;
    Journal journal = null;
    MeshHeading[] meshHeadings;

    public EntrezDocument(MedlineCitation citation) {
	this.citation = citation;

    }

    /**
     * 
     * @return
     */
    public Article getArticle() {
	if (article != null)
	    return article;
	if (citation != null) {
	    article = citation.getArticle();
	}
	return article;
    }

    public Abstract getAbstract() {
	if (abstrct != null)
	    return abstrct;
	if (getArticle() != null) {
	    abstrct = article.getAbstract();
	}
	return abstrct;
    }

    public Journal getJournal() {
	if (journal != null)
	    return journal;
	if (getArticle() != null) {
	    journal = article.getJournal();
	}
	return journal;
    }

    @Override
    public String getAbstractText() {
	if (getAbstract() == null)
	    return null;
	return abstrct.getAbstractText();
    }

    @Override
    public String getAggregationType() {
	return null;
    }

    @Override
    public Annotation getAnnotation(int i) {
	return null;
    }

    @Override
    public Annotation[] getAnnotations() {
	return null;
    }

    @Override
    public String getarXivId() {
	return null;
    }

    @Override
    public List<String> getAuthorList() {
	if (getArticle() == null || article.getAuthorList() == null)
	    return null;
	Vector<String> authors = new Vector<String>();
	AuthorList authorList = article.getAuthorList();

	for (Author author : authorList.getAuthor()) {
	    List<Object> list = author
		    .getLastNameOrForeNameOrInitialsOrSuffixOrNameIDOrCollectiveName();
	    for (Object obj : list) {
		if (obj instanceof CollectiveName) {
		    authors.add(((CollectiveName) obj).getvalue());
		}
	    }
	}
	return authors;
    }

    public String getAuthorNotes() {
	return null;
    }

    @Override
    public String getAuthors() {
	List<String> authors = getAuthorList();
	if (authors == null)
	    return null;
	return StringUtils.join(authors, ",");
    }

    @Override
    public String getCopyright() {
	if (getAbstract() == null)
	    return null;
	return abstrct.getCopyrightInformation();
    }

    @Override
    public String getCoverDate() {
	return getPublicationDate();
    }

    @Override
    public String getDate() {
	return getPublicationDate();
    }

    @Override
    public Date getDateObject() {
	return getIssuedDate();
    }

    @Override
    public String getDoi() {
	// <ELocationID EIdType="doi"
	// ValidYN="Y">10.1136/bmj.a1190</ELocationID>
	if (getArticle() == null)
	    return null;
	List<Object> ids = article.getPaginationOrELocationID();
	for (Object id : ids) {
	    if (id instanceof ELocationID) {
		ELocationID eId = (ELocationID) id;
		if ("doi".equals(eId.getEIdType())) {
		    return eId.getvalue();
		}
	    }
	}
	return null;
    }

    @Override
    public String getEdition() {
	return null;
    }

    @Override
    public String geteIssn() {
	return null;
    }

    @Override
    public String getGenre() {
	return null;
    }

    @Override
    public String getGUID() {
	return null;
    }

    /**
     * Use doi as id if avaliable, otherwise use pmid
     */
    @Override
    public String getId() {
	String doi = getDoi();
	if (doi != null && doi.length() > 0)
	    return doi;
	return getPMId();
    }

    @Override
    public String getIsbn() {
	return null;
    }

    @Override
    public String getIssn() {
	if (getJournal() == null)
	    return null;
	ISSN issn = journal.getISSN();
	return issn.getvalue();
    }

    @Override
    public String getIssue() {
	if (getJournal() == null || journal.getJournalIssue() == null)
	    return null;
	return journal.getJournalIssue().getIssue();
    }

    @Override
    public List<String> getKeywords() {
	if (citation == null || citation.getKeywordList() == null)
	    return null;
	List<String> keywords = new Vector<String>();
	for (KeywordList list : citation.getKeywordList()) {
	    List<Keyword> keywordList = list.getKeyword();
	    if (keywords != null) {
		for (Keyword keyword : keywordList) {
		    keywords.add(keyword.getvalue());
		}
	    }
	}
	return keywords;
    }

    @Override
    public String getLanguage() {
	if (getArticle() == null)
	    return null;
	List<Language> langs = article.getLanguage();
	List<String> language = new ArrayList<String>();
	for (Language lang : langs) {
	    language.add(lang.getvalue());
	}
	return StringUtils.join(language, ", ");
    }

    @Override
    public List<String> getMeshTerms() {
	if (citation == null || citation.getMeshHeadingList() == null)
	    return null;
	MeshHeadingList list = citation.getMeshHeadingList();
	List<String> headings = new Vector<String>();
	for (MeshHeading heading : list.getMeshHeading()) {
	    headings.add(heading.getDescriptorName().getvalue());
	    List<QualifierName> qualNames = heading.getQualifierName();
	    for (QualifierName name : qualNames) {
		headings.add(name.getvalue());
	    }
	}
	return headings;
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public int getNumAnnotations() {
	return 0;
    }

    @Override
    public String getItemNumber() {
	return null;
    }

    @Override
    public String getOwner() {
	return null;
    }

    @Override
    public Integer getPageEnd() {
	return null;
    }

    String startPage = "";
    String endPage = "";
    String medlinePgn;

    @Override
    public String getPages() {
	if (getArticle() == null)
	    return null;
	List<Object> objs = article.getPaginationOrELocationID();
	for (Object obj : objs) {
	    if (obj instanceof Pagination) {
		List<Object> pageInfo = ((Pagination) obj)
			.getStartPageOrEndPageOrMedlinePgn();
		for (Object pObj : pageInfo) {
		    if (pObj instanceof StartPage) {
			startPage = ((StartPage) pObj).getvalue();
		    } else if (pObj instanceof EndPage) {
			endPage = ((EndPage) obj).getvalue();
		    } else if (pObj instanceof MedlinePgn) {
			medlinePgn = ((MedlinePgn) pObj).getvalue();
			return medlinePgn;
		    }
		}
	    }
	}
	if (startPage.length() > 0)
	    return startPage + "-" + endPage;
	return null;
    }

    @Override
    public Integer getPageStart() {
	return null;
    }

    @Override
    public Document getParent() {
	return null;
    }

    @Override
    public String getPublicationDate() {
	// TODO note using Month and year only
	if (getJournal() == null || journal.getJournalIssue() == null)
	    return null;
	PubDate pubDate = journal.getJournalIssue().getPubDate();
	List<Object> dateFields = pubDate
		.getYearOrMonthOrDayOrSeasonOrMedlineDate();
	String dateStr = "";
	for (Object obj : dateFields) {
	    if (obj instanceof Month) {
		dateStr = ((Month) obj).getvalue() + dateStr;
	    } else if (obj instanceof Year) {
		dateStr += " " + ((Year) obj).getvalue();
	    }
	}
	return dateStr;
    }

    @Override
    public String getPublicationName() {
	if (getJournal() == null)
	    return null;
	return journal.getTitle();
    }

    @Override
    public String getPublisher() {
	return null;
    }

    @Override
    public String getPMId() {
	if (citation == null || citation.getPMID() == null)
	    return null;
	PMID pmid = citation.getPMID();
	return pmid.getvalue();
    }

    @Override
    public String getRights() {
	return getCopyright();
    }

    @Override
    public String getSeriesTitle() {
	return null;
    }

    @Override
    public String getSource() {
	return "entrez";
    }

    @Override
    public String getTitle() {
	if (getArticle() == null)
	    return null;
	return article.getArticleTitle();
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return "article";
    }

    @Override
    public String getUri() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUrl() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
	if (getJournal() == null || journal.getJournalIssue() == null)
	    return null;
	return journal.getJournalIssue().getVolume();
    }

    @Override
    public String getIssued() {
	return getPublicationDate();
    }

    DateFormat formatter = new SimpleDateFormat("MMM yyyy");

    @Override
    public Date getIssuedDate() {
	try {
	    return formatter.parse(getPublicationDate());
	} catch (Exception e) {
	}
	return null;
    }

}
