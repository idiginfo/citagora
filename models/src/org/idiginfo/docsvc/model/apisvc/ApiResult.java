package org.idiginfo.docsvc.model.apisvc;

import java.util.List;


public interface ApiResult {

	int getTotalResults();

	List<? extends Header> getHeaders();

}
