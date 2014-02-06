package org.idiginfo.docsvc.model.apisvc;

public interface MatchResult {

	String getText();

	Boolean getMatch();

	String getDoi();

	Double getScore();

	String getReason();

}
