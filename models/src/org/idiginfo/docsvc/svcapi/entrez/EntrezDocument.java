package org.idiginfo.docsvc.svcapi.entrez;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.aliasi.lingmed.medline.parser.Abstract;
import com.aliasi.lingmed.medline.parser.Article;
import com.aliasi.lingmed.medline.parser.Author;
import com.aliasi.lingmed.medline.parser.ELocationId;
import com.aliasi.lingmed.medline.parser.Journal;
import com.aliasi.lingmed.medline.parser.KeywordList;
import com.aliasi.lingmed.medline.parser.MedlineCitation;
import com.aliasi.lingmed.medline.parser.MeshHeading;
import com.aliasi.lingmed.medline.parser.PubDate;
import com.aliasi.lingmed.medline.parser.Topic;

public class EntrezDocument implements Document {

    MedlineCitation citation;
    Article article = null;
    Abstract abstrct = null;
    Journal journal = null;
    MeshHeading[] meshHeadings;

    public EntrezDocument(MedlineCitation citation) {
	this.citation = citation;

    }

    public Article getArticle() {
	if (article != null)
	    return article;
	if (citation != null) {
	    article = citation.article();
	}
	return article;
    }

    public Abstract getAbstract() {
	if (abstrct != null)
	    return abstrct;
	if (getArticle() != null) {
	    abstrct = article.abstrct();
	}
	return abstrct;
    }

    public Journal getJournal() {
	if (journal != null)
	    return journal;
	if (getArticle() != null) {
	    journal = article.journal();
	}
	return journal;
    }

    @Override
    public String getAbstractText() {
	if (getAbstract() == null)
	    return null;
	return abstrct.textWithoutTruncationMarker();
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
	if (getArticle() == null || article.authorList() == null)
	    return null;
	Vector<String> authors = new Vector<String>();
	for (Author author : article.authorList()) {
	    authors.add(author.collectiveName());
	}
	return authors;
    }

    @Override
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
	return abstrct.copyrightInformation();
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
	ELocationId[] ids = article.eLocationIds();
	for (ELocationId id : ids) {
	    if ("doi".equals(id.eIdType())) {
		return id.toString();
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

    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIsbn() {
	return null;
    }

    @Override
    public String getIssn() {
	if (getJournal() == null)
	    return null;
	return journal.issn();
    }

    @Override
    public String getIssue() {
	if (getJournal() == null || journal.journalIssue() == null)
	    return null;
	return journal.journalIssue().issue();
    }

    @Override
    public List<String> getKeywords() {
	if (citation == null || citation.keywordLists() == null)
	    return null;
	List<String> keywords = new Vector<String>();
	for (KeywordList keywordList : citation.keywordLists()) {
	    Topic[] topics = keywordList.keywords();
	    if (topics != null) {
		for (Topic topic : topics) {
		    keywords.add(topic.topic());
		}
	    }
	}
	return keywords;
    }

    @Override
    public String getLanguage() {
	if (getArticle() == null)
	    return null;
	return StringUtils.join(article.languages(), ", ");
    }

    @Override
    public List<String> getMeshTerms() {
	if (citation == null || citation.meshHeadings() == null)
	    return null;
	List<String> headings = new Vector<String>();
	for (MeshHeading heading : citation.meshHeadings()) {
	    headings.add(heading.toString());
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

    @Override
    public String getPages() {
	if (getArticle() == null)
	    return null;
	return article.pagination();
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
	if (getJournal() == null || journal.journalIssue() == null)
	    return null;
	PubDate pubDate = journal.journalIssue().pubDate();
	return pubDate.month() + pubDate.year();
    }

    @Override
    public String getPublicationName() {
	if (getJournal() == null)
	    return null;
	return journal.title();
    }

    @Override
    public String getPublisher() {
	return null;
    }

    @Override
    public String getPMId() {
	if (citation == null)
	    return null;
	return citation.pmid();
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
	return article.articleTitle();
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
	if (getJournal() == null || journal.journalIssue() == null)
	    return null;
	return journal.journalIssue().volume();
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
