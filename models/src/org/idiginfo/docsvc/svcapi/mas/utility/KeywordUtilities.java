package org.idiginfo.docsvc.svcapi.mas.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.idiginfo.docsvc.svcapi.mas.model.MasKeyword;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.model.MasResultObject;
import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;

public class KeywordUtilities {

	Map<Long, MasKeyword> keywordMap = new HashMap<Long, MasKeyword>();
	static MasService SERVICE = new MasService();
	int totalKeywords = 0;

	/**
	 * Get a list of keywords for a publication
	 * 
	 * @return
	 */
	public Map<Long, MasKeyword> fetchKeywords(Integer publicationId,
			int start, int end) {
		Map<Long, MasKeyword> keywordMap = new HashMap<Long, MasKeyword>();
		List<MasKeyword> block = getKeywords(publicationId, start, end);
		Iterator<MasKeyword> keywords = block.iterator();
		while (keywords.hasNext()) {
			MasKeyword keyword = keywords.next();
			keywordMap.put(keyword.getId(), keyword);
		}
		return keywordMap;
	}

	static final int NUM_ENTRIES_PER_CALL = 100;

	public Map<Long, MasKeyword> fetchAllKeywords(Integer publicationId) {
		// get first block of keywords (containing total number)
		Map<Long, MasKeyword> block = fetchKeywords(publicationId, 0,
				NUM_ENTRIES_PER_CALL);
		keywordMap.putAll(block);
		int numBlocks = (int) Math.ceil(totalKeywords / NUM_ENTRIES_PER_CALL);
		for (int b = 1; b < numBlocks; b++) {
			int startIdx = b * NUM_ENTRIES_PER_CALL;
			block = fetchKeywords(publicationId, startIdx, startIdx
					+ NUM_ENTRIES_PER_CALL);
			keywordMap.putAll(block);
		}
		return keywordMap;
	}

	public List<MasKeyword> getKeywords(Integer publicationId, int start,
			int end) {
		MasApiParams params = new MasApiParams();
		params.setPublicationId(publicationId);
		params.setPublicationContent("Keyword");
		params.setStartIdx(start);
		params.setEndIdx(end);
		params.setResultObjects("Keyword");
		MasResponse response = SERVICE.getResponse(params);
		MasResultObject<MasKeyword> keyword = response.getD().getKeyword();
		totalKeywords = keyword.getTotalItem();
		return keyword.getResult();
	}

	public Map<Long, MasKeyword> fetchAllKeywords(String keyword) {
		// get first block of keywords (containing total number)
		Map<Long, MasKeyword> block = fetchKeywords(keyword, 0,
				NUM_ENTRIES_PER_CALL);
		keywordMap.putAll(block);
		int numBlocks = (int) Math.ceil(totalKeywords / NUM_ENTRIES_PER_CALL);
		for (int b = 1; b < numBlocks; b++) {
			int startIdx = b * NUM_ENTRIES_PER_CALL;
			block = fetchKeywords(keyword, startIdx, startIdx
					+ NUM_ENTRIES_PER_CALL);
			keywordMap.putAll(block);
		}
		return keywordMap;
	}

	public List<MasKeyword> getKeywords(String keyword, int start, int end) {
		MasApiParams params = new MasApiParams();
		params.setFulltextQuery(keyword);
		params.setPublicationContent("Keyword");
		params.setStartIdx(start);
		params.setEndIdx(end);
		params.setResultObjects("Keyword");
		MasResponse response = SERVICE.getResponse(params);
		MasResultObject<MasKeyword> keywordResult = response.getD()
				.getKeyword();
		totalKeywords = keywordResult.getTotalItem();
		return keywordResult.getResult();
	}

	public Map<Long, MasKeyword> fetchKeywords(String keyword, int start,
			int end) {
		Map<Long, MasKeyword> keywordMap = new HashMap<Long, MasKeyword>();
		List<MasKeyword> block = getKeywords(keyword, start, end);
		Iterator<MasKeyword> keywords = block.iterator();
		while (keywords.hasNext()) {
			MasKeyword keywordObject = keywords.next();
			keywordMap.put(keywordObject.getId(), keywordObject);
		}
		return keywordMap;
	}

	public Map<Long, MasKeyword> getKeywordMap() {
		return keywordMap;
	}

	public int getTotalKeywords() {
		return totalKeywords;
	}

}
