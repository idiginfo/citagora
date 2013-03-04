package org.idiginfo.docsvc.svcapi.altmetric;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class AltmetricRecord implements Document {

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

    class History {
	Double score;
	List<String> redddits;
	Map<Integer, String> tweets;
	List<String> fbwalls;
	List<Integer> posts;
	List<Integer> feeds;
    }

    class Image {
	String small;
	String medium;
	String large;
    }

    class Journal {
	Integer count;
	Integer rank;
	Double pct;
    }

    class Reader {
	Integer mendeley;
	Integer connotea;
	Integer citeulike;
    }

    @SerializedName("_id")
    String altId;// place for altmetricId with collection 'details'
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

    static DateFormat dateFormatter = DateFormat.getDateInstance();

    public Integer getAddedOn() {
	return addedOn;
    }

    public String getAltmetricId() {
	if (altmetricId != null && altmetricId.length() > 0)
	    return altmetricId;
	return altId;
    }

    public String getAltmetricJid() {
	return altmetricJid;
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
    public List<String> getAuthorList() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getAuthors() {
	// TODO Auto-generated method stub
	return null;
    }

    public Integer getCitedByAccountsCount() {
	return citedByAccountsCount;
    }

    public Integer getCitedByFBWallsCount() {
	return citedByFBWallsCount;
    }

    public Integer getCitedByFeedsCount() {
	return citedByFeedsCount;
    }

    public Integer getCitedByGPlusCount() {
	return citedByGPlusCount;
    }

    public Integer getCitedByPostsCount() {
	return citedByPostsCount;
    }

    public Integer getCitedByRdtsCount() {
	return citedByRdtsCount;
    }

    public Integer getCitedByTweetersCount() {
	return citedByTweetersCount;
    }

    public Cohort getCohorts() {
	return cohorts;
    }

    public Context getContext() {
	return context;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

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

    public String getDetailsUrl() {
	return detailsUrl;
    }

    public String getDoi() {
	return doi;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return doi;
    }

    public Map<String, JsonElement> getHistory() {
	return history;
    }

    public History getHistoryObject(String key) {
	JsonElement value = history.get(key);
	if (value == null)
	    return null;
	if (value.isJsonObject()) {

	}
	return null;
    }

    public String getHistoryString(String key) {
	JsonElement value = history.get(key);
	if (value == null)
	    return null;
	if (value.isJsonPrimitive())
	    return value.toString();
	return null;
    }

    @Override
    public String getId() {
	return doi;
    }

    public Image getImages() {
	return images;
    }

    @Override
    public String getIsbn() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getIsOA() {
	return isOA;
    }

    @Override
    public String getIssn() {
	// TODO Auto-generated method stub
	return null;
    }

    public String[] getIssns() {
	return issns;
    }

    @Override
    public String getIssue() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getJournal() {
	return journal;
    }

    @Override
    public List<String> getKeywords() {
	// TODO Auto-generated method stub
	return null;
    }

    public Integer getLastUpdated() {
	return lastUpdated;
    }

    @Override
    public List<String> getMeshTerms() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getName() {
	return title;
    }

    public String getNlmid() {
	return nlmid;
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
    public Integer getPageStart() {
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
	// TODO Auto-generated method stub
	return null;
    }

    public String getPublishedOn() {
	return publishedOn;
    }

    @Override
    public String getPublisher() {
	// TODO Auto-generated method stub
	return null;
    }

    public Reader getReaders() {
	return readers;
    }

    public Integer getReadersCount() {
	return readersCount;
    }

    public String getSchema() {
	return schema;
    }

    public String[] getScopusSubjects() {
	return scopusSubjects;
    }

    public Double getScore() {
	return score;
    }

    @Override
    public String getSource() {
	return AltmetricApiParams.SOURCE;
    }

    public String[] getSubjects() {
	return subjects;
    }

    public String getTitle() {
	return title;
    }

    public String[] getTq() {
	return tq;
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUri() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getUrl() {
	return url;
    }

    @Override
    public String getVolume() {
	// TODO Auto-generated method stub
	return null;
    }

	@Override
	public String getAbstractText() {
		// TODO Auto-generated method stub
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
	public String getLanguage() {
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

    @Override
    public String getIssued() {
	return publishedOn;
    }

    @Override
    public Date getIssuedDate() {
	// TODO Auto-generated method stub
	return null;
    }
}
