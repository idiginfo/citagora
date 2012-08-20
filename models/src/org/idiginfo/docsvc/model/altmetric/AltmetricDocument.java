package org.idiginfo.docsvc.model.altmetric;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.ws.ServiceMode;

import org.idiginfo.docsvc.model.model.Annotation;
import org.idiginfo.docsvc.model.model.Document;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

public class AltmetricDocument implements Document {

	// citation parameters documented at
	// http://api.altmetric.com/docs/call_citations.html
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
	@SerializedName("cited_by_fbwalls_count")
	Integer citedByFBWallsCount;
	@SerializedName("cited_by_feeds_count")
	Integer citedByFeedsCount;
	@SerializedName("cited_by_gplus_count")
	Integer citedByGPlusCount;
	@SerializedName("cited_by_posts_count")
	Integer citedByPostsCount;
	@SerializedName("cited_by_rdts_count")
	Integer citedByRdtsCount;
	@SerializedName("cited_by_tweeters_count")
	Integer citedByTweetersCount;
	@SerializedName("cited_by_accounts_count")
	Integer citedByAccountsCount;
	@SerializedName("context")
	Context context;
	Double score;
	History history;
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

	// also provided in 'details' fetch
	// see http://api.altmetric.com/docs/call_details.html
	String cited_by_xxxx; // where xxxx is a service, as above

	// History history; //
	String links; //
	String posts;

	static class Cohort {
		Integer sci;
		Integer pub;
		Integer com;
		Integer doc;
	}

	static class Context {
		Journal journal;
		Journal all;
	}

	static class Journal {
		Integer count;
		Integer rank;
		Double pct;
	}

	static class History {
		List<HistoryValue> values;
	}

	static class HistoryValue {
		String name;
		String value;

		HistoryValue(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	private class DateTimeDeserializer implements
			JsonDeserializer<HistoryValue> {
		public HistoryValue deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return new HistoryValue(json.getAsJsonPrimitive().getAsString(), "");
		}

	}

	static class Reader {
		Integer mendeley;
		Integer connotea;
		Integer citeulike;
	}

	static class Image {
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

	public String getAltmetricJid() {
		return altmetricJid;
	}

	public void setAltmetricJid(String altmetricJid) {
		this.altmetricJid = altmetricJid;
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

	public String getAltmetricId() {
		return altmetricId;
	}

	public void setAltmetricId(String altmetricId) {
		this.altmetricId = altmetricId;
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

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
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

	public Reader getReader() {
		return readers;
	}

	public Image getImage() {
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
		return AltmetricApiParams.SOURCE;
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
}
