package org.idiginfo.docsvc.svcapi.msrc;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Class
 * 
 * @author griccardi
 * 
 */
public class MsrcRecord implements Document {

    @SerializedName("bid")
    String bId;// place for biblio node id
    String publication_type; // ": "journal_article",
    String uid;// ": "295",
    @SerializedName("biblio_md5")
    String biblioMd5;
    @SerializedName("biblio_sort_title")
    String sortTitle; // :
		      // "Veterans and suicide a reexamination of the National Death Index",
    @SerializedName("biblio_formats")
    String formats;
    Long created; // "1331831805",
    Long changed; // "1350925161",
    @SerializedName("field_added_by_core_b")
    Map<String, Value[]> otherFields;
    @SerializedName("field_attatchment")// misspelling
    Map<String,Attachment[]> attachments;
    @SerializedName("field_category")
    JsonElement categories;
    @SerializedName("field_conclusion_section")
    String conclusion;
    
    
     class Value {
	String value;

    }
class Attachment{
    
}
    String title;
    String doi;
    String nlmid;
    @SerializedName("arxiv_id")
    String I;
    @SerializedName("ads_id")
    String adsId;
    String[] tq;
    @SerializedName("altmetric_jid")
    String altmetricJid;
    String[] issns;
    String journal;
    Cohort cohorts;
    @SerializedName("altmetric_id")
    String altmetricId;
    String schema;
    @SerializedName("is_oa")
    String isOA;
    // fields for cited_by_XXX
    @SerializedName("cited_by_fbwalls")
    String[] citedByFBWalls;
    @SerializedName("cited_by_fbwalls_count")
    Integer citedByFBWallsCount;
    @SerializedName("cited_by_feeds")
    String[] citedByFeeds;
    @SerializedName("cited_by_feeds_count")
    Integer citedByFeedsCount;
    @SerializedName("cited_by_gplus")
    String[] citedByGPlus;
    @SerializedName("cited_by_gplus_count")
    Integer citedByGPlusCount;
    @SerializedName("cited_by_posts")
    String[] citedByPosts;
    @SerializedName("cited_by_posts_count")
    Integer citedByPostsCount;
    @SerializedName("cited_by_rdts")
    String[] citedByRdts;
    @SerializedName("cited_by_rdts_count")
    Integer citedByRdtsCount;
    // This field may contain a list of pairs (index, value) instead of a list
    // of strings
    @SerializedName("cited_by_tweeters")
    Map<String, String> citedByTweeters;
    @SerializedName("cited_by_tweeters_count")
    Integer citedByTweetersCount;
    @SerializedName("cited_by_accounts")
    String[] citedByAccounts;
    @SerializedName("cited_by_accounts_count")
    Integer citedByAccountsCount;
    Context context;
    Double score;
    Map<String, JsonElement> history;
    String url;
    @SerializedName("added_on")
    Integer addedOn;
    @SerializedName("published_on")
    String publishedOn;
    String[] subjects;
    String[] scopusSubjects;
    @SerializedName("last_updated")
    Integer lastUpdated;
    @SerializedName("readers_count")
    Integer readersCount;
    Reader readers;
    Image images;
    @SerializedName("details_url")
    String detailsUrl;

    class Cohort {
	Integer sci;
	Integer pub;
	Integer com;
	Integer doc;
    }

    class Context {
	Journal journal;
	Journal all;
    }

    class Journal {
	Integer count;
	Integer rank;
	Double pct;
    }

    class History {
	Double score;
	List<String> redddits;
	Map<Integer, String> tweets;
	List<String> fbwalls;
	List<Integer> posts;
	List<Integer> feeds;
    }

    class Reader {
	Integer mendeley;
	Integer connotea;
	Integer citeulike;
    }

    class Image {
	String small;
	String medium;
	String large;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDoi() {
	return doi;
    }

    public void setDoi(String doi) {
	this.doi = doi;
    }

    public String getNlmid() {
	return nlmid;
    }

    public void setNlmid(String nlmid) {
	this.nlmid = nlmid;
    }

    public String[] getTq() {
	return tq;
    }

    public void setTq(String[] tq) {
	this.tq = tq;
    }


    public String[] getIssns() {
	return issns;
    }

    public void setIssns(String[] issns) {
	this.issns = issns;
    }

    public String getJournal() {
	return journal;
    }

    public void setJournal(String journal) {
	this.journal = journal;
    }

    public Cohort getCohorts() {
	return cohorts;
    }

    public void setCohorts(Cohort cohorts) {
	this.cohorts = cohorts;
    }


    public String getSchema() {
	return schema;
    }

