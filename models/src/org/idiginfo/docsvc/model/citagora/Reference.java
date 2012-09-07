package org.idiginfo.docsvc.model.citagora;

import java.util.Date;
import java.util.List;

public interface Reference extends CitagoraObject {

	String getSource();

	String getAbstract();

	String getTitle();

	Integer getPageStart();

	Integer getPageEnd();

	String getVolume();

	Date getIssued();

	String getPmid();

	String getDoi();

	CitagoraAgent wasAttributedTo();

	Date generatedAtTime();

	Reference isPartOf();

	List<Author> getAuthorList();

	List<Reference> getCitationList();

	List<String> getSeeAlso();
}
