package org.idiginfo.docsvc.model.apisvc;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Interface to implement the DocService object
 * 
 * @author griccardi
 * 
 */

public interface DocService {

	public String format(String content);

	public Document getDocument(ApiParams params);

	public List<? extends Document> getDocuments(ApiParams params);

	public Document getAnnotations(ApiParams params);

	public Document getAnnotations(Document document);

	public JsonElement matchService(List<String> refs);

	public Gson getGson();

	public JsonElement matchService(JsonArray refsArray);

	/**
	 * Get matches for a list of references
	 * 
	 * @param refStrings
	 * @return
	 */
	public List<? extends MatchResult> getMatch(String[] refStrings);

	public ApiResult getApiResult(String function, String keywords,
			ApiParams params);

}