    public void setSchema(String schema) {
	this.schema = schema;
    }

    public String getIsOA() {
	return isOA;
    }

    public void setIsOA(String isOA) {
	this.isOA = isOA;
    }

    public Integer getCitedByFBWallsCount() {
	return citedByFBWallsCount;
    }

    public void setCitedByFBWallsCount(Integer citedByFBWallsCount) {
	this.citedByFBWallsCount = citedByFBWallsCount;
    }

    public Integer getCitedByFeedsCount() {
	return citedByFeedsCount;
    }

    public void setCitedByFeedsCount(Integer citedByFeedsCount) {
	this.citedByFeedsCount = citedByFeedsCount;
    }

    public Integer getCitedByGPlusCount() {
	return citedByGPlusCount;
    }

    public void setCitedByGPlusCount(Integer citedByGPlusCount) {
	this.citedByGPlusCount = citedByGPlusCount;
    }

    public Integer getCitedByPostsCount() {
	return citedByPostsCount;
    }

    public void setCitedByPostsCount(Integer citedByPostsCount) {
	this.citedByPostsCount = citedByPostsCount;
    }

    public Integer getCitedByRdtsCount() {
	return citedByRdtsCount;
    }

    public void setCitedByRdtsCount(Integer citedByRdtsCount) {
	this.citedByRdtsCount = citedByRdtsCount;
    }

    public Integer getCitedByTweetersCount() {
	return citedByTweetersCount;
    }

    public void setCitedByTweetersCount(Integer citedByTweetersCount) {
	this.citedByTweetersCount = citedByTweetersCount;
    }

    public Integer getCitedByAccountsCount() {
	return citedByAccountsCount;
    }

    public void setCitedByAccountsCount(Integer citedByAccountsCount) {
	this.citedByAccountsCount = citedByAccountsCount;
    }

    public Context getContext() {
	return context;
    }

    public Double getScore() {
	return score;
    }

    public void setScore(Double score) {
	this.score = score;
    }

    public Map<String, JsonElement> getHistory() {
	return history;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public Integer getAddedOn() {
	return addedOn;
    }

    public void setAddedOn(Integer addedOn) {
	this.addedOn = addedOn;
    }

    public String getPublishedOn() {
	return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
	this.publishedOn = publishedOn;
    }

    public String[] getSubjects() {
	return subjects;
    }

    public void setSubjects(String[] subjects) {
	this.subjects = subjects;
    }

    public String[] getScopusSubjects() {
	return scopusSubjects;
    }

    public void setScopusSubjects(String[] scopusSubjects) {
	this.scopusSubjects = scopusSubjects;
    }

    public Integer getLastUpdated() {
	return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
	this.lastUpdated = lastUpdated;
    }

    public Integer getReadersCount() {
	return readersCount;
    }

    public void setReadersCount(Integer readersCount) {
	this.readersCount = readersCount;
    }

    public Reader getReaders() {
	return readers;
    }

    public Image getImages() {
	return images;
    }

    public String getDetailsUrl() {
	return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
	this.detailsUrl = detailsUrl;
    }

    @Override
    public String getSource() {
	return MsrcApiParams.API_HOST;
    }

    @Override
    public String getId() {
	return doi;
    }

    @Override
    public void setId(String id) {
	doi = id;
    }

    static DateFormat dateFormatter = DateFormat.getDateInstance();

    @Override
    public String getDate() {
	if (publishedOn != null) {
	    return dateFormatter.format(publishedOn);
	}
	return null;
    }

    @Override
    public Date getDateObject() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setDate(Date date) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getName() {
	return title;
    }

    @Override
    public void setName(String name) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getOwner() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOwner(String owner) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setType(String type) {
	// TODO Auto-generated method stub

    }

    @Override
    public Document getParent() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setParent(Document parent) {
	// TODO Auto-generated method stub

    }

    @Override
    public Annotation[] getAnnotations() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getAuthors() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setAuthors(String authors) {
	// TODO Auto-generated method stub

    }

    @Override
    public int getNumAnnotations() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Annotation getAnnotation(int i) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return doi;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIsbn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPublicationName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPublisher() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setCopyright(String copyright) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setIsbn(String isbn) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setIssn(String issn) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPublicationName(String name) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPublisher(String publisher) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setVolume(String volume) {
	// TODO Auto-generated method stub

    }

    @Override
    public Integer getPageStart() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Integer getPageEnd() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPages() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssue() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setIssue(String issue) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<String> getAuthorList() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUri() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getBiblioId() {
	// TODO Auto-generated method stub
	return null;
    }
}