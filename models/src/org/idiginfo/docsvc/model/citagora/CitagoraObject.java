package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

public interface CitagoraObject {
	String getType();

	String getId();

	String getUri();

	String getWasAttributedTo();

	Date getCreated();

	String getSource();

	String getRights();

	CitagoraAgent getGenerator(); // same as wasAttributedTo?

	Date getGenerated();
}
